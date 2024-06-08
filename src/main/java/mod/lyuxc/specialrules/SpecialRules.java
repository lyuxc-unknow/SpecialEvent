package mod.lyuxc.specialrules;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import java.util.Map;
import java.util.TreeMap;

@Mod("specialrules")
public class SpecialRules {
    public static final Map<String, Component> translateMap = new TreeMap<>();
    public SpecialRules(ModContainer modContainer) {
        initMap();
        Config.init(modContainer);
    }
    public static void initMap() {
        for(String tranKey : Config.allRule) {
            translateMap.put(tranKey,Component.translatable("specialRules." + tranKey).withStyle(ChatFormatting.AQUA));
        }
    }
}
