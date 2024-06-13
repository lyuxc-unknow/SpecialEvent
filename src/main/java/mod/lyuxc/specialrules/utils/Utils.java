package mod.lyuxc.specialrules.utils;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.RulesData;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.Optional;

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

    /**
     * 附魔物品
     * @param itemStack 需要附魔的物品
     * @param enchantment 需要的附魔
     * @param level 附魔等级
     * */
    public static void addEnchantment(ItemStack itemStack, ResourceKey<Enchantment> enchantment, int level) {
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = CommonHooks.resolveLookup(Registries.ENCHANTMENT);
        Optional<Holder.Reference<Enchantment>> enchantmentReference;
        if (enchantmentRegistryLookup != null) {
            enchantmentReference = enchantmentRegistryLookup.get(enchantment);
            enchantmentReference.ifPresent(reference -> itemStack.enchant(reference.getDelegate(), level));
        }
    }

    /**
     * 向世界添加生物
     * @param level 需要生成生物的世界
     * @param entity 需要生成的生物
     * */
    public static void addLivingEntityToLevel(Level level, Entity entity) {
        if(level instanceof ServerLevel serverWorld) {
            if(entity instanceof Mob) {
                Vec3 pos = entity.position();
                serverWorld.getServer().execute(() -> {
                    EntityType<?> entityType = entity.getType();
                    Entity newEntity = entityType.create(serverWorld);
                    if(pos.y > -120) {
                        if (newEntity instanceof Mob newMob) {
                            newMob.moveTo(pos.x, pos.y, pos.z, entity.getYRot(), entity.getXRot());
                            newMob.setSpeed(1.5f);
                            serverWorld.addFreshEntity(newMob);
                        }
                    }
                });
            }
        }
    }
}
