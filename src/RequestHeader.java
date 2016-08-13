import java.io.BufferedReader;
import java.io.IOException;

public class RequestHeader {

	private String command;
	private String uri;
	private String method;

	public RequestHeader(BufferedReader in) throws IOException {
		this.command = in.readLine();
		if (command != null) {
			String[] result = command.split(" ");
			this.method = result[0];
			this.uri = result[1];
			String line = null;
			
			// Iterate until a blank line
			while ((line = in.readLine()).length() > 0) {}
		}
	}

	public String getUri() {	
		return uri;
	}

	public String getMethod() {
		return method;
	}
	
}
