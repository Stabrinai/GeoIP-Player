package org.github.stabrinai.geoIPPlayer.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.apache.commons.lang3.StringUtils;
import org.github.stabrinai.geoIPPlayer.GeoIPPlayer;

import java.util.HashMap;

public class LookupCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> createCommand(GeoIPPlayer plugin) {
        final HashMap<String, HashMap<String, String>> playerData = plugin.getPlayerData();
        return Commands.literal("lookup")
                .requires(ctx -> ctx.getSender().hasPermission("geoip.lookup"))
                .then(Commands.argument("player", StringArgumentType.word())
                        .suggests((ctx, sug) -> {
                            String input = ctx.getInput().split("lookup")[1].strip();
                            for (String name : playerData.keySet().stream().filter(name -> name.startsWith(input)).toList()) {
                                sug.suggest(name);
                            }
                            return sug.buildFuture();
                        })
                        .executes(ctx -> {
                                    String playerName = ctx.getArgument("player", String.class);
                                    StringBuilder result = new StringBuilder("<#0288d1>" + playerName + " Lookup Result: </#0288d1>");
                                    if (playerData.get(playerName) != null) {
                                        result.append("\n<GRAY> - </GRAY><WHITE>");
                                        for (String key : playerData.get(playerName).keySet()) {
                                            result.append("<#91c6f3>").append(StringUtils.capitalize(key)).append(": </#91c6f3>").append(playerData.get(playerName).get(key)).append("\n<GRAY> - </GRAY>");
                                        }
                                        result.delete(result.length() - 17, result.length());
                                        ctx.getSource().getSender().sendRichMessage(result.toString());
                                    } else {
                                        ctx.getSource().getSender().sendRichMessage(result.toString());
                                    }
                                    return Command.SINGLE_SUCCESS;
                                }
                        )
                );
    }
}
