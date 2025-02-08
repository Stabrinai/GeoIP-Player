package org.github.stabrinai.geoIPPlayer;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.github.stabrinai.geoIPPlayer.command.MainCommand;
import org.github.stabrinai.geoIPPlayer.listener.PlayerListener;
import org.github.stabrinai.geoIPPlayer.placeholder.Expansion;
import org.github.stabrinai.geoIPPlayer.service.GeoIPService;

import java.util.HashMap;

public final class GeoIPPlayer extends JavaPlugin {
    final HashMap<String, HashMap<String, String>> playerData = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        GeoIPService geoIpService = new GeoIPService(this);
        // 检查 PlaceHolderAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Expansion(this).register();
        }
        //注册监听器
        getServer().getPluginManager().registerEvents(new PlayerListener(this, geoIpService), this);
        // 注册命令
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> commands.registrar().register(MainCommand.registerAllCommand(this)));

    }

    @Override
    public void onDisable() {
        playerData.clear();
        super.onDisable();
    }

    public HashMap<String, HashMap<String, String>> getPlayerData() {
        return playerData;
    }
}
