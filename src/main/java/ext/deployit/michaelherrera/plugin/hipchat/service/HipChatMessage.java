package ext.deployit.michaelherrera.plugin.hipchat.service;

public class HipChatMessage {

	private String from;
	private boolean notify;
	private String messageFormat = "html";
	private String message;
	private String color;
	
	public HipChatMessage(String from, boolean notify, String message, String color)	{
		this.from = from;
		this.notify = notify;
		this.message = message;
		this.color = (color == null || color.equals("")) ? "yellow" : color;
	}

	public String getFrom() {
		return from;
	}

	public boolean isNotify() {
		return notify;
	}

	public String getMessageFormat() {
		return messageFormat;
	}

	public String getMessage() {
		return message;
	}

	public String getColor() {
		return color;
	}
}
