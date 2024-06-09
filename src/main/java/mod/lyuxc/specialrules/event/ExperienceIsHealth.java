package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.ExperienceUtils;
import mod.lyuxc.specialrules.utils.RuleUtils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

@EventBusSubscriber
public class ExperienceIsHealth {
    @SubscribeEvent
    public static void experienceIsHealthEvent(LivingAttackEvent event) {
        if (RuleUtils.isEnableRule(Config.experienceIsHealth)) {
            if(event.getEntity() instanceof Player player) {
                if(ExperienceUtils.getXPoint(player) > 0) {
                    event.setCanceled(true);
                    ExperienceUtils.reduceXP(player,event.getAmount() * 10);
                }
            }
        }
    }
}
