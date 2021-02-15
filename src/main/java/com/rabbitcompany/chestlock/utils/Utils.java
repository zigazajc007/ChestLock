package com.rabbitcompany.chestlock.utils;

import com.rabbitcompany.chestlock.ChestLock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.material.Attachable;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class Utils {

    //Language
    public static HashMap<UUID, String> language = new HashMap<>();

    public static boolean isBlockLocked(Location chest_loc){
        if(chest_loc.getBlock().getRelative(BlockFace.EAST).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.EAST).getState();
            if(getAttachedFace(sign.getBlock()) == BlockFace.WEST)
                if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return true;
        }
        if(chest_loc.getBlock().getRelative(BlockFace.WEST).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.WEST).getState();
            if(getAttachedFace(sign.getBlock()) == BlockFace.EAST)
                if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return true;
        }
        if(chest_loc.getBlock().getRelative(BlockFace.SOUTH).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.SOUTH).getState();
            if(getAttachedFace(sign.getBlock()) == BlockFace.NORTH)
                if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return true;
        }
        if(chest_loc.getBlock().getRelative(BlockFace.NORTH).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.NORTH).getState();
            if(getAttachedFace(sign.getBlock()) == BlockFace.SOUTH)
                if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return true;
        }
        return false;
    }

    public static boolean canOpenBlock(String name, Location chest_loc){
        if(chest_loc.getBlock().getRelative(BlockFace.EAST).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.EAST).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))){
                for(int i = 1; i <= 3; i++)
                    if(sign.getLine(i).equals(name)) return true;
            }
        }
        if(chest_loc.getBlock().getRelative(BlockFace.WEST).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.WEST).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))){
                for(int i = 1; i <= 3; i++)
                    if(sign.getLine(i).equals(name)) return true;
            }
        }
        if(chest_loc.getBlock().getRelative(BlockFace.SOUTH).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.SOUTH).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))){
                for(int i = 1; i <= 3; i++)
                    if(sign.getLine(i).equals(name)) return true;
            }
        }
        if(chest_loc.getBlock().getRelative(BlockFace.NORTH).getState() instanceof Sign){
            Sign sign = (Sign) chest_loc.getBlock().getRelative(BlockFace.NORTH).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))){
                for(int i = 1; i <= 3; i++)
                    if(sign.getLine(i).equals(name)) return true;
            }
        }
        return false;
    }

    public static boolean canBeLocked(Location sign_loc){
        Block block = getAttachedBlock(sign_loc.getBlock());

        if(!canLockMaterial(block.getType())) return false;

        if(block.getRelative(BlockFace.EAST).getState() instanceof Sign && getAttachedFace(sign_loc.getBlock()) != BlockFace.EAST){
            Sign sign = (Sign) block.getRelative(BlockFace.EAST).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return false;
        }

        if(block.getRelative(BlockFace.WEST).getState() instanceof Sign && getAttachedFace(sign_loc.getBlock()) != BlockFace.WEST){
            Sign sign = (Sign) block.getRelative(BlockFace.WEST).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return false;
        }

        if(block.getRelative(BlockFace.SOUTH).getState() instanceof Sign && getAttachedFace(sign_loc.getBlock()) != BlockFace.SOUTH){
            Sign sign = (Sign) block.getRelative(BlockFace.SOUTH).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return false;
        }

        if(block.getRelative(BlockFace.NORTH).getState() instanceof Sign && getAttachedFace(sign_loc.getBlock()) != BlockFace.NORTH){
            Sign sign = (Sign) block.getRelative(BlockFace.NORTH).getState();
            if(sign.getLine(0).equals(Message.chat(ChestLock.getInstance().getConf().getString("chestlock_identifier", "[Private]")))) return false;
        }

        return true;
    }

    public static boolean canLockMaterial(Material material){
        return ChestLock.getInstance().getConf().getList("locked_blocks", Arrays.asList("CHEST", "FURNACE", "ENDER_CHEST")).contains(material.name());
    }

    public static Block getAttachedBlock(Block b) {

        try {
            Class.forName("org.bukkit.block.data.BlockData");
            BlockData blockData = b.getState().getBlockData();
            BlockFace face = BlockFace.DOWN;
            if(blockData instanceof WallSign)
                face = ((WallSign) blockData).getFacing().getOppositeFace();
            return  b.getRelative(face);
        } catch (ClassNotFoundException ignored) {
            MaterialData materialData = b.getState().getData();
            BlockFace face = BlockFace.DOWN;
            if (materialData instanceof Attachable) {
                face = ((Attachable) materialData).getAttachedFace();
            }
            if(face == null) face = BlockFace.SOUTH;
            return b.getRelative(face);
        }

    }

    public static BlockFace getAttachedFace(Block b){

        try {
            Class.forName("org.bukkit.block.data.BlockData");
            BlockData blockData = b.getState().getBlockData();
            BlockFace face = BlockFace.DOWN;
            if(blockData instanceof WallSign)
                face = ((WallSign) blockData).getFacing().getOppositeFace();
            return face;
        } catch (ClassNotFoundException ignored) {
            MaterialData materialData = b.getState().getData();
            BlockFace face = BlockFace.DOWN;
            if (materialData instanceof Attachable) {
                face = ((Attachable) materialData).getAttachedFace();
            }
            if(face == null) face = BlockFace.SOUTH;
            return face;
        }

    }
}
