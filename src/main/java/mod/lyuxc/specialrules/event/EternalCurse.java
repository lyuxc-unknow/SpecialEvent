package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.LoadData;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber
public class EternalCurse {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if(LoadData.getNowRule().equals(Config.eternalCurse) || LoadData.getNowRule().equals(Config.allCurse)) {
            if(event.getEntity() instanceof Mob mob) {
                mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(Integer.MAX_VALUE);
                mob.setHealth(mob.getMaxHealth());
            }
        }
    }
}
