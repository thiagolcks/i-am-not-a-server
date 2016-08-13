
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Thiago Locks
 */
public class WebServer {

    ServerSocket ss;

    public void setUp() {
        try {
            ss = new ServerSocket(8081);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket waitForConnections() {
        try {
            return ss.accept();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Socket Error!");
        }
    }

    public RequestHeader receiveRequest(Socket s) {
        RequestHeader request = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            request = new RequestHeader(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public byte[] processRequest(RequestHeader request) {
    	byte[] content = null;
        String uri = request.getUri();
        if (uri != null) {
	        char lastChar = uri.charAt(uri.length() - 1);
	        if (lastChar == '/') {
	        	uri += "index.html";
	        }
        }
        
        String contentType = FileUtil.getContentType(uri);
        byte[] file = FileUtil.getFile(uri);
        byte[] header = null;
        if (file == null) {
        	file = FileUtil.getFile("/error/404.html");
        	if (file != null) {
        		header = ResponseHeader.getHeader(404, file.length);
        	} else {
        		header = ResponseHeader.getHeader(500);
        		file = new byte[0];
        	}
        } else {
        	header = ResponseHeader.getHeader(200, file.length, contentType);
        }
        content = new byte[header.length + file.length];
        System.arraycopy(header, 0, content, 0, header.length);
        System.arraycopy(file, 0, content, header.length, file.length);
    
        return content;
    }

    public void sendResponse(byte responseData[], Socket s) {
    	try {
    		java.io.OutputStream out = s.getOutputStream();
    		out.write(responseData);
    		out.flush();
    		out.close();
    		s.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
