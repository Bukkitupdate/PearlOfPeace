package dkdunnings.pop;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class delay
{
	private static Player pl;
	public static void pearlDuration(Player p)
	{
		pl = p;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				main.isInvisible.remove(delay.pl);
				delay.pl.sendMessage(ChatColor.RED + "Pearl effects have worn off.");
				main.onCooldown.put(delay.pl, new Date());
			}
		}
		, (int)main.duration);
	}
}