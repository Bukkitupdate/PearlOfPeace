package dkdunnings.pop;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin
{
  public static double duration;
  public static double cooldown;
  public static boolean consumepearl;
  public static HashSet<Player> isInvisible = new HashSet<Player>();
  public static HashMap<Player, Date> onCooldown = new HashMap<Player, Date>();
  entityListener entityListener = new entityListener(this);
  playerListener playerListener = new playerListener(this);

  public void onEnable() {
    conf.configCheck();
    if (conf.readDouble("duration").doubleValue() == 0.0D) {
      conf.write("duration", Integer.valueOf(30000));
      duration = 30000.0D;
    }
    else {
      duration = conf.readDouble("duration").doubleValue();
    }
    if (conf.readDouble("cooldown").doubleValue() == 0.0D) {
      conf.write("cooldown", Integer.valueOf(60000));
      cooldown = 60000.0D;
    }
    else {
      cooldown = conf.readDouble("cooldown").doubleValue();
    }
    if (conf.readBoolean("consumepearl").booleanValue()) {
      conf.write("consumepearl", Boolean.valueOf(true));
      consumepearl = true;
    }
    else {
      consumepearl = conf.readBoolean("consumepearl").booleanValue();
    }
    getServer().getPluginManager().registerEvents(this.playerListener, this);
    getServer().getPluginManager().registerEvents(this.entityListener, this);
    System.out.print("Pearl of Peace v1.7 enabled...");
  }

  public void onDisable()
  {
    System.out.print("Pearl of Peace v1.7 disabled...");
  }
}