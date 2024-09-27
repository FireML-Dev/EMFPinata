package uk.firedev.emfpinata.pinatas.internal;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.firedev.emfpinata.EMFPinata;
import uk.firedev.emfpinata.ScoreboardHelper;
import uk.firedev.emfpinata.pinatas.PinataManager;
import uk.firedev.emfpinata.pinatas.PinataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pinata implements PinataType {

    private final String identifier;
    private String displayName;
    private int health = 20;
    private boolean glowing = false;
    private List<String> rewards = new ArrayList<>();
    private boolean silent = true;
    private String glowColor = "";
    private final EntityType entityType;
    private boolean hasAwareness;

    public Pinata(@NotNull String identifier, @NotNull String entityTypeString, @Nullable String displayName) {
        this.identifier = identifier;
        this.displayName = displayName;

        @NotNull EntityType entityType;
        try {
            entityType = EntityType.valueOf(entityTypeString.toUpperCase());
        } catch (IllegalArgumentException ex) {
            entityType = EntityType.LLAMA;
        }
        this.entityType = entityType;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        if (health <= 0) {
            health = 1;
        }
        this.health = health;
    }

    @Override
    public boolean isAware() {
        return hasAwareness;
    }

    @Override
    public void setAware(boolean hasAwareness) {
        this.hasAwareness = hasAwareness;
    }

    @Override
    public boolean isGlowing() {
        return glowing;
    }

    @Override
    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    @Override
    public List<String> getRewards() {
        return rewards;
    }

    @Override
    public void setRewards(@NotNull List<String> rewards) {
        this.rewards = new ArrayList<>(rewards);
    }

    @Override
    public void addReward(@NotNull String reward) {
        this.rewards.add(reward);
    }

    @Override
    public void addRewards(@NotNull String... rewards) {
        this.rewards.addAll(Arrays.asList(rewards));
    }

    @Override
    public void addRewards(@NotNull List<String> rewards) {
        this.rewards.addAll(rewards);
    }

    @Override
    public boolean isSilent() {
        return silent;
    }

    @Override
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    @Override
    public String getGlowColor() {
        return glowColor;
    }

    @Override
    public void setGlowColor(@NotNull String glowColor) {
        this.glowColor = glowColor;
    }

    @Override
    public Entity getEntity(@NotNull Location location) {
        Entity entity = location.getWorld().spawnEntity(location, getEntityType());
        applyCommonValues(entity);
        return entity;
    }

}