package me.hapily.plugins.skymining.mines;

import me.hapily.plugins.skymining.util.Selection;
import me.hapily.plugins.skymining.util.Util;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CMDMine extends BukkitCommand {

    public CMDMine(String name){
        super(name);
        this.description = "A command to register kits";
        this.usageMessage = "/mine <help|create|delete|reset> [<name>] [<items>]";
        this.setPermission("SkyMining.mine");
        this.setPermissionMessage("You don't have the permission '" + this.getPermission() + "'.");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        Player p = (Player) sender;
        if(args.length == 0) {
            helpMSG(p);
            return true;
        }
        String subcmd = args[0];
        if(subcmd.equalsIgnoreCase("create")){
            if(args.length < 3){
                p.sendMessage(createMSG());
            }
            else{
                if(Mines.getMine(args[1]) == null){
                    if(Selection.loc1.containsKey(p) && Selection.loc2.containsKey(p)){
                        new Mine(Util.itemsConvert(args[3]), args[2], Selection.loc1.get(p), Selection.loc2.get(p));
                        p.sendMessage("§2§lSuccess! §aMine " + args[2] + " §ahas been created!");
                    }
                    else{
                        p.sendMessage("§4ERROR: §cBoth of your selections are not set!");
                    }
                }
                else{
                    p.sendMessage(mineExists(true));
                }
            }
        }
        else if(subcmd.equalsIgnoreCase("delete")){
            if(args.length < 2){
                p.sendMessage(deleteMSG());
            }
        }
        else if(subcmd.equalsIgnoreCase("reset")){
            if(args.length < 2){
                p.sendMessage(resetMSG());
            }
            else{
                if(Mines.getMine(args[1]) != null){
                    Mines.getMine(args[1]).reset();
                    p.sendMessage("§2§lSuccess! §aMine " + args[2] + " §ahas been reset!");
                }
                else{
                    p.sendMessage(mineExists(false));
                }
            }
        }
        else if(subcmd.equalsIgnoreCase("tool")){
            ItemStack item = new ItemStack(Material.BEDROCK);
            item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
            item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.getItemMeta().setDisplayName("§5§lMines Selection Tool");
            p.getInventory().addItem(item);
            p.sendMessage("§5Mines tool given! §dLeft click: select pos #1; Right click: select pos #2");
        }
        else{
            helpMSG(p);
        }
        return true;
    }

    public void helpMSG(Player player){
        player.sendMessage("\n§5§lMines Help\n" +
                "\n" +
                "§5/mine help §f- §7Prints this help message.\n" +
                createMSG() + "\n" +
                deleteMSG() + "\n" +
                resetMSG() + "\n" +
                "§5/mine tool §f- §7Gives you the selection tool.\n");
    }

    public String createMSG(){
        return "§5/mine create <name> <items> §f- §7Creates a mine with the given items.\n" +
                "§5§nItems Example: §d'dirt:20,coal_ore:80' §f- §7Dirt 20% chance and Coal Ore with 80% chance.";
    }

    public String deleteMSG(){
        return "§5/mine delete <name> §f- §7Deletes the mine with the given name.";
    }

    public String resetMSG(){
        return "§5/mine reset <name> §f- §7Resets the mine with the given name.";
    }

    public String mineExists(boolean bool){
        if(bool){
            return "§4ERROR: §cThe mine with the given name already exists!";
        }
        else{
            return "§4ERROR: §cThe mine with the given name doesn't exist!";
        }
    }
}
