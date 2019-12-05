import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * This class makes API calls. 
 * It has a subclass NetworkCallerMock, for unit test purpose.
 * 
 * @author xueyingli
 *
 */
public class NetworkCaller {
	
	public String Call(String url, HashMap<String, String> headers) throws IOException {
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
}
