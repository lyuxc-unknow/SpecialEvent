package mod.lyuxc.specialrules;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Collections;
import java.util.List;

public class Config {
    private static final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
    private static ModConfigSpec.ConfigValue<List<String>> RULES;
    private static ModConfigSpec.IntValue SWITCH_TIME;
    public static final String noneCurse = "none";
    public static final String allCurse = "all";
    public static final String oneHitOneKill = "oneHitOneKill";
    public static final String creepersCurse = "creepersCurse";
    public static final String invisibleCurse = "invisibleCurse";
    public static final String spawnZombie = "spawnZombie";
    public static final String eternalCurse = "eternalCurse";
    public static final String longNight = "longNight";
    public static final String zombiePigPerish = "zombiePigPerish";
    public static final String explodeHit = "explodeHit";
    public static final String speedUp = "speedUp";
    public static final String snowmanImpersonator = "snowmanImpersonator";
    public static final String mobWillSplit = "mobWillSplit";
    public static final String dragonBoatFestival = "dragonBoatFestival";
    public static final String experienceIsHealth = "experienceIsHealth";
    public static final String mobHasTotemOfUndying = "mobHasTotemOfUndying";
    public static final String eternalCursePlus = "eternalCursePlus";
    public static final List<String> allRule = List.of(
            noneCurse,allCurse,oneHitOneKill,creepersCurse,invisibleCurse,spawnZombie,eternalCurse,longNight,zombiePigPerish,explodeHit,speedUp,
            snowmanImpersonator, mobWillSplit,experienceIsHealth,mobHasTotemOfUndying,eternalCursePlus
    );
    public static void init(ModContainer modContainer) {
        RULES = builder.comment("Enabled Rules" + allRule).worldRestart().defineInList("enableRules",allRule, Collections.singleton(allRule));
        SWITCH_TIME = builder.comment("Time to switch rules(in seconds)").defineInRange("timeToSwitchRules",900,1,3600);
        modContainer.registerConfig(ModConfig.Type.COMMON, builder.build());
    }
    public static List<String> getRules() {
        return RULES.get();
    }

    public static int getSwitchTime() {
        return SWITCH_TIME.get() * 20;
    }

}
