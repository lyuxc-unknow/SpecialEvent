package mod.lyuxc.specialrules.event.old;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class OneHitKill {
    @SubscribeEvent
        public static void oneHitKillEntity(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        Entity source = event.getSource().getEntity();
        if(Utils.isEnableRule(Config.oneHitOneKill)) {
            if(source != entity && event.getAmount() > Float.MIN_VALUE && entity.attackable()) {
                if(entity.getHealth() > 0) {
                    entity.setHealth(0.5f);
                }
            }
        }
    }
}
