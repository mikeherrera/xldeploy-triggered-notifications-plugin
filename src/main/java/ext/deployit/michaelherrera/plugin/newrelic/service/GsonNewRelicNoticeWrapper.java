package ext.deployit.michaelherrera.plugin.newrelic.service;

public class GsonNewRelicNoticeWrapper {
	
	private NewRelicNotice deployment;
	
	public GsonNewRelicNoticeWrapper(String app_name, String user, String description)	{
		this.deployment = new NewRelicNotice(app_name, user, description);
	}
	
	public NewRelicNotice getDeployment()	{
		return this.deployment;
	}

}
