import java.net.Socket;

public class Application {

	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WebServer ws = new WebServer();
        ws.setUp();
        while (true) {
	        Socket s = ws.waitForConnections();
	        RequestHeader request = ws.receiveRequest(s);
	        byte[] data = ws.processRequest(request);     
	        ws.sendResponse(data, s);
        }
    }
    
}
