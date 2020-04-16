package cn.idea360.idcwechat.utils;

import cn.idea360.idcwechat.bean.WxAccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String newGetRequest(String url) {

        HttpClient httpclient = new DefaultHttpClient();
        String result = null;
        try {

            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(response.getEntity());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return result;
    }

    public static void main(String[] args) throws Exception{
        String msg = "{\n" +
                "    \"access_token\": \"32_S9_b7A9dIZ4004QwnLYMwdha4Wv113R0BfTThITTWFGJZTi-t5ogOiECkNXWh3KJztE1e8UMRl4qbpMRqP00JKNzaBsl_7g06pVQ7YRhB3cLKnwntaNAHM2zCEYcywbPfAd4YofH9wE0wiIbSAXeAEAOWT\",\n" +
                "    \"expires_in\": 7200\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        WxAccessToken wxXmlMessage = mapper.readValue(msg, WxAccessToken.class);
        System.out.println(wxXmlMessage);

    }
}
