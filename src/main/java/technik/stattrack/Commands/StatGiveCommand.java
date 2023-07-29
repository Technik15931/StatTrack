package technik.stattrack.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import technik.stattrack.ColorUtil;
import technik.stattrack.StatTrack;

import java.util.ArrayList;
import java.util.List;

public class StatGiveCommand implements CommandExecutor {

    private final StatTrack plugin;

    public StatGiveCommand(StatTrack plugin) {
        this.plugin = plugin;
    }

    public ItemStack StatGive(int count, int cmd, String item) {
        //item
        ItemStack itemToGive = new ItemStack(Material.valueOf(item.toUpperCase()));
        //
        ItemMeta itemMeta = itemToGive.getItemMeta();
        //item count
        itemToGive.setAmount(count);
        //Item stat-track value
        itemMeta.setCustomModelData(cmd);
        //Item stat-track value lore
        List<String> lore = new ArrayList<>();


        //Pickaxes
        if (item.equalsIgnoreCase(Material.NETHERITE_PICKAXE.toString()) ||
                item.equalsIgnoreCase(Material.DIAMOND_PICKAXE.toString()) ||
                item.equalsIgnoreCase(Material.GOLDEN_PICKAXE.toString()) ||
                item.equalsIgnoreCase(Material.IRON_PICKAXE.toString()) ||
                item.equalsIgnoreCase(Material.STONE_PICKAXE.toString()) ||
                item.equalsIgnoreCase(Material.WOODEN_PICKAXE.toString())){
            lore.add(ColorUtil.hexColor(this.plugin.getConfig().getString("lore.mine")) + cmd);
        }

        //Elytra
        if(item.equalsIgnoreCase(Material.ELYTRA.toString())){
            lore.add(ColorUtil.hexColor(this.plugin.getConfig().getString("lore.fly")) + cmd);
        }

        //Swords
        if (item.equalsIgnoreCase(Material.NETHERITE_SWORD.toString()) ||
                item.equalsIgnoreCase(Material.DIAMOND_SWORD.toString()) ||
                item.equalsIgnoreCase(Material.GOLDEN_SWORD.toString()) ||
                item.equalsIgnoreCase(Material.IRON_SWORD.toString()) ||
                item.equalsIgnoreCase(Material.STONE_SWORD.toString()) ||
                item.equalsIgnoreCase(Material.WOODEN_SWORD.toString())){
            lore.add(ColorUtil.hexColor(this.plugin.getConfig().getString("lore.kill")) + cmd);
        }

        itemMeta.setLore(lore);
        itemToGive.setItemMeta(itemMeta);

        return itemToGive;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        Configuration config = this.plugin.getConfig();
        if (sender instanceof Player p) {
            if (cmd.getName().equalsIgnoreCase("stattrackitemgive")) {
                if (args.length < 3) {
                    p.sendMessage("3 args needed");
                    return true;
                }

                String item = String.valueOf(args[0]);
                int count = Integer.parseInt(args[1]);
                int cmdValue = Integer.parseInt(args[2]);

                // Debug messages
                System.out.println("Player: " + p.getName());
                System.out.println("Count: " + count);
                System.out.println("Cmd: " + cmdValue);
                System.out.println("Item: " + item);

                p.getInventory().addItem(StatGive(count, cmdValue, item));
                return true;
            }
        }

        return false;
    }
}
