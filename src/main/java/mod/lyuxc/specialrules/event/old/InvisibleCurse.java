package mod.lyuxc.specialrules.event.old;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class InvisibleCurse {
    @SubscribeEvent
    public static void invisibleCurseEvent(EntityTickEvent.Pre event) {
        if(Utils.isEnableRule(Config.invisibleCurse)) {
            if(event.getEntity() instanceof LivingEntity entity) {
                Utils.addEffect(entity, MobEffects.INVISIBILITY,20,5);
            }
        }
    }
}
