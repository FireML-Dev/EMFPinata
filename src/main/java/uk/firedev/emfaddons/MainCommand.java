package uk.firedev.emfaddons;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import uk.firedev.emfaddons.config.MessageConfig;
import uk.firedev.emfaddons.pinatas.PinataCommand;

public class MainCommand extends CommandAPICommand {

    private static MainCommand instance;

    private MainCommand() {
        super("emfaddons");
        setPermission(CommandPermission.fromString("emfaddons.command"));
        withShortDescription("Manage the plugin");
        withFullDescription("Manage the plugin");
        withSubcommands(
                getReloadCommand(),
                PinataCommand.getInstance()
        );
    }

    public static MainCommand getInstance() {
        if (instance == null) {
            instance = new MainCommand();
        }
        return instance;
    }

    private CommandAPICommand getReloadCommand() {
        return new CommandAPICommand("reload")
                .executes((sender, arguments) -> {
                    EMFAddons.getInstance().reload();
                    sender.sendMessage(MessageConfig.getInstance().getReloadedMessage());
                });
    }

}
