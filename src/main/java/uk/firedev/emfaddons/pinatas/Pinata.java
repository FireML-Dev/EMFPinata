package uk.firedev.emfaddons.pinatas;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public Pinata(@NotNull String identifier, @NotNull EntityType entityType, @Nullable String displayName) {
        this.identifier = identifier;
        this.displayName = displayName;
        this.entityType = entityType;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0) {
            health = 1;
        }
        this.health = health;
    }

    @Override
    public boolean isGlowing() {
        return glowing;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    @Override
    public List<String> getRewards() {
        return rewards;
    }

    public void setRewards(@NotNull List<String> rewards) {
        this.rewards = new ArrayList<>(rewards);
    }

    public void addReward(@NotNull String reward) {
        this.rewards.add(reward);
    }

    public void addRewards(@NotNull String... rewards) {
        this.rewards.addAll(Arrays.stream(rewards).toList());
    }

    public void addRewards(@NotNull List<String> rewards) {
        this.rewards.addAll(rewards);
    }

    @Override
    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    @Override
    public String getGlowColor() {
        return glowColor;
    }

    public void setGlowColor(@NotNull String glowColor) {
        this.glowColor = glowColor;
    }

}
