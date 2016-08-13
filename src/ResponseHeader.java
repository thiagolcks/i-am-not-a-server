
public class ResponseHeader {

	public static byte[] getHeader(int code) {
    	return getHeader(code, 0, "text/html; charset=UTF-8");
    }

    public static byte[] getHeader(int code, int size) {
    	return getHeader(code, size, "text/html; charset=UTF-8");
    }
   
    public static byte[] getHeader(int code, int size, String contentType) {
    	String codeHeader = "";
    	switch (code) {
    		case 200: codeHeader = "200 OK"; break;
    		case 404: codeHeader = "404 Not Found"; break;
    		default: codeHeader = "500 Internal Error";
    	}
    	String header = "HTTP/1.1 " + codeHeader + "\n";
    	header += "Content-Type: " + contentType + "\n";
    	header += "Server: I'm Not a Server 1.1\n";
    	header += "Connection: close\n";
    	header += "Content-Length: " + size + "\n\n";
    	return header.getBytes();
    }
    
}
