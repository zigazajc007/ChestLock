package com.rabbitcompany.chestlock.listeners;

import com.rabbitcompany.chestlock.ChestLock;
import com.rabbitcompany.chestlock.utils.Message;
import com.rabbitcompany.chestlock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    private ChestLock chestLock;

    public SignChangeListener(ChestLock plugin){
        chestLock = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e){

        if(Utils.canBeLocked(e.getBlock().getLocation()) && !(Utils.getAttachedFace(e.getBlock()) == BlockFace.UP) && !(Utils.getAttachedFace(e.getBlock()) == BlockFace.DOWN)){
            if(e.getLine(0) != null && e.getLine(1) != null && e.getLine(2) != null && e.getLine(3) != null) {
                if (e.getLine(0).equals("") && e.getLine(1).equals("") && e.getLine(2).equals("") && e.getLine(3).equals("")) {
                    e.setLine(0, Message.chat(chestLock.getConf().getString("chestlock_identifier", "[Private]")));
                    e.setLine(1, e.getPlayer().getName());
                }else if(e.getLine(0).equals(Message.chat(chestLock.getConf().getString("chestlock_identifier", "[Private]")))){
                    e.setLine(1, e.getPlayer().getName());
                }
            }
        }else{
            if (e.getLine(0).equals(Message.chat(chestLock.getConf().getString("chestlock_identifier", "[Private]")))) {
                e.setLine(0, Message.chat(chestLock.getConf().getString("chestlock_error", "&8[&4Error&8]")));
                for (int i = 1; i <= 3; i++) e.setLine(i, "");
            }
        }
    }

}
