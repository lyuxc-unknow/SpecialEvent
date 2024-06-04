package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

@EventBusSubscriber
public class OneHitKill {
    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        Entity source = event.getSource().getEntity();
        if(Config.nowRule.equals(Config.oneHitOneKill) || Config.nowRule.equals(Config.allCurse)) {
            if(source != entity && event.getAmount() > Float.MIN_VALUE && entity.attackable()) {
                if(entity.getHealth() > 0) {
                    entity.setHealth(0.5f);
                    entity.die(event.getSource());
                }
            }
        }
    }
}
