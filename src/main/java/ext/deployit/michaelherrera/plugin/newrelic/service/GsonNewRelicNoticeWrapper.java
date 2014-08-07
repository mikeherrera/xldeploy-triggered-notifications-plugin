package ext.deployit.michaelherrera.plugin.newrelic.service;

public class GsonNewRelicNoticeWrapper {
	
	private NewRelicNotice newRelicNotice;
	
	public GsonNewRelicNoticeWrapper(String app_name, String user, String description)	{
		this.newRelicNotice = new NewRelicNotice(app_name, user, description);
	}
	
	public NewRelicNotice getNewRelicNotice()	{
		return this.newRelicNotice;
	}

}
