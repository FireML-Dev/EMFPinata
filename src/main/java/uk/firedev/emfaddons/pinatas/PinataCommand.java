package uk.firedev.emfaddons.pinatas;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.StringArgument;
import uk.firedev.emfaddons.config.MessageConfig;

import java.util.concurrent.CompletableFuture;

public class PinataCommand extends CommandAPICommand {

    private static PinataCommand instance;

    private PinataCommand() {
        super("pinata");
        setPermission(CommandPermission.fromString("emfaddons.command.pinata"));
        withShortDescription("Spawn Piñatas!");
        withFullDescription("Spawn Piñatas!");
        withArguments(getPinataArgument());
        executesPlayer((player, arguments) -> {
            String[] args = arguments.rawArgs();
            if (args.length < 1) {
                player.sendMessage(MessageConfig.getInstance().getPinataNotValidMessage());
                return;
            }
            PinataType pinataType = PinataManager.getInstance().getPinataFromIdentifier(args[0]);
            if (pinataType == null) {
                player.sendMessage(MessageConfig.getInstance().getPinataNotValidMessage());
                return;
            }
            player.sendMessage(MessageConfig.getInstance().getPinataSpawnedMessage());
            pinataType.spawn(player.getLocation());
        });
    }

    public static PinataCommand getInstance() {
        if (instance == null) {
            instance = new PinataCommand();
        }
        return instance;
    }

    private Argument<?> getPinataArgument() {
        return new StringArgument("pinata").includeSuggestions(ArgumentSuggestions.stringsAsync(info ->
                CompletableFuture.supplyAsync(() ->
                        PinataManager.getInstance().getPinataList().stream().map(PinataType::getIdentifier).toArray(String[]::new)
        )));
    }

}
