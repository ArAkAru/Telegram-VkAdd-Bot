import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;




public class Main {
	private static Logger LOGGER;
	private static String BOT_NAME = "vk_add_bot";
    private static String BOT_TOKEN = "970278084:AAE9bNJy5vc7EFXZPiENYasRtg_xYxo9NYc" /* your bot's token here */;
    private static String PROXY_HOST = "139.59.132.64" /* proxy host */;
    private static Integer PROXY_PORT = 1080 /* proxy port */;
	static {
	      String path = Main.class.getClassLoader()
	                                  .getResource("logging.properties")
	                                  .getFile();
	      System.setProperty("java.util.logging.config.file", path);
	      LOGGER = Logger.getLogger(Main.class.getName());
	}

	 public static void main(String[] args) throws ApiException, ClientException {
		  
		  System.out.println("-- main method starts --");
	      LOGGER.info("an info msg");
	      LOGGER.warning("a warning msg");
	      LOGGER.severe("a severe msg");
	      ApiContextInitializer.init();
	      TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
	      
	      try {
	          telegramBotsApi.registerBot(new VkBot());
	      } catch (TelegramApiRequestException e) {
	          e.printStackTrace();
	      }
	      
      
	}
}
	
