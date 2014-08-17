package ext.deployit.michaelherrera.plugin.newrelic.service;

public class Deployment {

	private String app_name;
	private String application_id;
	private String revision;
	private String changelog;
	private String user;
	private String description;
	private String timestamp;
	
	public Deployment(String app_name, String user, String description)	{
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

	public String getApplication_id() {
		return application_id;
	}

	public String getRevision() {
		return revision;
	}

	public String getChangelog() {
		return changelog;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setChangelog(String changelog) {
		this.changelog = changelog;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}