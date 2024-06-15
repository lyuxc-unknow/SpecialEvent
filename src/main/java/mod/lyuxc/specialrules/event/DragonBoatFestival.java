//package mod.lyuxc.specialrules.event;
//
//import mod.lyuxc.specialrules.Config;
//import mod.lyuxc.specialrules.utils.Utils;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.player.Player;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.tick.PlayerTickEvent;
//
//import static mod.lyuxc.specialrules.utils.Utils.addEffect;
//import static mod.lyuxc.specialrules.utils.Utils.setPlayerHunger;
//
//@EventBusSubscriber
//public class DragonBoatFestival {
//    @SubscribeEvent
//    public static void dragonBoatFestivalEvent(PlayerTickEvent.Pre event) {
//        Player player = event.getEntity();
//        if(Utils.isEnableRule(Config.dragonBoatFestival)) {
//            setPlayerHunger(player,20,20);
//            addEffect(player,MobEffects.LUCK,200,1);
//            addEffect(player,MobEffects.DAMAGE_RESISTANCE,400,4);
//            addEffect(player,MobEffects.WATER_BREATHING,400,255);
//            addEffect(player,MobEffects.HERO_OF_THE_VILLAGE,400,1);
//            player.addTag("dragonBoatFestival");
//            player.getAbilities().flying = true;
//        } else if(player.getTags().contains("dragonBoatFestival")) {
//            player.getAbilities().flying = false;
//            player.removeTag("dragonBoatFestival");
//        }
//    }
//}
