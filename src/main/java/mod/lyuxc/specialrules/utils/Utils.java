package mod.lyuxc.specialrules.utils;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.RulesData;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

public class Utils {
    /**
     * 用于判断玩家当前规则
     * @param rule 需要判断的规则
     * */
    public static boolean isEnableRule(String rule) {
        return RulesData.getNowRule().equals(rule) || RulesData.getNowRule().equals(Config.allCurse);
    }
    /**
     * 用于修改玩家饥饿值
     * @param player 需要修改饥饿值的玩家
     * @param hunger 饥饿值
     * @param saturation 饱和度
     * */
    public static void setPlayerHunger(Player player, int hunger, float saturation) {
        FoodData foodData = player.getFoodData();
        foodData.setFoodLevel(hunger);
        foodData.setSaturation(saturation);
    }
    /**
     * 向实体添加药水效果
     * @param entity 待添加效果的实体
     * @param mobEffect 药水效果
     * @param duration 持续事件
     * @param amplifier 药水等级
     * */
    public static void addEffect(LivingEntity entity, Holder<MobEffect> mobEffect, int duration, int amplifier) {
        entity.addEffect(new MobEffectInstance(mobEffect,duration,amplifier));
    }
}
