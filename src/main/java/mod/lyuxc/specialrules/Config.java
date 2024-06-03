package mod.lyuxc.specialrules;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static String nowRule = "spawnZombie";
    public static ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
    public static ModConfigSpec.BooleanValue RULE0;
    public static ModConfigSpec.BooleanValue RULE1;
    public static ModConfigSpec.BooleanValue RULE2;
    public static ModConfigSpec.BooleanValue RULE3;
    public static final String oneHitOneKill = "oneHitOneKill";
    public static final String creepersCurse = "creepersCurse";
    public static final String invisibleCurse = "invisibleCurse";
    public static final String spawnZombie = "spawnZombie";
    public static final String noneCurse = "none";
    public static final String allCurse = "all";
    public static void init(ModContainer modContainer) {
        RULE0 = builder.comment("是否启用一击必杀").define("一击必杀",true);
        RULE1 = builder.comment("是否启用苦力怕的诅咒").define("苦力怕的诅咒",true);
        RULE2 = builder.comment("是否启用隐身诅咒").define("隐身诅咒",true);
        RULE3 = builder.comment("是否启用僵尸擂台赛").define("僵尸擂台赛",true);
        modContainer.registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}
