package dkdunnings.pop;

import java.io.File;
import java.util.List;

public class conf
{
	private static main plugin;
	static String directory = "plugins/Pearl Of Peace";
	static File file = new File(directory + "/config.yml");
	
	public conf(main instance)
	{
		plugin = instance;
	}
	
	public static void configCheck()
	{
		new File(directory).mkdir();
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (Exception ex) {
			}
	}
	
	public static void write(String root, Object x) {
		plugin.getConfig().set(root, x);
		plugin.saveConfig();
	}
	public static Boolean readBoolean(String root) {
		return plugin.getConfig().getBoolean(root, true);
	}
	
	public static Double readDouble(String root) {
		return plugin.getConfig().getDouble(root, 0.0D);
	}
	public static List<String> readStringList(String root) {
		return plugin.getConfig().getStringList(root);
	}
	public static String readString(String root) {
		return plugin.getConfig().getString(root);
	}
	
	@SuppressWarnings("unused")
	private static void load() {
		plugin.getConfig().options().copyDefaults(true);
	}
}