package ext.deployit.michaelherrera.plugin.newrelic.service;

public class GsonNewRelicNoticeWrapper {
	
	private Deployment deployment;
	
	public GsonNewRelicNoticeWrapper(String app_name, String user, String description)	{
		this.deployment = new Deployment(app_name, user, description);
	}
	
	public Deployment getDeployment()	{
		return this.deployment;
	}

}
