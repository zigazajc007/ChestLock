package com.rabbitcompany.chestlock.utils;

import com.rabbitcompany.chestlock.ChestLock;
import org.bukkit.ChatColor;

import java.util.UUID;

public class Message {

    public static String chat(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String getMessage(UUID uuid, String config){
        String mess;
        String lang = Utils.language.getOrDefault(uuid, ChestLock.getInstance().getConf().getString("default_language"));
        switch (lang){
            default:
                mess = ChestLock.getInstance().getEngl().getString(config);
                break;
        }
        if(mess != null){
            return chat(mess);
        }else{
            return chat("&cValue: &6" + config + "&c is missing in " + lang + ".yml file! Please add it or delete " + lang + ".yml file.");
        }
    }
}
