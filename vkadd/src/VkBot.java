import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;

public class VkBot extends TelegramLongPollingBot {
	static boolean start_flag=false;
	public String getBotUsername() {
		return "vk_add_bot";
	}

	@Override
	public String getBotToken() {
		return "970278084:AAE9bNJy5vc7EFXZPiENYasRtg_xYxo9NYc";
	}

	@SuppressWarnings("deprecation")
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();                   // Получаем текст входящего сообщения
		SendMessage sendMessage = new SendMessage(); 
		sendMessage.setChatId(message.getChatId().toString()); 
		if(message.getText().equals("/check")&&!start_flag) {
			sendMessage.setText("Hi, write pls your VK id");
			try {
				sendMessage(sendMessage);
				start_flag =true;
			} catch (TelegramApiException e1) {
				e1.printStackTrace();
			}
		}
        
		else if(!start_flag){
        	sendMessage.setText("Pls use /check ");
        	try {
        		sendMessage(sendMessage);                           // Отправим сообщение
        	} catch (TelegramApiException e) {
            e.printStackTrace();                                // Это обработка исключительных ситуаций - на случай если что-то пойдет не так
        		}
		}
		else {
			update.getMessage();   
			TransportClient transportClient = HttpTransportClient.getInstance(); 
			VkApiClient vk = new VkApiClient(transportClient);
			message=update.getMessage(); 
			UserAuthResponse authResponse;
			try {
				authResponse = vk.oauth()
						.userAuthorizationCodeFlow(7112274,"2vRb0lPBxeLCOKnWZ12e","https://oauth.vk.com/blank.html","9bae88cba9aa1002f9") 
						.execute();
				UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken()); 
				List<UserXtrCounters> getResponse = vk.users().get(actor).userIds(message.getText()).fields(UserField.IS_FRIEND,UserField.CAN_SEE_AUDIO).execute();
				if(getResponse.get(0).isFriend()) {
					sendMessage.setText("We are friend :)");
				    try {
				    	sendMessage(sendMessage);
						} catch (TelegramApiException e) {
							e.printStackTrace();
						}
				}
				else {
					sendMessage.setText("Add me https://vk.com/fuuark");
				    try {
				    	sendMessage(sendMessage);
						} catch (TelegramApiException e) {
								e.printStackTrace();
						}
				}
				} catch (ApiException e) {
					e.printStackTrace();
				} catch (ClientException e) {
					e.printStackTrace();
				} 
			
				start_flag =false;
		}
	}
}

