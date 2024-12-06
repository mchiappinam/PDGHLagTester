package me.mchiappinam.pdghlagtester;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;

public class Main extends JavaPlugin implements Listener {
	
	int tdelayArenaTimeLimit;
	boolean ativado = false;
	
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
	
	getServer().getConsoleSender().sendMessage("§3[PDGHLagTester] §2ativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHLagTester] §2Acesse: http://pdgh.com.br/");
  }

  public void onDisable() {
	getServer().getConsoleSender().sendMessage("§3[PDGHLagTester] §2desativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHLagTester] §2Acesse: http://pdgh.com.br/");
  }
  
  


@EventHandler
  public void onPlayerInteract(PlayerInteractEvent e) {
    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
      Block b = e.getClickedBlock();
      if ((b.getType() == Material.OAK_SIGN) || (b.getType() == Material.OAK_WALL_SIGN)) {
        Sign s = (Sign)b.getState();
        if ((s.getLine(0).equalsIgnoreCase("[PDGHLagTester]"))) {
        	if(ativado) {
        		ativado=false;
        		getServer().broadcastMessage("§3§l[PDGHLagTester] §aOFF");
        		cdelayArenaTimeLimit();
        	} else {
        		ativado=true;
        		getServer().broadcastMessage("§3§l[PDGHLagTester] §aON");
        		delayArenaTimeLimit();
        	}
        }
      }
    }
  }
  

  
  


  private void delayArenaTimeLimit() {
	  	  tdelayArenaTimeLimit = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	    		public void run() {
    				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
    		        	all.playEffect(all.getLocation(), Effect.ENDER_DRAGON_DESTROY_BLOCK, 3);
    				}
	    		}
	  	  }, 0, 0);
	}

	private void cdelayArenaTimeLimit() {
		Bukkit.getScheduler().cancelTask(tdelayArenaTimeLimit);
	}
  
  
  
  
  
}