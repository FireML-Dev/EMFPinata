package uk.firedev.emfaddons;

import dev.jorel.commandapi.*;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;
import uk.firedev.emfaddons.config.MessageConfig;
import uk.firedev.emfaddons.pinatas.PinataManager;

public final class EMFAddons extends JavaPlugin {

    private static EMFAddons instance;

    @Override
    public void onLoad() {
        CommandAPIBukkitConfig conf = new CommandAPIBukkitConfig(this).shouldHookPaperReload(true);
        CommandAPI.onLoad(conf);
    }

    @Override
    public void onEnable() {
        instance = this;
        CommandAPI.onEnable();
        reload();
        registerCommands();
        System.out.println(NamedTextColor.NAMES.values());
    }

    public static EMFAddons getInstance() { return instance; }

    public void reload() {
        MessageConfig.getInstance().reload();
        PinataManager.getInstance().reload();
    }

    private void registerCommands() {
        MainCommand.getInstance().register();
    }

}
