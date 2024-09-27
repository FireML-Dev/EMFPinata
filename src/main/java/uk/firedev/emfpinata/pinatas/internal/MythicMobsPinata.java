package uk.firedev.emfpinata.pinatas.internal;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.firedev.emfpinata.pinatas.PinataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MythicMobsPinata implements PinataType {

    private final String identifier;
    private String displayName;
    private int health = 20;
    private boolean glowing = false;
    private List<String> rewards = new ArrayList<>();
    private boolean silent = true;
    private String glowColor = "";
    private final @NotNull MythicMob mythicMob;
    private boolean hasAwareness;

    public MythicMobsPinata(@NotNull String identifier, @NotNull String mythicName, @Nullable String displayName) {
        this.identifier = identifier;
        this.displayName = displayName;
        this.mythicMob = MythicBukkit.inst().getMobManager().getMythicMob(mythicName).orElseThrow();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public Entity getEntity(@NotNull Location location) {
        Entity entity = mythicMob.spawn(BukkitAdapter.adapt(location), 1).getEntity().getBukkitEntity();
        applyCommonValues(entity);
        return entity;
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
    public boolean isGlowing() {
        return glowing;
    }

    @Override
    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
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

}