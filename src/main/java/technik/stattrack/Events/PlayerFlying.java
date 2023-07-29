package technik.stattrack.Events;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.ItemMeta;
import technik.stattrack.ColorUtil;
import technik.stattrack.StatTrack;

import java.util.ArrayList;
import java.util.List;

public class PlayerFlying implements Listener {
    private final StatTrack plugin;

    public PlayerFlying(StatTrack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEvent(PlayerMoveEvent e) {
        Configuration config = this.plugin.getConfig();
        Player p = e.getPlayer();
        if (p.isGliding()) {
            if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
                if (p.getInventory().getChestplate().getType() == Material.ELYTRA) {
                    if (p.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                        ItemMeta itemMeta = p.getInventory().getChestplate().getItemMeta();
                        int cmd = p.getInventory().getChestplate().getItemMeta().getCustomModelData();
                        int distanceflown = cmd + 1;

                        itemMeta.setCustomModelData(distanceflown);

                        List<String> lore = itemMeta.getLore();
                        if (lore == null) {
                            lore = new ArrayList<>();
                        }

                        String loreLine = ColorUtil.hexColor(config.getString("lore.fly").replaceAll("%flown%", String.valueOf(distanceflown)));
                        if (lore.isEmpty()) {
                            lore.add(loreLine);
                        } else {
                            lore.set(0, loreLine);
                        }

                        itemMeta.setLore(lore);
                        p.getInventory().getChestplate().setItemMeta(itemMeta);
                    }
                }
            }
        }
    }
}
