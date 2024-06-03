package mod.lyuxc.specialrules;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod("specialrules")
public class SpecialRules {
    public SpecialRules(ModContainer modContainer, IEventBus modEventbus, Dist dist) {
        Config.init(modContainer);
    }
}
