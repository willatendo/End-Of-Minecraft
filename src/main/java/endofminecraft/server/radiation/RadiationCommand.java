package endofminecraft.server.radiation;

import static endofminecraft.EndOfMinecraftMod.UTILS;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class RadiationCommand implements Command<CommandSourceStack> {
	public static final RadiationCommand INSTANCE = new RadiationCommand();

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("setradiationlevel").requires((soruce) -> {
			return soruce.hasPermission(2);
		}).then(Commands.argument("level", IntegerArgumentType.integer(0, 6)).executes(INSTANCE)));
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		int level = IntegerArgumentType.getInteger(context, "level");

		RadiationHandler.setRadiationLevel(level);

		context.getSource().sendSuccess(UTILS.boundArgsTrans("command", "radiation.success", level + ""), true);

		return 0;
	}
}
