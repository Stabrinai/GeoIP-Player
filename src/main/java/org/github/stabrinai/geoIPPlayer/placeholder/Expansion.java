package org.github.stabrinai.geoIPPlayer.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.github.stabrinai.geoIPPlayer.GeoIPPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Expansion extends PlaceholderExpansion {
    private final HashMap<String, HashMap<String, String>> playerData;

    public Expansion(GeoIPPlayer plugin) {
        playerData = plugin.getPlayerData();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "geoip";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Stabrinai";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        return switch (params) {
            case "city", "country", "continent", "time_zone", "location" -> getValue(player, params);
            default -> null;
        };
    }

    private String getValue(OfflinePlayer player, String key) {
        HashMap<String, String> data = playerData.get(player.getName());

        if (data != null) {
            if (data.containsKey("ERROR")) {
                return data.get("ERROR");
            } else if (data.get(key) != null) {
                return data.get(key);
            }
        }

        return "";
    }
}
