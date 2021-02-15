package com.rabbitcompany.chestlock.listeners;

import com.rabbitcompany.chestlock.ChestLock;
import com.rabbitcompany.chestlock.utils.Message;
import com.rabbitcompany.chestlock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private ChestLock chestLock;

    public BlockBreakListener(ChestLock plugin){
        chestLock = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(Utils.canLockMaterial(e.getBlock().getType())){
            if(Utils.isBlockLocked(e.getBlock().getLocation())){
                e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "message_break_locked_block"));
                e.setCancelled(true);
            }
        }else if(e.getBlock().getState() instanceof Sign){
            Sign sign = (Sign) e.getBlock().getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))){
                if(!sign.getLine(1).equals(e.getPlayer().getName()) && !sign.getLine(2).equals(e.getPlayer().getName()) && !sign.getLine(3).equals(e.getPlayer().getName()) && !e.getPlayer().hasPermission("chestlock.break")){
                    e.getPlayer().sendMessage(Message.getMessage(e.getPlayer().getUniqueId(), "prefix") + Message.getMessage(e.getPlayer().getUniqueId(), "permission"));
                    e.setCancelled(true);
                }
            }
        }
    }

}
