package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.LoadData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class InvisibleCurse {
    @SubscribeEvent
    public static void InvisibleCurseEvent(EntityTickEvent.Pre event) {
        if(LoadData.getNowRule().equals(Config.invisibleCurse) || LoadData.getNowRule().equals(Config.allCurse)) {
            event.getEntity().setInvisible(true);
        }
    }
}
