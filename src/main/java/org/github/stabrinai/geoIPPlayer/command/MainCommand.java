package org.github.stabrinai.geoIPPlayer.command;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.github.stabrinai.geoIPPlayer.GeoIPPlayer;

public class MainCommand {
    public static LiteralCommandNode<CommandSourceStack> registerAllCommand(GeoIPPlayer plugin) {
        return Commands.literal("geoip")
                .requires(ctx -> ctx.getSender().hasPermission("geoip.command"))
                .then(ListCommand.createCommand(plugin))
                .then(ReloadCommand.createCommand(plugin))
                .then(LookupCommand.createCommand(plugin))
                .build();
    }
}
