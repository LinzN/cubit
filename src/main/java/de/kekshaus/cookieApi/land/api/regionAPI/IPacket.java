package de.kekshaus.cookieApi.land.api.regionAPI;

import org.bukkit.ChatColor;

import de.kekshaus.cookieApi.land.api.regionAPI.region.RegionData;

public interface IPacket {
	public abstract RegionData enablePacket(RegionData regionData);

	public abstract RegionData disablePacket(RegionData regionData);

	public abstract boolean getState(RegionData regionData);

	public abstract ChatColor getStateColor(RegionData regionData);

	public abstract RegionData switchState(RegionData regionData, boolean value);

	public abstract RegionData switchState(RegionData regionData);

	public abstract String getPacketName();
}