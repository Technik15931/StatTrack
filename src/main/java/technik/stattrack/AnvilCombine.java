package technik.stattrack;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AnvilCombine implements Listener {
    private final StatTrack plugin;

    public AnvilCombine(StatTrack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(PrepareAnvilEvent e) {
        ItemStack left = e.getInventory().getItem(0);
        ItemStack right = e.getInventory().getItem(1);


    if (left != null && right != null) {
        if (!left.getItemMeta().hasCustomModelData()) {
            if (right.getItemMeta().hasCustomModelData()) {
                if (right.getItemMeta().getCustomModelData() == 2137) {
                    if (right.getAmount() == 1) {
                        if (right.getItemMeta().getDisplayName().equals(ColorUtil.hexColor(this.plugin.getConfig().getString("names.stattrack-item-name")))){
                            if (left.getType().equals(Material.NETHERITE_PICKAXE) ||
                                    left.getType().equals(Material.DIAMOND_PICKAXE) ||
                                    left.getType().equals(Material.GOLDEN_PICKAXE) ||
                                    left.getType().equals(Material.IRON_PICKAXE) ||
                                    left.getType().equals(Material.STONE_PICKAXE) ||
                                    left.getType().equals(Material.WOODEN_PICKAXE) ||

                                    left.getType().equals(Material.NETHERITE_AXE) ||
                                    left.getType().equals(Material.DIAMOND_AXE) ||
                                    left.getType().equals(Material.GOLDEN_AXE) ||
                                    left.getType().equals(Material.IRON_AXE) ||
                                    left.getType().equals(Material.STONE_AXE) ||
                                    left.getType().equals(Material.WOODEN_AXE) ||

                                    left.getType().equals(Material.NETHERITE_SHOVEL) ||
                                    left.getType().equals(Material.DIAMOND_SHOVEL) ||
                                    left.getType().equals(Material.GOLDEN_SHOVEL) ||
                                    left.getType().equals(Material.IRON_SHOVEL) ||
                                    left.getType().equals(Material.STONE_SHOVEL) ||
                                    left.getType().equals(Material.WOODEN_SHOVEL)) {
                                ItemStack result = new ItemStack(left);
                                ItemMeta itemMeta = result.getItemMeta();
                                //itemMeta.setDisplayName("Pickaxe with StatTrack");
                                List<String> lore = new ArrayList<>();
                                lore.add(ColorUtil.hexColor(ColorUtil.hexColor(this.plugin.getConfig().getString("lore.mine").replaceAll("%mined%", "0"))));
                                itemMeta.setLore(lore);
                                itemMeta.setCustomModelData(0);
                                result.setItemMeta(itemMeta);
                                e.getInventory().setRepairCost(5);
                                e.setResult(result);
                                }
                            if (left.getType().equals(Material.NETHERITE_SWORD) ||
                                    left.getType().equals(Material.DIAMOND_SWORD) ||
                                    left.getType().equals(Material.GOLDEN_SWORD) ||
                                    left.getType().equals(Material.IRON_SWORD) ||
                                    left.getType().equals(Material.STONE_SWORD) ||
                                    left.getType().equals(Material.WOODEN_SWORD)) {
                                ItemStack result = new ItemStack(left);
                                ItemMeta itemMeta = result.getItemMeta();
                                //itemMeta.setDisplayName("Sword with StatTrack");
                                List<String> lore = new ArrayList<>();
                                lore.add(ColorUtil.hexColor(ColorUtil.hexColor(this.plugin.getConfig().getString("lore.kill").replaceAll("%killed%", "0"))));
                                itemMeta.setLore(lore);
                                itemMeta.setCustomModelData(0);
                                result.setItemMeta(itemMeta);
                                e.getInventory().setRepairCost(5);
                                e.setResult(result);
                                }
                            if (left.getType().equals(Material.ELYTRA)) {
                                ItemStack result = new ItemStack(left);
                                ItemMeta itemMeta = result.getItemMeta();
                                //itemMeta.setDisplayName("Elytra with StatTrack");
                                List<String> lore = new ArrayList<>();
                                lore.add(ColorUtil.hexColor(ColorUtil.hexColor(this.plugin.getConfig().getString("lore.fly").replaceAll("%flown%", "0"))));
                                itemMeta.setLore(lore);
                                itemMeta.setCustomModelData(0);
                                result.setItemMeta(itemMeta);
                                e.getInventory().setRepairCost(5);
                                e.setResult(result);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
