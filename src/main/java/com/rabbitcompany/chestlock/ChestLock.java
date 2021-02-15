package com.rabbitcompany.chestlock;

import com.rabbitcompany.chestlock.listeners.*;
import com.rabbitcompany.chestlock.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class ChestLock extends JavaPlugin {

    private static ChestLock instance;

    //Config
    private File co = null;
    private YamlConfiguration conf = new YamlConfiguration();

    //English
    private File en = null;
    private YamlConfiguration engl = new YamlConfiguration();

    @Override
    public void onEnable() {
        instance = this;
        this.co = new File(getDataFolder(), "config.yml");
        this.en = new File(getDataFolder(), "Languages/English.yml");

        mkdir();
        loadYamls();

        info("&aEnabling");

        //Listeners
        new BlockBreakListener(this);
        new BlockPlaceListener(this);
        new BlockExplodeListener(this);
        new SignChangeListener(this);
        new PlayerInteractiveListener(this);
    }

    @Override
    public void onDisable() {
        info("&4Disabling");
    }

    private void mkdir(){

        if(!this.co.exists()){
            saveResource("config.yml", false);
        }

        if (!this.en.exists()) {
            saveResource("Languages/English.yml", false);
        }

    }

    public void loadYamls(){

        try{
            this.conf.load(this.co);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        try {
            this.engl.load(this.en);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConf() { return this.conf; }
    public YamlConfiguration getEngl() { return this.engl; }

    private void info(String message){
        Bukkit.getConsoleSender().sendMessage(Message.chat(""));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8[]=====[" + message + " &bChestLock&8]=====[]"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8| &cInformation:"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|   &9Name: &bChestLock"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|   &9Developer: &bBlack1_TV"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|   &9Version: &b" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8| &cSupport:"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|   &9Discord: &bCrazy Rabbit#0001"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|   &9Mail: &bziga.zajc007@gmail.com"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|   &9Discord: &bhttps://discord.gg/hUNymXX"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8|"));
        Bukkit.getConsoleSender().sendMessage(Message.chat("&8[]=====================================[]"));
        Bukkit.getConsoleSender().sendMessage(Message.chat(""));
    }

    public static ChestLock getInstance(){ return instance; }
}
