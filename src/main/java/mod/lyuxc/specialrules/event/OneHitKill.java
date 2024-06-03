package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

@EventBusSubscriber
public class OneHitKill {
    @SubscribeEvent
    public static void  onAttack(LivingAttackEvent event) {
        if(Config.nowRule.equals(Config.oneHitOneKill) || Config.nowRule.equals(Config.allCurse)) {
            if( event.getSource().getEntity() != event.getEntity() && event.getAmount() > Float.MIN_VALUE && event.getEntity().attackable()) {
                if(event.getEntity().getHealth() > 0) {
                    event.getEntity().setHealth(0.5f);
                    event.getEntity().die(event.getSource());
                }
            }
        }
    }
}
