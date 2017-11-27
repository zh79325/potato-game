package com.potato.server.communication;

/**
 * This interface declares method for sending a message to client. Different
 * implementations would be used by the server for sending based on the delivery
 * guaranty that is required.
 * 
 * @author Abraham Menacherry
 * 
 */
public interface MessageSender
{
	/**
	 * This method delegates to the underlying native session object to send a
	 * message to the client.
	 * 
	 * @param message
	 *            The message to be sent to client.
	 * @return The boolean or future associated with this operation if
	 *         synchronous or asynchronous implementation respectively.
	 */
	public Object sendMessage(Object message);

	
	/**
	 * Since message sender would have a network connection, it would require
	 * some cleanup. This method can be overriden to close underlying channels
	 * and so on.
	 */
	public void close();
	


}
