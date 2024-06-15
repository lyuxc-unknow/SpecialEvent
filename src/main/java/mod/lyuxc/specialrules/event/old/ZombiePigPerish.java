package mod.lyuxc.specialrules.event.old;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class ZombiePigPerish {
    @SubscribeEvent
    public static void onDeathRespawn(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        Entity source = event.getSource().getEntity();
        if(Utils.isEnableRule(Config.zombiePigPerish)) {
            if(source instanceof Player player &&
                !(entity instanceof Piglin) &&
                !(entity instanceof Ghast) &&
                !(entity instanceof Guardian) &&
                !entity.level().isClientSide()) {
                spawnZombiePigPerish(entity,player);
            }
        }
    }
    private static void spawnZombiePigPerish(Entity entity,Player targetPlayer) {
        Level level = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ZombifiedPiglin zombifiedPiglin = new ZombifiedPiglin(EntityType.ZOMBIFIED_PIGLIN,level);
        zombifiedPiglin.setPos(x,y,z);
        zombifiedPiglin.setItemSlot(EquipmentSlot.HEAD,new ItemStack(Items.GOLDEN_HELMET));
        zombifiedPiglin.setItemSlot(EquipmentSlot.CHEST,new ItemStack(Items.GOLDEN_CHESTPLATE));
        zombifiedPiglin.setItemSlot(EquipmentSlot.LEGS,new ItemStack(Items.GOLDEN_LEGGINGS));
        zombifiedPiglin.setItemSlot(EquipmentSlot.FEET,new ItemStack(Items.GOLDEN_BOOTS));
        zombifiedPiglin.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(Items.GOLDEN_AXE));
        zombifiedPiglin.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(80);
        zombifiedPiglin.setHealth(zombifiedPiglin.getMaxHealth());
        zombifiedPiglin.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(14);
        zombifiedPiglin.setTarget(targetPlayer);
        level.addFreshEntity(zombifiedPiglin);
    }
}
