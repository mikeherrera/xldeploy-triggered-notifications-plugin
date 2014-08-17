package ext.deployit.michaelherrera.plugin.newrelic.notification;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

import com.xebialabs.deployit.plugin.api.udm.Metadata;
import com.xebialabs.deployit.plugin.api.udm.Property;
import com.xebialabs.deployit.plugin.api.udm.base.BaseConfigurationItem;
import com.xebialabs.deployit.plugin.trigger.Action;

import ext.deployit.michaelherrera.plugin.newrelic.service.Deployment;
import ext.deployit.michaelherrera.plugin.newrelic.service.NewRelicService;
import ext.deployit.michaelherrera.plugin.util.TemplateParser;

@SuppressWarnings("serial")
@Metadata(root = Metadata.ConfigurationItemRoot.CONFIGURATION, description = "NewRelic Notification configuration.")
public class NewRelicNotification extends BaseConfigurationItem implements Action {
	
	private static final Logger logger = LoggerFactory.getLogger(NewRelicNotification.class);
	private static final String MESSAGE_FROM = "XL Deploy";
	
	@Property(description = "The application name as configured within New Relic.", label = "Application Name")
	private String app_name;
	
	@Property(defaultValue = "https://api.newrelic.com", description = "The host NewRelic API request.")
    private String newRelicApiHost;
	
	@Property(description = "The api key used for the NewRelic API request.")
    private String apiKey;
	
	@Property(description = "The text annotation for the deployment, notes for you.", size = Property.Size.LARGE)
    private String description;
	
	public NewRelicNotification()	{}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Map context) {
		String notificationMessage = TemplateParser.parseTemplatedMessage(description, context);
		logger.info("notificationMessage: " + notificationMessage);
		
		Deployment newRelicNotice = new Deployment(app_name, MESSAGE_FROM, description);
		
		RequestInterceptor requestInterceptor = new RequestInterceptor() {
			  @Override
			  public void intercept(RequestFacade request) {
			    request.addHeader("x-api-key", apiKey);
			  }
			};
		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setLogLevel(RestAdapter.LogLevel.FULL)
			.setEndpoint(newRelicApiHost)
			.setConverter(new SimpleXMLConverter())
			.setRequestInterceptor(requestInterceptor)
			.build();
		
		NewRelicService newRelicService = restAdapter.create(NewRelicService.class);
		newRelicService.sendNewRelicNotice(newRelicNotice);
	}
}