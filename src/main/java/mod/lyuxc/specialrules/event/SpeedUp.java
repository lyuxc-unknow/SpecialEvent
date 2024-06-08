package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.world.LoadData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;

import java.util.Random;

@EventBusSubscriber
public class SpeedUp {
    @SubscribeEvent
    public static void speedUp(LivingChangeTargetEvent event) {
        Entity entity = event.getEntity();
        Entity target = event.getNewTarget();
        if(LoadData.getNowRule().equals(Config.speedUp) || LoadData.getNowRule().equals(Config.allCurse)) {
            if(target instanceof Player player && !(entity instanceof EnderMan)) {
                Level level = event.getEntity().level();
                double px = player.getX();
                double py = player.getY();
                double pz = player.getZ();
                Random r = new Random();
                int dx = 2 + r.nextInt(2);
                int dz = 2 + r.nextInt(2);
                int x = (int)(r.nextBoolean() ? px + (double)dx : px - (double)dx);
                int z = (int)(r.nextBoolean() ? pz + (double)dz : pz - (double)dz);
                for(int y = (int)py + 16; y >= (int)py - 16; --y) {
                    if (level.isEmptyBlock(new BlockPos(x, y, z)) && level.isEmptyBlock(new BlockPos(x, y + 1, z)) && !level.isEmptyBlock(new BlockPos(x,y-1,z))) {
                        entity.setPos(x, y, z);
                        break;
                    }
                }
            }
        }
    }
}
