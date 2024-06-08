package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.LoadData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
        if(LoadData.getNowRule().equals(Config.longNight) || LoadData.getNowRule().equals(Config.allCurse)) {
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
        if(LoadData.getNowRule().equals(Config.longNight) || LoadData.getNowRule().equals(Config.allCurse)) {
            if(isBedBlock(event.getLevel().getBlockState(event.getPos()).getBlock())) {
                event.setCanceled(true);
            }
        }
    }
    private static boolean isBedBlock(Block block) {
        return block == Blocks.RED_BED || block == Blocks.BLACK_BED || block == Blocks.BLUE_BED ||
                block == Blocks.BROWN_BED || block == Blocks.CYAN_BED || block == Blocks.GRAY_BED ||
                block == Blocks.GREEN_BED || block == Blocks.LIGHT_BLUE_BED || block == Blocks.LIGHT_GRAY_BED ||
                block == Blocks.LIME_BED || block == Blocks.MAGENTA_BED || block == Blocks.ORANGE_BED ||
                block == Blocks.PINK_BED || block == Blocks.PURPLE_BED || block == Blocks.WHITE_BED ||
                block == Blocks.YELLOW_BED;
    }
}
