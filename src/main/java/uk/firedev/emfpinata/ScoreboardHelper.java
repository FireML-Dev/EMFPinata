package uk.firedev.emfpinata;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardHelper {

    private static ScoreboardHelper instance;

    private final Map<NamedTextColor, Team> loadedTeams = new HashMap<>();

    private ScoreboardHelper() {}

    public static ScoreboardHelper getInstance() {
        if (instance == null) {
            instance = new ScoreboardHelper();
        }
        return instance;
    }

    public void addToTeam(@NotNull Entity entity, @NotNull String color) {
        color = color.toLowerCase();
        NamedTextColor namedTextColor = NamedTextColor.NAMES.value(color);
        if (namedTextColor == null) {
            EMFPinata.getInstance().getLogger().warning("Invalid piñata glow color: " + color + ". Not setting it.");
            return;
        }
        String teamName = "EMFPinata_" + color;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        String uuidString = entity.getUniqueId().toString();
        if (loadedTeams.containsKey(namedTextColor)) {
            loadedTeams.get(namedTextColor).addEntry(entity.getUniqueId().toString());
        } else {
            Team existingTeam = manager.getMainScoreboard().getTeam(teamName);
            if (existingTeam != null) {
                existingTeam.addEntry(uuidString);
                loadedTeams.put(namedTextColor, existingTeam);
                return;
            }
            Team team = manager.getMainScoreboard().registerNewTeam(teamName);
            team.color(namedTextColor);
            team.addEntry(uuidString);
            loadedTeams.put(namedTextColor, team);
        }
    }

}
