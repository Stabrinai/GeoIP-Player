package org.github.stabrinai.geoIPPlayer.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.github.stabrinai.geoIPPlayer.GeoIPPlayer;
import org.github.stabrinai.geoIPPlayer.service.GeoIPService;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class PlayerListener implements Listener {
    private final GeoIPPlayer plugin;
    private final HashMap<String, HashMap<String, String>> playerData;
    private final GeoIPService service;

    public PlayerListener(GeoIPPlayer plugin, GeoIPService service) {
        this.plugin = plugin;
        this.service = service;
        this.playerData = plugin.getPlayerData();
    }


    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        InetSocketAddress ip = event.getPlayer().getAddress();
        playerData.remove(event.getPlayer().getName());
        if (ip != null) {
            playerData.put(event.getPlayer().getName(), service.lookupByIp(ip));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("toggle.delete_on_quit", true))
            playerData.remove(event.getPlayer().getName());
    }
}
