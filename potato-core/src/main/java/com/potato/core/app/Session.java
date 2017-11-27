package com.potato.core.app;

import com.potato.core.service.SessionStorage;

public interface Session
{
	/**
	 * session status types
	 */
	enum Status
	{
		NOT_CONNECTED, CONNECTING, CONNECTED, CLOSED
	}

	String getId();


	void setAttribute(String key, Object value);

	Object getAttribute(String key);

	void removeAttribute(String key);

	long getCreationTime();

	long getLastUpdateTime();

	void setStatus(Status status);

	Status getStatus();

	//判断状态是否是连接中
	boolean isConnected();

	//判断是否存在
	boolean exists();

	//判断是否可用
	boolean isActive();

	void destory();


	void setSessionStorage(SessionStorage storage);

}
