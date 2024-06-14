package mod.lyuxc.specialrules;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import java.util.Map;
import java.util.TreeMap;

@Mod("specialevent")
public class SpecialEvent {
    public static final Map<String, Component> translateMap = new TreeMap<>();
    public SpecialEvent(ModContainer modContainer) {
        initMap();
        Config.init(modContainer);
    }
    public static void initMap() {
        for(String tranKey : Config.allRule) {
            translateMap.put(tranKey,Component.translatable("specialEvent." + tranKey).withStyle(ChatFormatting.AQUA));
        }
    }
}
