package ext.deployit.michaelherrera.plugin.newrelic.service;

import retrofit.http.Body;
import retrofit.http.POST;

public interface NewRelicService {
	
	@POST("/deployments.xml")
	String sendNewRelicNotice(@Body Deployment newRelicNotice);
	
}
