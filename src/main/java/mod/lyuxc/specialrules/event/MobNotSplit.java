package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.RuleUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobSplitEvent;

@EventBusSubscriber
public class MobNotSplit {
    @SubscribeEvent
    public static void mobNotSplit(MobSplitEvent event) {
        if(RuleUtils.isEnableRule(Config.mobNotSplit)) {
            event.setCanceled(true);
        }
    }
}
