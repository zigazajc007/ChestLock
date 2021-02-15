package com.rabbitcompany.chestlock.listeners;

import com.rabbitcompany.chestlock.ChestLock;
import com.rabbitcompany.chestlock.utils.Message;
import com.rabbitcompany.chestlock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private ChestLock chestLock;

    public BlockPlaceListener(ChestLock plugin){
        chestLock = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(e.getBlock().getType() == Material.HOPPER || e.getBlock().getType() == Material.HOPPER_MINECART){
            if(Utils.canLockMaterial(e.getBlock().getRelative(BlockFace.EAST).getType())){
                if(Utils.isBlockLocked(e.getBlock().getRelative(BlockFace.EAST).getLocation())){
                    if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getBlock().getRelative(BlockFace.EAST).getLocation())){
                        e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                        e.setCancelled(true);
                    }
                }
            }

            if(Utils.canLockMaterial(e.getBlock().getRelative(BlockFace.WEST).getType())){
                if(Utils.isBlockLocked(e.getBlock().getRelative(BlockFace.WEST).getLocation())){
                    if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getBlock().getRelative(BlockFace.WEST).getLocation())){
                        e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                        e.setCancelled(true);
                    }
                }
            }

            if(Utils.canLockMaterial(e.getBlock().getRelative(BlockFace.SOUTH).getType())){
                if(Utils.isBlockLocked(e.getBlock().getRelative(BlockFace.SOUTH).getLocation())){
                    if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getBlock().getRelative(BlockFace.SOUTH).getLocation())){
                        e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                        e.setCancelled(true);
                    }
                }
            }

            if(Utils.canLockMaterial(e.getBlock().getRelative(BlockFace.NORTH).getType())){
                if(Utils.isBlockLocked(e.getBlock().getRelative(BlockFace.NORTH).getLocation())){
                    if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getBlock().getRelative(BlockFace.NORTH).getLocation())){
                        e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                        e.setCancelled(true);
                    }
                }
            }

            if(Utils.canLockMaterial(e.getBlock().getRelative(BlockFace.UP).getType())){
                if(Utils.isBlockLocked(e.getBlock().getRelative(BlockFace.UP).getLocation())){
                    if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getBlock().getRelative(BlockFace.UP).getLocation())){
                        e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                        e.setCancelled(true);
                    }
                }
            }

            if(Utils.canLockMaterial(e.getBlock().getRelative(BlockFace.DOWN).getType())){
                if(Utils.isBlockLocked(e.getBlock().getRelative(BlockFace.DOWN).getLocation())){
                    if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getBlock().getRelative(BlockFace.DOWN).getLocation())){
                        e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}
