package com.potato.core.app;


import com.potato.common.common.Player;

/**
 * for communication.
 * 
 * @author Abraham Menacherry
 * 
 */
public interface PlayerSession extends Session
{
	/**
	 * Each session is associated with a {@link Player}. This is the actual
	 * human or machine using this session.
	 * 
	 * @return Returns that associated Player object or null if it is not
	 *         associated yet.
	 */
	Player getPlayer();


	PlayerSession createNewSession(Player player);

}
