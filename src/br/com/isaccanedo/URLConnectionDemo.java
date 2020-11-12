package br.com.isaccanedo;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class URLConnectionDemo {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.wikipedia.org");
            System.out.println(url.getAuthority());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getFile());
            System.out.println(url.toExternalForm());
            URLConnection urlCon = url.openConnection();
            long len = urlCon.getContentLengthLong();
            System.out.println("len=" + len);
            if (len > 0) {
                InputStream in = urlCon.getInputStream();
                int i = 0;
                while ((i = in.read()) != -1)
                    System.out.print((char) i);
                in.close();
            }

            Map<String, List<String>> headers = urlCon.getHeaderFields();
            Set<String> keys = headers.keySet();
            for (String key : keys)
                System.out.println("Key: " + key + "\tValue: " + headers.get(key));

            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            System.out.println("Request Method: "+httpCon.getRequestMethod());
            System.out.println("Response Code: " + httpCon.getResponseCode());
            System.out.println("Response Message: " + httpCon.getResponseMessage());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
