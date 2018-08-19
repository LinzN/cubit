/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.internal.cubitRegion.flags;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.ICubitPacket;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import java.util.HashSet;

public class MonsterPacket implements ICubitPacket {

    @Override

    public CubitLand enablePacket(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(Flags.MOB_DAMAGE, StateFlag.State.DENY);
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, new HashSet<com.sk89q.worldedit.world.entity.EntityType>() {
            {
                add(BukkitAdapter.adapt(EntityType.CREEPER));
                add(BukkitAdapter.adapt(EntityType.ZOMBIE));
                add(BukkitAdapter.adapt(EntityType.SKELETON));
                add(BukkitAdapter.adapt(EntityType.SILVERFISH));
                add(BukkitAdapter.adapt(EntityType.ENDER_DRAGON));
                add(BukkitAdapter.adapt(EntityType.WITHER));
                add(BukkitAdapter.adapt(EntityType.WITHER_SKULL));
                add(BukkitAdapter.adapt(EntityType.GIANT));
                add(BukkitAdapter.adapt(EntityType.PIG_ZOMBIE));
                add(BukkitAdapter.adapt(EntityType.CAVE_SPIDER));
                add(BukkitAdapter.adapt(EntityType.SPIDER));
                add(BukkitAdapter.adapt(EntityType.WITCH));
                add(BukkitAdapter.adapt(EntityType.ENDERMITE));
                add(BukkitAdapter.adapt(EntityType.GUARDIAN));
                add(BukkitAdapter.adapt(EntityType.ZOMBIE_VILLAGER));
                add(BukkitAdapter.adapt(EntityType.HUSK));
                add(BukkitAdapter.adapt(EntityType.POLAR_BEAR));
                add(BukkitAdapter.adapt(EntityType.EVOKER));
                add(BukkitAdapter.adapt(EntityType.SHULKER));
                add(BukkitAdapter.adapt(EntityType.MAGMA_CUBE));
                add(BukkitAdapter.adapt(EntityType.STRAY));
                add(BukkitAdapter.adapt(EntityType.VEX));
                add(BukkitAdapter.adapt(EntityType.VINDICATOR));
            }
        });
        return cubitLand;

    }

    @Override
    public CubitLand disablePacket(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(Flags.MOB_DAMAGE, StateFlag.State.ALLOW);
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, new HashSet<EntityType>() {
            {

            }
        });
        return cubitLand;

    }

    @Override
    public boolean getState(CubitLand cubitLand) {
        return cubitLand.getWGRegion().getFlag(Flags.MOB_DAMAGE) == StateFlag.State.DENY;
    }

    @Override
    public ChatColor getStateColor(CubitLand cubitLand) {
        if (getState(cubitLand)) {
            return ChatColor.GREEN;
        }
        return ChatColor.RED;
    }

    @Override
    public CubitLand switchState(CubitLand cubitLand, boolean value, boolean save) {
        CubitLand newCubitLand;
        if (value) {
            newCubitLand = enablePacket(cubitLand);
        } else {
            newCubitLand = disablePacket(cubitLand);
        }
        if (save) {
            CubitBukkitPlugin.inst().getRegionManager().getRegionSaver().save(cubitLand.getWorld());
        }
        return newCubitLand;
    }

    @Override
    public CubitLand switchState(CubitLand cubitLand, boolean save) {
        if (getState(cubitLand)) {
            return switchState(cubitLand, false, save);
        } else {
            return switchState(cubitLand, true, save);
        }
    }

    @Override
    public String getPacketName() {
        return "MONSTER";
    }
}
