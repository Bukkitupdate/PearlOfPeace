package dkdunnings.pop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class entityListener implements Listener
{
	@SuppressWarnings("unused")
	private final main plugin;
	
	public entityListener(main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityTarget(EntityTargetEvent event)
	{
		if ((event.getTarget() instanceof Player)) {
			Player p = (Player)event.getTarget();
			if (main.isInvisible.contains(p))
				event.setCancelled(true);
		}
	}
}