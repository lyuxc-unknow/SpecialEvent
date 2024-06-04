package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber
public class LongNight {
    @SubscribeEvent
    public static void setNightAndRain(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        GameRules gameRules = event.getLevel().getGameRules();
        if(Config.nowRule.equals(Config.longNight)) {
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
}
