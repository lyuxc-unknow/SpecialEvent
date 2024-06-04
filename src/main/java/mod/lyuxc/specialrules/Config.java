package mod.lyuxc.specialrules;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static String nowRule = "zombiePigPerish";
    public static ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
    public static ModConfigSpec.BooleanValue RULE0;
    public static ModConfigSpec.BooleanValue RULE1;
    public static ModConfigSpec.BooleanValue RULE2;
    public static ModConfigSpec.BooleanValue RULE3;
    public static ModConfigSpec.BooleanValue RULE4;
    public static ModConfigSpec.BooleanValue RULE5;
    public static ModConfigSpec.BooleanValue RULE6;
    public static ModConfigSpec.BooleanValue RULE7;
    public static ModConfigSpec.BooleanValue RULE8;
    public static final String oneHitOneKill = "oneHitOneKill";
    public static final String creepersCurse = "creepersCurse";
    public static final String invisibleCurse = "invisibleCurse";
    public static final String spawnZombie = "spawnZombie";
    public static final String eternalCurse = "eternalCurse";
    public static final String longNight = "longNight";
    public static final String zombiePigPerish = "zombiePigPerish";
    public static final String explodeHit = "explodeHit";
    public static final String speedUp = "speedUp";
    public static final String noneCurse = "none";
    public static final String allCurse = "all";
    public static void init(ModContainer modContainer) {
        RULE0 = builder.comment("是否启用一击必杀").define("一击必杀",true);
        RULE1 = builder.comment("是否启用苦力怕的诅咒").define("苦力怕的诅咒",true);
        RULE2 = builder.comment("是否启用隐身诅咒").define("隐身诅咒",true);
        RULE3 = builder.comment("是否启用僵尸擂台赛").define("僵尸擂台赛",true);
        RULE4 = builder.comment("是否启用永生诅咒").define("永生诅咒",true);
        RULE5 = builder.comment("是否启用永夜").define("永夜",true);
        RULE6 = builder.comment("是否启用僵尸猪猪之亡音").define("僵尸猪猪之亡音",true);
        RULE7 = builder.comment("是否启用攻击Boom!").define("攻击Boom!",true);
        RULE8 = builder.comment("是否启用怪物都是闪电侠").define("怪物都是闪电侠",true);
        modContainer.registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}
