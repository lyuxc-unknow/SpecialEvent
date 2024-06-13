package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class MobWillSplit {
    @SubscribeEvent
    public static void mobWillSplitEvent(LivingDeathEvent event) {
        if(Utils.isEnableRule(Config.mobWillSplit)) {
            Entity entity = event.getEntity();
            Level level = entity.level();
            Utils.addLivingEntityToLevel(level,entity);
            Utils.addLivingEntityToLevel(level,entity);
        }
    }
}
