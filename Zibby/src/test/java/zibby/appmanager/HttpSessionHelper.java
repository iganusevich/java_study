package zibby.appmanager;



import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpSessionHelper {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSessionHelper(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build(); //  .setRedirectStrategy(new LaxRedirectStrategy())
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty1("web.baseURL") + "login.php"); //"http://localhost/mantisbt-1.2.19/login.php"
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));
    }

    public String getPhone() throws IOException {
        HttpPost post = new HttpPost(app.getProperty1("web.phoneURL"));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = geTextFrom(response);
        return extractRandomValue("\\+1([0-9]{10})<", body);
    }

    private String extractRandomValue(String reg_value, String text){
        Pattern p = Pattern.compile(reg_value);
        Matcher m = p.matcher(text);
        List<String> phones = new ArrayList<String>();
        while(m.find()) {
            phones.add(m.group(1));
        }
        Random rand = new Random();
        return phones.get(rand.nextInt(phones.size()));
    }

    public String getCode(String phone) throws IOException, InterruptedException {
        HttpPost post = new HttpPost(app.getProperty1("web.phoneURL"));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = geTextFrom(response);
        String ref = extractValue(phone + "<.*\\n.*\\n.*([0-9]{4})", body);
        String ver_code = "No Match Found";
        while (ver_code == "No Match Found") {
            TimeUnit.SECONDS.sleep(5);
            HttpPost post1 = new HttpPost(app.getProperty1("web.codeURL") + ref + "/");
            CloseableHttpResponse response1 = httpclient.execute(post1);
            String body1 = geTextFrom(response1);
            ver_code = extractValue("Your Zibby verification code is .*([0-9]{6})", body1);
        }
        return ver_code;
    }

    public String extractValue(String reg_value, String text) {
        Pattern p = Pattern.compile(reg_value);
        Matcher m = p.matcher(text);
        boolean found = m.find();
        if (found) {
            return m.group(1);
        } else {
            return "No Match Found";
        }
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty1("web.baseURL") + "/index.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));
    }



}



