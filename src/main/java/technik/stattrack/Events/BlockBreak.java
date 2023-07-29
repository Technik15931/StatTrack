package technik.stattrack.Events;


import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.ItemMeta;
import technik.stattrack.ColorUtil;
import technik.stattrack.StatTrack;

import java.util.ArrayList;
import java.util.List;

public class BlockBreak implements Listener {
    private final StatTrack plugin;

    public BlockBreak(StatTrack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(BlockBreakEvent e) {
        Configuration config = this.plugin.getConfig();
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE ||
                p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE ||
                p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_PICKAXE ||
                p.getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE ||
                p.getInventory().getItemInMainHand().getType() == Material.STONE_PICKAXE ||
                p.getInventory().getItemInMainHand().getType() == Material.WOODEN_PICKAXE ||

                p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_AXE ||
                p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE ||
                p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_AXE ||
                p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE ||
                p.getInventory().getItemInMainHand().getType() == Material.STONE_AXE ||
                p.getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE ||

                p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SHOVEL ||
                p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SHOVEL ||
                p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SHOVEL ||
                p.getInventory().getItemInMainHand().getType() == Material.IRON_SHOVEL ||
                p.getInventory().getItemInMainHand().getType() == Material.STONE_SHOVEL ||
                p.getInventory().getItemInMainHand().getType() == Material.WOODEN_SHOVEL) {

            if (p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                ItemMeta itemMeta = p.getInventory().getItemInMainHand().getItemMeta();
                int cmd = p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData();
                int brokenBlocks = cmd + 1;

                itemMeta.setCustomModelData(brokenBlocks);

                List<String> lore = itemMeta.getLore();
                if (lore == null) {
                    lore = new ArrayList<>();
                }

                String loreLine = ColorUtil.hexColor(config.getString("lore.mine").replaceAll("%mined%", String.valueOf(brokenBlocks)));
                if (lore.isEmpty()) {
                    lore.add(loreLine);
                } else {
                    lore.set(0, loreLine);
                }

                itemMeta.setLore(lore);
                p.getInventory().getItemInMainHand().setItemMeta(itemMeta);
            }
        }
    }
}