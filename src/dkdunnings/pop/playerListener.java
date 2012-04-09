package dkdunnings.pop;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class playerListener implements Listener
{
	@SuppressWarnings("unused")
	private final main plugin;
	
	public playerListener(main plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) | event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			Player p = event.getPlayer();
			if (event.getPlayer().getItemInHand().getTypeId() == 368) {
				if (!p.hasPermission("pearl.use")) return;
				if (main.onCooldown.containsKey(p)) {
					double timedif = new Date().getTime() - ((Date)main.onCooldown.get(p)).getTime();
					if (timedif >= main.cooldown) {
						main.onCooldown.remove(p);
					}
					else {
						int timerem = (int)main.cooldown - (int)timedif;
						p.sendMessage(ChatColor.RED + "Cooldown, time remaining: " + timerem / 1000 + " seconds.");
						return;
					}
				}
				if (main.isInvisible.contains(p)) {
					p.sendMessage(ChatColor.RED + "You are already under the protection of the pearl.");
					return;
				}
				main.isInvisible.add(p);
				List<Entity> entList = p.getNearbyEntities(17.0D, 17.0D, 17.0D);
				Iterator<Entity> iter = entList.iterator();
				while (iter.hasNext()) {
					Entity ent = (Entity)iter.next();
					Location loc = ent.getLocation();
					if ((ent instanceof Zombie)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.ZOMBIE);
					}
					else if ((ent instanceof Spider)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.SPIDER);
					}
					else if ((ent instanceof Ghast)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.GHAST);
					}
					else if ((ent instanceof CaveSpider)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.CAVE_SPIDER);
					}
					else if ((ent instanceof Creeper)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.CREEPER);
					}
					else if ((ent instanceof Enderman)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.ENDERMAN);
					}
					else if ((ent instanceof Skeleton)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.SKELETON);
					}
					else if ((ent instanceof PigZombie)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.PIG_ZOMBIE);
					}
					else if ((ent instanceof Wolf)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.WOLF);
					}
					else if ((ent instanceof Silverfish)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.SILVERFISH);
					}
					else if ((ent instanceof Slime)) {
						ent.remove();
						loc.getWorld().spawnCreature(loc, EntityType.SLIME);
					}
					else if ((ent instanceof Wolf)) {
						Wolf wolf = (Wolf)ent;
						
						if (wolf.isTamed()) {
							wolf.setSitting(true);
							AnimalTamer p2 = wolf.getOwner();
							wolf.setAngry(false);
							wolf.setTarget(null);
							wolf.setOwner(p2);
						}
						else {
							wolf.setAngry(false);
							wolf.setTarget(null);
						}
					}
					
				}
				
				p.sendMessage(ChatColor.DARK_AQUA + "Pearl of peace casts an aura of protection.");
				delay.pearlDuration(p);
				
				if (main.consumepearl) {
					ItemStack item = p.getItemInHand();
					if (item.getAmount() > 1) {
						ItemStack newItem = item;
						newItem.setAmount(item.getAmount() - 1);
						event.getPlayer().setItemInHand(newItem);
					}
					else {
						p.getInventory().remove(p.getItemInHand());
					}
				}
			}
		}
	}
}