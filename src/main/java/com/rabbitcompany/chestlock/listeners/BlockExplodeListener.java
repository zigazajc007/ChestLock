package com.rabbitcompany.chestlock.listeners;

import com.rabbitcompany.chestlock.ChestLock;
import com.rabbitcompany.chestlock.utils.Message;
import com.rabbitcompany.chestlock.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockExplodeListener implements Listener {

    private ChestLock chestLock;

    public BlockExplodeListener(ChestLock plugin){
        chestLock = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent e){

        for (Block block : e.blockList().toArray(new Block[e.blockList().size()])){
            if(Utils.canLockMaterial(block.getType())){
                if(Utils.isBlockLocked(block.getLocation())) e.blockList().remove(block);
            }else if(block.getState() instanceof Sign){
                Sign sign = (Sign) block.getState();
                if(sign.getLine(0).equals(Message.chat(chestLock.getConf().getString("chestlock_identifier", "[Private]")))) e.blockList().remove(block);
            }
        }
    }
}
