package de.kekshaus.cookieApi.land.regionAPI;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.kekshaus.cookieApi.land.Landplugin;

public class CookieRegion {

	public static ProtectedRegion newRegion(int chunkX, int chunkZ, World world, Player player, String regionName) {
		final Vector min;
		final Vector max;
		final Vector2D min2D;
		final LocalPlayer localplayer = Landplugin.inst().getWorldGuardPlugin().wrapPlayer(player);

		min2D = new Vector2D(chunkX * 16, chunkZ * 16);
		min = new Vector(min2D.getBlockX(), 0, min2D.getBlockZ());
		max = min.add(15, world.getMaxHeight(), 15);

		ProtectedRegion region = new ProtectedCuboidRegion(regionName, min.toBlockVector(), max.toBlockVector());
		DefaultDomain domain = new DefaultDomain();
		domain.addPlayer(localplayer);
		region.setOwners(domain);
		return region;
	}

	public static void removeRegion(ProtectedRegion region, World world) {
		RegionManager manager = Landplugin.inst().getWorldGuardPlugin().getRegionManager(world);
		manager.removeRegion(region.getId());

	}

	public static ProtectedRegion updateOwner(ProtectedRegion region, World world, Player player) {
		final LocalPlayer localplayer = Landplugin.inst().getWorldGuardPlugin().wrapPlayer(player);
		region.getOwners().clear();
		DefaultDomain domain = new DefaultDomain();
		domain.addPlayer(localplayer);
		region.setOwners(domain);
		return region;
	}

}
