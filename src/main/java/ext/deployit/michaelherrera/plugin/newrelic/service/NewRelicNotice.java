package ext.deployit.michaelherrera.plugin.newrelic.service;

public class NewRelicNotice {

	private String app_name;
	private String user;
	private String description;
	
	public NewRelicNotice(String app_name, String user, String description)	{
		this.app_name = app_name;
		this.user = user;
		this.description = description;
	}
	
	public String getApp_name() {
		return app_name;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getDescription() {
		return description;
	}
}
