package technik.stattrack.Events;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import technik.stattrack.ColorUtil;
import technik.stattrack.StatTrack;

import java.util.ArrayList;
import java.util.List;

public class EntityKilled implements Listener {
    private final StatTrack plugin;

    public EntityKilled(StatTrack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(EntityDeathEvent e) {
        Configuration config = this.plugin.getConfig();
        Player p = e.getEntity().getKiller();
        LivingEntity entity = e.getEntity();
        if (p instanceof Player) {
            if (p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD ||
                    p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD ||
                    p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD ||
                    p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD ||
                    p.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD ||
                    p.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
                if (p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    ItemMeta itemMeta = p.getInventory().getItemInMainHand().getItemMeta();
                    int cmd = p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData();
                    int kills = cmd + 1;

                    itemMeta.setCustomModelData(kills);

                    List<String> lore = itemMeta.getLore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    String loreLine = ColorUtil.hexColor(config.getString("lore.kill").replaceAll("%killed%", String.valueOf(kills)));
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
}