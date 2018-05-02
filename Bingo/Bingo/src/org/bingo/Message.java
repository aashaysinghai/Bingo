package org.bingo;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String playerId;
	private String message;
	private Object obj;
	
	public Message(String message, Object obj) {
		super();
		this.message = message;
		this.obj = obj;
	}
	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", obj=" + obj + "]";
	}
	

}
