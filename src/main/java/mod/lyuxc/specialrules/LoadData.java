package mod.lyuxc.specialrules;

import mod.lyuxc.specialrules.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class LoadData {
    private static int time = 0;
    private static List<String> ruleList = new ArrayList<>();
    @SubscribeEvent
     public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof Player) {
                ruleList.add("none");
                ruleList.add("all");
               if(Config.RULE0.get()) {
                   ruleList.add(Config.oneHitOneKill);
               }
               if(Config.RULE1.get()) {
                   ruleList.add(Config.creepersCurse);
               }
                if(Config.RULE2.get()) {
                    ruleList.add(Config.invisibleCurse);
                }
                if(Config.RULE3.get()) {
                    ruleList.add(Config.spawnZombie);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onTickEvent(ServerTickEvent.Pre event) {
        time++;
        if(time >= 3000) {
            time = 0;
            Config.nowRule = ruleList.get(RandomSource.create().nextIntBetweenInclusive(0,ruleList.size() - 1));
            for(Player player : event.getServer().getPlayerList().getPlayers()) {
                player.sendSystemMessage(Component.literal("当前事件" + Config.nowRule));
            }
        }
    }
}
