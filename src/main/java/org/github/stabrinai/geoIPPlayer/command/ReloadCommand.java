package org.github.stabrinai.geoIPPlayer.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.github.stabrinai.geoIPPlayer.GeoIPPlayer;

public class ReloadCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> createCommand(GeoIPPlayer plugin) {
        return Commands.literal("reload")
                .requires(ctx -> ctx.getSender().hasPermission("geoip.reload"))
                .executes(ctx -> {
                    plugin.reloadConfig();
                    ctx.getSource().getSender().sendRichMessage(plugin.getConfig().getString("messages.reload_config", ""));
                    return Command.SINGLE_SUCCESS;
                });
    }
}
