package mod.lyuxc.specialrules.event;

import mod.lyuxc.specialrules.Config;
import mod.lyuxc.specialrules.utils.RuleUtils;
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
    public static void setPlayerHead(PlayerTickEvent.Pre event) {
        ItemStack pumpkinHead = new ItemStack(Items.CARVED_PUMPKIN);
        Player player = event.getEntity();
        pumpkinHead.enchant(Enchantments.BINDING_CURSE,1);
        pumpkinHead.enchant(Enchantments.VANISHING_CURSE,1);
        pumpkinHead.setCount(2);
        if(RuleUtils.isEnableRule(Config.snowmanImpersonator)) {
            player.setItemSlot(EquipmentSlot.HEAD,pumpkinHead.copy());
        }else if(player.getItemBySlot(EquipmentSlot.HEAD).is(Items.CARVED_PUMPKIN) &&
                player.getItemBySlot(EquipmentSlot.HEAD).getCount()==2) {
            player.setItemSlot(EquipmentSlot.HEAD,Items.AIR.getDefaultInstance());
        }
    }
}
