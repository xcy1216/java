package com.component;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class BaiduApiClient {

        // 设置静态字段

//    public static String APP_ID;
//    public static String API_KEY;
//    public static String SECRET_KEY;
//
//    @Autowired
//    public BaiduApiClient(@Value("${baidu-api.appId}") String appId,
//                          @Value("${baidu-api.apiKey}") String apiKey,
//                          @Value("${baidu-api.apiSecret}") String apiSecret) {
//        APP_ID = appId;
//        API_KEY = apiKey;
//        SECRET_KEY = apiSecret;
//    }

    private static final String API_KEY = "RVSei6WqY70AgBp4j3dC4L31";
    private static final String SECRET_KEY = "Y57pNnnrz0oc6Rrs1NXPseJhLIQrUOXV";

//    @Value("${baidu-api.appId}")
//    public void setAppId(String appId) {
//        APP_ID = appId;
//    }
//
//    @Value("${baidu-api.apiKey}")
//    public void setApiKey(String apiKey) {
//        API_KEY = apiKey;
//    }
//    @Value("${baidu-api.apiSecret}")
//    public void setApiSecret(String apiSecret) {
//        SECRET_KEY = apiSecret;
//    }

    // 设置获取access token的地址和参数
    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token";
    private static final String AUTH_PARAMS;

    static {
        try {
            AUTH_PARAMS = "grant_type=client_credentials" +
                    "&client_id=" + URLEncoder.encode(API_KEY, "UTF-8") +
                    "&client_secret=" + URLEncoder.encode(SECRET_KEY, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // 定义一个获取access token的方法
    public static String getAccessToken() {
        try {
            // 创建一个URL对象，并传入获取access token的地址和参数
            URL url = new URL(AUTH_HOST + "?" + AUTH_PARAMS);

            // 打开一个连接对象，并设置相关的属性
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // 请求方法
            conn.setConnectTimeout(5000); // 连接超时时间
            conn.setReadTimeout(5000); // 读取超时时间

            // 获取连接对象的输入流，并使用一个缓冲读取器来读取返回值
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // 将返回值转换为一个JSON对象，并解析其中的数据项，获取access token
            JSONObject json = new JSONObject(sb.toString());
            String accessToken = json.getString("access_token");

            // 关闭连接
            conn.disconnect();

            // 返回access token
            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
