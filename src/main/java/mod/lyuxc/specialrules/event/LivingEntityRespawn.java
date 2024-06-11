package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class LivingEntityRespawn {
    @SubscribeEvent
    public static void mobHasTotemOfUndying(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        if(Utils.isEnableRule(Config.mobHasTotemOfUndying)) {
            if(level instanceof ServerLevel serverWorld) {
                if(entity instanceof Mob) {
                    Vec3 pos = entity.position();
                    serverWorld.getServer().execute(() -> {
                        EntityType<?> entityType = entity.getType();
                        Entity newEntity = entityType.create(serverWorld);
                        if(pos.y > -120) {
                            if (newEntity instanceof Mob newMob) {
                                newMob.moveTo(pos.x, pos.y, pos.z, entity.getYRot(), entity.getXRot());
                                newMob.setSpeed(1.5f);
                                serverWorld.addFreshEntity(newMob);
                            }
                        }
                    });
                }
            }
        }
    }
}
