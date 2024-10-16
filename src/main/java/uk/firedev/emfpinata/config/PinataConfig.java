package uk.firedev.emfpinata.config;

import com.oheers.fish.config.ConfigBase;
import com.oheers.fish.libs.boostedyaml.block.implementation.Section;
import org.bukkit.Bukkit;
import uk.firedev.emfpinata.EMFPinata;
import uk.firedev.emfpinata.pinatas.PinataManager;
import uk.firedev.emfpinata.pinatas.PinataType;
import uk.firedev.emfpinata.pinatas.internal.MythicMobsPinata;
import uk.firedev.emfpinata.pinatas.internal.Pinata;

public class PinataConfig extends ConfigBase {

    private static PinataConfig instance;

    private PinataConfig() {
        super("piñatas.yml", "piñatas.yml", EMFPinata.getInstance(), false);
    }

    public static PinataConfig getInstance() {
        if (instance == null) {
            instance = new PinataConfig();
        }
        return instance;
    }

    public void loadAllPinatas() {
        Section section = getConfig().getSection("pinatas");
        if (section == null) {
            return;
        }
        boolean mythicMobsEnabled = Bukkit.getPluginManager().isPluginEnabled("MythicMobs");
        section.getRoutesAsStrings(false).forEach(key -> {
            Section pinataSection = section.getSection(key);
            if (pinataSection == null) {
                return;
            }
            String displayName = pinataSection.getString("display-name");
            String type = pinataSection.getString("entity-type", "llama");
            PinataType pinataType;
            if (mythicMobsEnabled && type.startsWith("mythicmob:")) {
                String mythicMobType = type.replaceFirst("mythicmob:", "");
                pinataType = new MythicMobsPinata(key, mythicMobType, displayName);
            } else {
                pinataType = new Pinata(key, type, displayName);
            }
            pinataType.setGlowing(pinataSection.getBoolean("glowing", true));
            pinataType.setHealth(pinataSection.getInt("health", 120));
            pinataType.setSilent(pinataSection.getBoolean("silent", true));
            pinataType.setGlowColor(pinataSection.getString("glow-color", "aqua").toUpperCase());
            pinataType.setRewards(pinataSection.getStringList("rewards"));
            pinataType.setAware(pinataSection.getBoolean("has-awareness"));
            pinataType.register();
        });
    }

    @Override
    public void reload() {
        super.reload();
        PinataManager.getInstance().clearLoadedPinatas();
        loadAllPinatas();
    }

}
