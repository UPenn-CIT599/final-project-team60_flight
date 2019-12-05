import java.io.IOException;
import java.util.HashMap;

/**
 * Mock API call results, for unit test purpose.
 * 
 * @author xueyingli
 *
 */
public class NetworkCallerMock extends NetworkCaller {
	
	private String mockResult;
	
	public NetworkCallerMock(String mockResult) {
		this.mockResult = mockResult;
	}
	
	@Override
	public String Call(String url, HashMap<String, String> headers) throws IOException {
		return mockResult;
	}

}
