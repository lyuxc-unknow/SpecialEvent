package mod.lyuxc.specialrules;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import mod.lyuxc.specialrules.world.EventData;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class SpecialRulesCommands {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("specialrules")
            .then(Commands.literal("settime")
                .then(Commands.argument("settime",IntegerArgumentType.integer())
                    .executes(context -> {
                        int time = IntegerArgumentType.getInteger(context,"settime");
                        context.getSource().getPlayerOrException().sendSystemMessage(EventData.setSwitchTime(time));
                        return Command.SINGLE_SUCCESS;
                    })
                    .requires(r -> r.hasPermission(2))
                ))
            .then(Commands.literal("setrule")
                .then(Commands.argument("setrule", StringArgumentType.string())
                    .executes(context -> {
                        String rule = StringArgumentType.getString(context,"setrule");
                        context.getSource().getPlayerOrException().sendSystemMessage(EventData.setRule(rule));
                        return Command.SINGLE_SUCCESS;
                    })
                    .requires(r -> r.hasPermission(2))
                )
            )
            .then(Commands.literal("gettime")
                .executes(context -> {
                    context.getSource().getPlayerOrException().sendSystemMessage(EventData.getSwitchTime());
                    return Command.SINGLE_SUCCESS;
                })
            )
            .then(Commands.literal("getrule")
                .executes(context -> {
                    context.getSource().getPlayerOrException().sendSystemMessage(EventData.getRule());
                    return Command.SINGLE_SUCCESS;
                })
            )
        );
    }
}
