package mod.lyuxc.specialrules.event.old;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.RuleUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class InvisibleCurse {
    @SubscribeEvent
    public static void invisibleCurseEvent(EntityTickEvent.Pre event) {
        if(RuleUtils.isEnableRule(Config.invisibleCurse)) {
            event.getEntity().setInvisible(true);
        }
    }
}
