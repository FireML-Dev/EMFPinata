package uk.firedev.emfaddons.pinatas;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import uk.firedev.emfaddons.EMFAddons;
import uk.firedev.emfaddons.ScoreboardHelper;

import java.util.List;

public interface PinataType {

    String getIdentifier();

    EntityType getEntityType();

    String getDisplayName();

    int getHealth();

    boolean isGlowing();

    /**
     * Hooks into EvenMoreFish's reward system to manage piñata rewards.
     * @return The list of reward strings.
     */
    List<String> getRewards();

    boolean isSilent();

    String getGlowColor();

    default boolean register() {
        return PinataManager.getInstance().registerPinata(this);
    }

    default void spawn(@NotNull Location location) {
        World world = location.getWorld();
        if (world == null) {
            EMFAddons.getInstance().getLogger().warning("Invalid world!");
            return;
        }
        LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, getEntityType());
        if (getDisplayName() != null) {
            entity.setCustomNameVisible(true);
            entity.customName(MiniMessage.miniMessage().deserialize(getDisplayName()));
        }
        entity.setGlowing(isGlowing());
        if (getGlowColor() != null && !getGlowColor().isEmpty()) {
            ScoreboardHelper.getInstance().addToTeam(entity, getGlowColor());
        }
        AttributeInstance attribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            attribute.setBaseValue(getHealth());
            entity.setHealth(getHealth());
        }
        entity.setSilent(isSilent());
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.set(PinataManager.getInstance().getPinataKey(), PersistentDataType.BOOLEAN, true);
        pdc.set(PinataManager.getInstance().getPinataRewardsKey(), PersistentDataType.LIST.strings(), getRewards());
    }

}
