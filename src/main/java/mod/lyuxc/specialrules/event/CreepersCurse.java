package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class CreepersCurse {
    @SubscribeEvent
    public static void updateToCreeper(EntityTickEvent.Pre event) {
        if(Config.nowRule.equals(Config.creepersCurse) || Config.nowRule.equals(Config.allCurse)) {
            if(event.getEntity() instanceof Mob) {
                if(!(event.getEntity() instanceof Creeper) && !(event.getEntity() instanceof Ghast)) {
                    Level level = event.getEntity().level();
                    double x = event.getEntity().getX();
                    double y = event.getEntity().getY();
                    double z = event.getEntity().getZ();
                    event.getEntity().setRemoved(Entity.RemovalReason.KILLED);
                    Creeper creeper = new Creeper(EntityType.CREEPER,level);
                    creeper.setPos(x,y,z);
                    creeper.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(0.4f);
                    level.addFreshEntity(creeper);
                }
            }
        }
    }
}
