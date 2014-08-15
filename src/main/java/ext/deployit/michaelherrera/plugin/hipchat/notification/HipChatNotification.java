package ext.deployit.michaelherrera.plugin.hipchat.notification;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.xebialabs.deployit.plugin.api.udm.Metadata;
import com.xebialabs.deployit.plugin.api.udm.Property;
import com.xebialabs.deployit.plugin.api.udm.base.BaseConfigurationItem;
import com.xebialabs.deployit.plugin.trigger.Action;

import ext.deployit.michaelherrera.plugin.hipchat.service.HipChatMessageStatus;
import ext.deployit.michaelherrera.plugin.hipchat.service.HipChatService;
import ext.deployit.michaelherrera.plugin.util.TemplateParser;

@SuppressWarnings("serial")
@Metadata(root = Metadata.ConfigurationItemRoot.CONFIGURATION, description = "HipChat Notification configuration.")
public class HipChatNotification extends BaseConfigurationItem implements Action {
	
	private static final Logger logger = LoggerFactory.getLogger(HipChatNotification.class);
	private static final String MESSAGE_FROM = "XL Deploy";
	private static final String MESSAGE_FORMAT = "text";
	
	@Property(defaultValue = "https://api.hipchat.com", description = "The host HipChat API request.")
    private String hipChatApiHost;
	
	@Property(description = "The auth token used for the HipChat API request.")
    private String authToken;
	
	@Property(description = "The room id for the HipChat API request.")
    private String roomId;
	
	@Property(description = "The message to send to the HipChat room via API request.", size = Property.Size.LARGE)
    private String message;
	
	@Property(required = false, 
			defaultValue = "false", 
			label = "Notify Room Occupants", 
			description = "Should the message cause a notification to room occupants?")
	private boolean notify;
	
	public HipChatNotification()	{}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Map context) {
		String notificationMessage = TemplateParser.parseTemplatedMessage(message, context);
		logger.info("notificationMessage: " + notificationMessage);
		
		class MyErrorHandler implements ErrorHandler {
			  @Override public Throwable handleError(RetrofitError cause) {
			    Response r = cause.getResponse();
			    logger.error(r.getReason());
			    return cause;
			  }
			}
		
		RequestInterceptor requestInterceptor = new RequestInterceptor() {
			  @Override
			  public void intercept(RequestFacade request) {
			    request.addQueryParam("auth_token", authToken);
			    request.addQueryParam("room_id", roomId);
			    request.addQueryParam("message_format", MESSAGE_FORMAT);
			    request.addQueryParam("from", MESSAGE_FROM);
			  }
			};
		
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setLogLevel(RestAdapter.LogLevel.FULL)
			.setEndpoint(hipChatApiHost)
			.setRequestInterceptor(requestInterceptor)
			.setErrorHandler(new MyErrorHandler())
			.build();
		
		HipChatService hipChatService = restAdapter.create(HipChatService.class);
		hipChatService.sendHipChatMessage(notificationMessage, 0, sendMessageCallback);
	}
	
	public String getAuthToken()	{
		return authToken;
	}

	public String getRoomId()	{
		return roomId;
	}
	
	public String getHipChatApiHost()	{
		return hipChatApiHost;
	}
	
	private Callback<HipChatMessageStatus> sendMessageCallback = new Callback<HipChatMessageStatus>()	{

		@Override
		public void failure(RetrofitError retrofitError) {
			logger.error("Response - Status: " + retrofitError.getResponse().getStatus() + " - Reason: " + retrofitError.getResponse().getReason());
		}

		@Override
		public void success(HipChatMessageStatus hipChatMessageStatus, Response response) {
			logger.info("Message Sent - Status: " + hipChatMessageStatus.getStatus());
		}
		
	};

}
