import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class FileUtil {

	public static String getContentType(String uri) {
    	String extension = null;
    	String result = "";

    	if (uri != null) {
	    	uri = uri.toLowerCase();
	    	String[] uriSplitted = uri.split("\\.");
	    	extension = uriSplitted[uriSplitted.length - 1];
    	}

    	switch (extension) {
    	case "html":
    	case "htm": result = "text/html; charset=UTF-8"; break;
    	case "jpg":
    	case "jpeg": result = "image/jpeg"; break;
    	case "gif": result = "image/gif"; break;
    	case "png": result = "image/png"; break;
    	case "json": result = "application/json"; break;
    	default: result = "text/plain";
    	}

    	return result;
    }

    public static byte[] getFile(String uri) {
    	int read;
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	try {
	    	InputStream resource = WebServer.class.getResourceAsStream("/public" + uri);
	    	if (resource != null) {
		    	while ((read = resource.read()) != -1) {
		    		output.write(read);
		    	}
		    	return output.toByteArray();
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

}
