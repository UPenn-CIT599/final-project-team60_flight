import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class makes API calls. 
 * It has a subclass NetworkCallerMock, for unit test purpose.
 * @author xueyingli
 *
 */
public class NetworkCaller {
	
	public String Call(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); //response
		StringBuilder sb = new StringBuilder();
		int next = br.read();
		while (next != -1) {
			sb.append((char)next);
			next = br.read();
		}
		is.close();
		String jsonText = sb.toString();
		return jsonText;
	}
	
	public String Call(String urlHead, String key, String urlTail) throws IOException {
		String url = urlHead + key + urlTail;
		return Call(url);
	}
	
	public String Call(String url, HashMap<String, String> headers) throws IOException, UnirestException {
		HttpResponse<JsonNode> response = Unirest.get(url)
				.headers(headers)
				.asJson();
		return response.getBody().toString();
	}
	
	public String sessionKeyHelper(String host, String body, HashMap<String, String> headers) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(host)
				.headers(headers)
				.body(body)
				.asJson();
		List<String> sessionKeyList = response.getHeaders().get("Location");
		String[] sessionKeyArr = sessionKeyList.get(0).split("/");
		return sessionKeyArr[7];
	}
	
}
