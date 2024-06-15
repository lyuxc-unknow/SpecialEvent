package mod.lyuxc.specialrules.event.old;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class LongNight {
    @SubscribeEvent
    public static void setNightAndRain(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        GameRules gameRules = event.getLevel().getGameRules();
        if(Utils.isEnableRule(Config.longNight)) {
            if(level instanceof ServerLevel level1) {
                if(gameRules.getBoolean(GameRules.RULE_DAYLIGHT)) {
                    gameRules.getRule(GameRules.RULE_DAYLIGHT).set(false,level.getServer());
                    if (level1.getDayTime() > 18050L || level1.getDayTime() < 17950L) {
                        level1.setDayTime(18000L);
                    }
                    if (!level1.isRaining()){
                        level1.setRainLevel(1);
                    }
                    if (!level1.isThundering()) {
                        level1.setThunderLevel(1);
                    }
                } else if (!level1.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
                    level1.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true,level1.getServer());
                }
            }
        }
    }
    @SubscribeEvent
    public static void disableRightOnLongNight(PlayerInteractEvent.RightClickBlock event) {
        if(Utils.isEnableRule(Config.longNight)) {
            if(event.getLevel().getBlockState(event.getPos()).is(BlockTags.BEDS)) {
                event.getEntity().displayClientMessage(Component.translatable("specialEvent.longNight.description"),true);
                event.setCanceled(true);
            }
        }
    }
}
