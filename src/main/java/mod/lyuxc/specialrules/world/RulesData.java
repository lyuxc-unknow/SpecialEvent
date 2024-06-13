package mod.lyuxc.specialrules.world;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.SpecialRules;
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
public class RulesData {
    private static String nowRule = Config.noneCurse;
    private static SpecialRulesData specialRulesData;
    private static File saveFile;
    private static int time = 0;
    @SubscribeEvent
    public static void onTickEvent(ServerTickEvent.Pre event) {
        time++;
        if(time >= Config.getSwitchTime()) {
            time = 0;
            nowRule = Config.getRules().get(getIndex(Config.getRules().size()));
            for(Player player : event.getServer().getPlayerList().getPlayers()) {
                player.sendSystemMessage(Component.translatable("specialRules.ruleTip", SpecialRules.translateMap.get(nowRule)));
            }
        }
    }
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getLevel().getServer() != null) {
                File worldDir = event.getLevel().getServer().getWorldPath(LevelResource.ROOT).toFile();
                saveFile = new File(worldDir, "special_rule_data.json");
                if (saveFile.exists()) {
                    try {
                        specialRulesData = SpecialRulesData.load(saveFile);

                    } catch (IOException e) {
                        e.fillInStackTrace();
                    }
                } else {
                    specialRulesData = new SpecialRulesData();
                }
                nowRule = specialRulesData.getRule();
                time = specialRulesData.getSwitchTime();
            }
        }
    }
    @SubscribeEvent
    public static void onWorldSave(LevelEvent.Save event) {
        if (!event.getLevel().isClientSide() && specialRulesData != null) {
            try {
                specialRulesData.setRule(nowRule);
                specialRulesData.setSwitchTime(time);
                specialRulesData.save(saveFile);
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }
    public static int getIndex(int maxIndex) {
        int i;
        do {
            i = RandomSource.create().nextIntBetweenInclusive(0, maxIndex - 1);
        } while (i == specialRulesData.getRandomValue());
        specialRulesData.setRandomValue(i);
        return i;
    }
    public static String getNowRule() {
        return nowRule;
    }
    public static Component setRule(String rule) {
        if(Config.getRules().contains(rule)) {
            nowRule = rule;
            time = 0;
            return Component.translatable("specialRules.setRule",SpecialRules.translateMap.get(nowRule));
        } else {
            return Component.translatable("specialRules.OutRange");
        }
    }
    public static Component getRule() {
        return Component.translatable("specialRules.currentRule",SpecialRules.translateMap.get(nowRule));
    }
    public static Component getSwitchTime() {
        int copyTime = time;
        return Component.translatable("specialRules.getTime",(Config.getSwitchTime() - copyTime) / 20);
    }
    public static Component setSwitchTime(int newTime) {
        if(newTime > Config.getSwitchTime()) {
            return Component.translatable("specialRules.OutRange");
        } else {
            time = newTime * 20;
            return Component.translatable("specialRules.successSetTime",(Config.getSwitchTime() - newTime * 20) / 20);
        }
    }
}
