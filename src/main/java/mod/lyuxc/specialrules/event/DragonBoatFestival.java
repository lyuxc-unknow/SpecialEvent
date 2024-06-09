package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.RuleUtils;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class DragonBoatFestival {
    @SubscribeEvent
    public static void dragonBoatFestivalEvent(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if(RuleUtils.isEnableRule(Config.dragonBoatFestival)) {
            setPlayerHunger(player,20,20);
            addEffect(player,MobEffects.LUCK,200,1);
            addEffect(player,MobEffects.DAMAGE_RESISTANCE,400,4);
            addEffect(player,MobEffects.WATER_BREATHING,400,255);
            addEffect(player,MobEffects.HERO_OF_THE_VILLAGE,400,1);
            player.addTag("dragonBoatFestival");
            player.getAbilities().flying = true;
        } else if(player.getTags().contains("dragonBoatFestival")) {
            player.getAbilities().flying = false;
            player.removeTag("dragonBoatFestival");
        }
    }
    public static void setPlayerHunger(Player player, int hunger, float saturation) {
        FoodData foodData = player.getFoodData();
        foodData.setFoodLevel(hunger);
        foodData.setSaturation(saturation);
    }
    private static void addEffect(Player player,Holder<MobEffect> mobEffect,int duration,int amplifier) {
        player.addEffect(new MobEffectInstance(mobEffect,duration,amplifier));
    }
}
