package mod.lyuxc.specialrules.event.old;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

@EventBusSubscriber
public class ExplodedHit {
    @SubscribeEvent
    public static void explodeHitEvent(LivingHurtEvent event) {
        Entity target = event.getEntity();
        Entity source = event.getSource().getEntity();
        Level level = target.level();
        if(Utils.isEnableRule(Config.explodeHit)) {
            if (!event.getSource().getMsgId().contains("explosion")) {
                float radius = event.getAmount() / 8.0F;
                if (radius < 0.75F) radius = 0.75F;
                if (radius > 2.0F) radius = 2.0F;
                if (level.isEmptyBlock(new BlockPos((int) target.getX(), (int) (target.getY() + 1.0), (int) target.getZ()))) {
                    level.explode(source, target.getX(), target.getY() + 1.0, target.getZ(), radius, Level.ExplosionInteraction.TNT);
                }
            }
        }
    }
}
