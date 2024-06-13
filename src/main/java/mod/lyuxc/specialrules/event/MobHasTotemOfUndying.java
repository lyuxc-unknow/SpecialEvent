package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class MobHasTotemOfUndying {
    @SubscribeEvent
    public static void mobHasTotemOfUndyingEvent(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        if(Utils.isEnableRule(Config.mobHasTotemOfUndying)) {
            Utils.addLivingEntityToLevel(level,entity);
        }
    }
}
