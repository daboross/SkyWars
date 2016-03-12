/*
 * Copyright (C) 2016 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.skywars.commands.mainsubcommands;

import java.util.List;
import net.daboross.bukkitdev.commandexecutorbase.SubCommand;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import net.daboross.bukkitdev.skywars.api.players.OfflineSkyPlayer;
import net.daboross.bukkitdev.skywars.api.translations.SkyTrans;
import net.daboross.bukkitdev.skywars.api.translations.TransKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TopCommand extends SubCommand {

    private final SkyWars plugin;

    public TopCommand(final SkyWars plugin) {
        super("top", true, "skywars.top", SkyTrans.get(TransKey.CMD_TOP_DESCRIPTION));
        this.plugin = plugin;
    }

    @Override
    public void runCommand(final CommandSender sender, final Command baseCommand, final String baseCommandLabel, final String subCommandLabel, final String[] subCommandArgs) {
        int toGet = 10; // TODO: Parameter?
        List<? extends OfflineSkyPlayer> topList = plugin.getScore().getTopPlayers(toGet);
        if (topList.isEmpty()) {
            sender.sendMessage(SkyTrans.get(TransKey.CMD_TOP_NO_PLAYERS));
            return;
        }

        int got = Math.min(toGet, topList.size());

        sender.sendMessage(SkyTrans.get(TransKey.CMD_TOP_TITLE, got));
        for (OfflineSkyPlayer player : topList.subList(0, got)) {
            sender.sendMessage(SkyTrans.get(TransKey.CMD_TOP_FORMAT, player.getName(), player.getScore(), player.getRank()));
        }
    }
}
