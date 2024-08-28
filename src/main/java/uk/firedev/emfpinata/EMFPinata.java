package uk.firedev.emfpinata;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import uk.firedev.emfpinata.config.ExampleConfig;
import uk.firedev.emfpinata.config.MessageConfig;
import uk.firedev.emfpinata.pinatas.PinataManager;

public final class EMFPinata extends JavaPlugin {

    private static EMFPinata instance;
    private MiniMessage miniMessage;

    @Override
    public void onLoad() {
        CommandAPIBukkitConfig conf = new CommandAPIBukkitConfig(this)
                .shouldHookPaperReload(true)
                .usePluginNamespace();
        CommandAPI.onLoad(conf);
    }

    @Override
    public void onEnable() {
        instance = this;
        CommandAPI.onEnable();
        reload();
        registerCommands();
        metrics();

        // Load the example config
        new ExampleConfig();
    }

    public static EMFPinata getInstance() { return instance; }

    public void reload() {
        MessageConfig.getInstance().reload();
        PinataManager.getInstance().reload();
    }

    public MiniMessage getMiniMessage() {
        if (miniMessage == null) {
            miniMessage = MiniMessage.miniMessage();
        }
        return miniMessage;
    }

    private void registerCommands() {
        MainCommand.getInstance().register();
    }

    private void metrics() {
        new Metrics(this, 22866);
    }

}
