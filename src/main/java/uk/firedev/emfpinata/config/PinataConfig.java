package uk.firedev.emfpinata.config;

import com.oheers.fish.config.ConfigBase;
import com.oheers.fish.libs.boostedyaml.block.implementation.Section;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import uk.firedev.emfpinata.EMFPinata;
import uk.firedev.emfpinata.pinatas.Pinata;
import uk.firedev.emfpinata.pinatas.PinataManager;

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
        section.getRoutesAsStrings(false).forEach(key -> {
            Section pinataSection = section.getSection(key);
            if (pinataSection == null) {
                return;
            }
            @NotNull EntityType entityType;
            try {
                entityType = EntityType.valueOf(pinataSection.getString("entity-type", "llama").toUpperCase());
            } catch (IllegalArgumentException ex) {
                entityType = EntityType.LLAMA;
            }
            Pinata pinata = new Pinata(key, entityType, pinataSection.getString("display-name"));
            pinata.setGlowing(pinataSection.getBoolean("glowing", true));
            pinata.setHealth(pinataSection.getInt("health", 120));
            pinata.setSilent(pinataSection.getBoolean("silent", true));
            pinata.setGlowColor(pinataSection.getString("glow-color", "aqua").toUpperCase());
            pinata.setRewards(pinataSection.getStringList("rewards"));
            pinata.setAware(pinataSection.getBoolean("has-awareness"));
            pinata.register();
        });
    }

    @Override
    public void reload() {
        super.reload();
        PinataManager.getInstance().clearLoadedPinatas();
        loadAllPinatas();
    }

}
