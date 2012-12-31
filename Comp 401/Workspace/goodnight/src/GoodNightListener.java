import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class GoodNightListener extends PluginListener {
private Logger log = Logger.getLogger("Minecraft");
private static final long ONEDAY = 24000L;
private static final long SUNSET = 12350L;
private static final long SUNRISE = 23650L;
private static long currentNight = -1L;
private static List<Player> good_nights = new ArrayList<Player>();

public boolean onCommand( Player player, String[] split) {
	if (split[0].equalsIgnoreCase( "/goodnight")) {
		long now = etc.getServer().getTime();
		long thisNight = now / ONEDAY;
		// check for night time
		long daytime = now - (thisNight * ONEDAY);
		if (daytime < SUNSET || daytime > SUNRISE) {
			player.sendMessage("It is not night time! Try again later.");
		    return true;
		}

		if (thisNight > currentNight) {
			// first /goodnight during this night cycle, clear the list of good nighters. 
			currentNight = thisNight;
			good_nights.clear();
		}

		if (good_nights.contains( player )) {
			player.sendMessage("You are already trying to get to sleep.");
		    return true;
		}

		// This command was correctly invoked. Broadcast the "Good night!" message
		
		StringBuffer msg = new StringBuffer(player.getName());
		msg.append(" says Good night");
		if (split.length > 1) {
			for (int i=1; i<split.length; i++ ) {
				msg.append( " " );
				msg.append(split[i]);
			}
		}
		msg.append( "!");
		broadcast(msg.toString());

		// Add the player to the list and check the list
		good_nights.add( player );

		List<Player> players = etc.getServer().getPlayerList();
		if (players.size() > good_nights.size()) {
			// Not everyone has said good night
			return true;
		}
		for (Player check_player : players) {
			boolean hasComplied = false;
			for (Player gn_player : good_nights) {
				if (check_player == gn_player) {
					hasComplied = true;
				    break;
				}
			}
			if (!hasComplied) {
				// this player hasn't complied yet.
				return true;
			}
		}
		// Everyone has complied! Reset the time accordingly.
		etc.getServer().setTime( (thisNight * ONEDAY) + SUNRISE );
		broadcast("Rise and Shine everybody!");
		good_nights.clear();
		return true;
	}
	return false;
}

public void broadcast(String message) {
    for (Player p : etc.getServer().getPlayerList()) {
        p.sendMessage(message);
    }
}

}
