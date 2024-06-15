package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.Utils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class SnowmanHead {
    @SubscribeEvent
    public static void snowmanHeadEvent(PlayerTickEvent.Pre event) {
        ItemStack pumpkinHead = new ItemStack(Items.CARVED_PUMPKIN);
        Player player = event.getEntity();
        Utils.addEnchantment(pumpkinHead,Enchantments.BINDING_CURSE,1);
        Utils.addEnchantment(pumpkinHead,Enchantments.VANISHING_CURSE,1);
        pumpkinHead.setCount(2);
        if(Utils.isEnableRule(Config.snowmanImpersonator)) {
            if(player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                player.setItemSlot(EquipmentSlot.HEAD,pumpkinHead.copy());
            } else if(!player.getItemBySlot(EquipmentSlot.HEAD).is(Items.CARVED_PUMPKIN)) {
                ItemStack itemInPlayerHead = player.getItemBySlot(EquipmentSlot.HEAD);
                player.setItemSlot(EquipmentSlot.HEAD,pumpkinHead);
                player.drop(itemInPlayerHead,true);
            }
        }else if(ItemStack.isSameItem(player.getItemBySlot(EquipmentSlot.HEAD),pumpkinHead)) {
            player.setItemSlot(EquipmentSlot.HEAD,Items.AIR.getDefaultInstance());
        }
    }
}
