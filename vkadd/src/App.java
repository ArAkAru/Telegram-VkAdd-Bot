

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.LongPollingBot;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String BOT_NAME = "vk_add_bot";
    private static String BOT_TOKEN = "970278084:AAE9bNJy5vc7EFXZPiENYasRtg_xYxo9NYc" /* your bot's token here */;
    private static String PROXY_HOST = "139.59.132.64" /* proxy host */;
    private static Integer PROXY_PORT = 1080 /* proxy port */;
    public static void main(String[] args) {
//    	System.getProperties().put("proxySet",true);
//    	System.getProperties().put("socksProxyHost","127.0.0.1");
//    	System.getProperties().put("socksProxyPort","9150");
	ApiContextInitializer.init();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//    DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//    HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);
//    RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
//    botOptions.setRequestConfig(requestConfig);
//    botOptions.setHttpProxy(httpHost);
    
    try {
        telegramBotsApi.registerBot(new VkBot());
    } catch (TelegramApiRequestException e) {
        e.printStackTrace();
    }
	}
}
