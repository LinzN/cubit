package de.linzn.cubit.bukkit.plugin.listener;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Date;

public class LoginListener implements Listener {

	@EventHandler
	public void onPlayerJoin(final PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(CubitBukkitPlugin.inst(), () -> {
            CubitBukkitPlugin.inst().getDataAccessManager().databaseType.set_update_profile(
                    event.getPlayer().getUniqueId(), event.getPlayer().getName(), new Date().getTime());
            if (event.getPlayer().hasPermission(CubitBukkitPlugin.inst().getPermNodes().checkUpdateAdmin) && CubitBukkitPlugin.inst().getSpigetUpdateCheck() != null) {
                if (CubitBukkitPlugin.inst().getSpigetUpdateCheck().isAvailable) {
                    event.getPlayer()
                            .sendMessage(ChatColor.GREEN
                                    + "A new update for Cubit is avaiable. Check out the new version "
                                    + CubitBukkitPlugin.inst().getSpigetUpdateCheck().version + "!");
                }
            }
        });
    }
}
