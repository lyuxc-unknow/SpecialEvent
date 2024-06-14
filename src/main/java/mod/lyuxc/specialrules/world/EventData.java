package mod.lyuxc.specialrules.world;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.SpecialEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.io.File;
import java.io.IOException;

@EventBusSubscriber
public class EventData {
    private static String nowRule = Config.noneCurse;
    private static LevelEventData levelEventData;
    private static File saveFile;
    private static int time = 0;
    @SubscribeEvent
    public static void onTickEvent(ServerTickEvent.Pre event) {
        time++;
        if(time >= Config.getSwitchTime()) {
            time = 0;
            nowRule = Config.getRules().get(getIndex(Config.getRules().size()));
            for(Player player : event.getServer().getPlayerList().getPlayers()) {
                player.sendSystemMessage(Component.translatable("specialEvent.ruleTip", SpecialEvent.translateMap.get(nowRule)));
            }
        }
    }
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getLevel().getServer() != null) {
                File worldDir = event.getLevel().getServer().getWorldPath(LevelResource.ROOT).toFile();
                saveFile = new File(worldDir, "special_event_data.json");
                if (saveFile.exists()) {
                    try {
                        levelEventData = LevelEventData.load(saveFile);

                    } catch (IOException e) {
                        e.fillInStackTrace();
                    }
                } else {
                    levelEventData = new LevelEventData();
                }
                nowRule = levelEventData.getRule();
                time = levelEventData.getSwitchTime();
            }
        }
    }
    @SubscribeEvent
    public static void onWorldSave(LevelEvent.Save event) {
        if (!event.getLevel().isClientSide() && levelEventData != null) {
            try {
                levelEventData.setRule(nowRule);
                levelEventData.setSwitchTime(time);
                levelEventData.save(saveFile);
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }
    public static int getIndex(int maxIndex) {
        int i;
        do {
            i = RandomSource.create().nextIntBetweenInclusive(0, maxIndex - 1);
        } while (i == levelEventData.getRandomValue());
        levelEventData.setRandomValue(i);
        return i;
    }
    public static String getNowRule() {
        return nowRule;
    }
    public static Component setRule(String rule) {
        if(Config.getRules().contains(rule)) {
            nowRule = rule;
            time = 0;
            return Component.translatable("specialEvent.setRule", SpecialEvent.translateMap.get(nowRule));
        } else {
            return Component.translatable("specialEvent.OutRange");
        }
    }
    public static Component getRule() {
        return Component.translatable("specialEvent.currentRule", SpecialEvent.translateMap.get(nowRule));
    }
    public static Component getSwitchTime() {
        int copyTime = time;
        return Component.translatable("specialEvent.getTime",(Config.getSwitchTime() - copyTime) / 20);
    }
    public static Component setSwitchTime(int newTime) {
        if(newTime > Config.getSwitchTime()) {
            return Component.translatable("specialEvent.OutRange");
        } else {
            time = newTime * 20;
            return Component.translatable("specialEvent.successSetTime",(Config.getSwitchTime() - newTime * 20) / 20);
        }
    }
}
