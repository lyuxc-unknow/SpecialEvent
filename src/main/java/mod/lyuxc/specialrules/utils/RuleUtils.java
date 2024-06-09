package mod.lyuxc.specialrules.utils;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.RulesData;

public class RuleUtils {
    /**
     * @param rule 需要判断的规则
     * */
    public static boolean isEnableRule(String rule) {
        return RulesData.getNowRule().equals(rule) || RulesData.getNowRule().equals(Config.allCurse);
    }
}
