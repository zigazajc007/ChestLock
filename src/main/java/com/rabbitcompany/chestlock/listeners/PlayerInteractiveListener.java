package com.rabbitcompany.chestlock.listeners;

import com.rabbitcompany.chestlock.ChestLock;
import com.rabbitcompany.chestlock.utils.Message;
import com.rabbitcompany.chestlock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractiveListener implements Listener {

    private ChestLock chestLock;

    public PlayerInteractiveListener(ChestLock plugin){
        chestLock = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        if(e.getClickedBlock() != null && Utils.canLockMaterial(e.getClickedBlock().getType())){
            if(Utils.isBlockLocked(e.getClickedBlock().getLocation())){
                if(!Utils.canOpenBlock(e.getPlayer().getName(), e.getClickedBlock().getLocation())){
                    e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                    e.setCancelled(true);
                }
            }
        }

    }
}
