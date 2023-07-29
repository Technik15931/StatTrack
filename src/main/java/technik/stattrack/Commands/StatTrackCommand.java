package technik.stattrack.Commands;

import com.google.common.base.Strings;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import technik.stattrack.ColorUtil;
import technik.stattrack.StatTrack;

import java.util.ArrayList;
import java.util.List;

public class StatTrackCommand implements CommandExecutor {
    private final StatTrack plugin;

    public StatTrackCommand(StatTrack plugin) {
        this.plugin = plugin;
    }

    public ItemStack StatGive(int count) {

        ItemStack itemToGive = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = itemToGive.getItemMeta();
        itemToGive.setAmount(count);
        itemMeta.setCustomModelData(2137);
        itemMeta.setDisplayName(ColorUtil.hexColor(this.plugin.getConfig().getString("names.stattrack-item-name")));
        List<String> lore = new ArrayList<>();
        for(String s : this.plugin.getConfig().getStringList("lore.stattrack-item-lore")){
            lore.add(ColorUtil.hexColor(s));
        }
        itemMeta.setLore(lore);
        itemToGive.setItemMeta(itemMeta);
        return itemToGive;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(cmd.getName().equalsIgnoreCase("stattrack")) {
            if (sender instanceof Player p) {
                if (args.length < 1) {
                    List<String> list = new ArrayList<>();
                    for (String s : this.plugin.getConfig().getStringList("messages.no-arguments-provided")) {
                        list.add(ColorUtil.hexColor(s));
                        p.sendMessage(ColorUtil.hexColor(s));
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("give")) {
                            int count = Integer.parseInt(args[1]);
                            p.getInventory().addItem(StatGive(count));
                    }
                    if (args[0].equalsIgnoreCase("config")) {
                        if (args[1].equalsIgnoreCase("reload")) {
                            this.plugin.reloadConfig();
                            p.sendMessage(ColorUtil.hexColor(this.plugin.getConfig().getString("messages.config-successfull-reload")));
                        }
                    }
                }
            }
        }
        return false;
    }
}
