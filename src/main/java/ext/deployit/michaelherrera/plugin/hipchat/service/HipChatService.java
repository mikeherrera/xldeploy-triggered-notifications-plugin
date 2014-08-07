package ext.deployit.michaelherrera.plugin.hipchat.service;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

public interface HipChatService {
	
	@POST("/v1/rooms/message?format=json")
	void sendHipChatMessage(@Query("message") String message, @Query("notify") int notify, Callback<HipChatMessageStatus> sendMessageCallback);
	
}
