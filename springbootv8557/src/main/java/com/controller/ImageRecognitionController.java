package com.controller;

import com.component.BaiduApiClient;
import com.entity.ImageRecognitionInformationEntity;
import com.service.ImageRecognitionService;
import com.utils.R;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@RestController
public class ImageRecognitionController {


    @Autowired
    private BaiduApiClient baiduApiClient;

    @Autowired
    private ImageRecognitionService imageRecognitionService;

    // 设置图像识别的接口地址和参数
    private static final String IMAGE_HOST = "https://aip.baidubce.com/api/v1/solution/direct/imagerecognition/combination";
    private static final String SCENES = "[\"ingredient\"]";

    // 定义一个获取图片并转为base64编码的方法
    public static String getImageBase64(String imagePath) {
        try {
            // 创建一个文件对象，并传入图片的地址
            File file = new File(imagePath);

            // 创建一个文件输入流对象，并传入文件对象
            FileInputStream fis = new FileInputStream(file);

            // 创建一个字节数组，用来存储图片数据
            byte[] data = new byte[(int) file.length()];

            // 从文件输入流中读取图片数据到字节数组中
            fis.read(data);

            // 关闭文件输入流
            fis.close();

            // 使用Base64类的方法，将字节数组转为base64编码的字符串
            String imageBase64 = Base64.getEncoder().encodeToString(data);

            // 返回base64编码的字符串
            return imageBase64;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/identifyOld")
    public R imageRecognition(@RequestParam("imagePath") String imagePath) {
        try {
            String accessToken=baiduApiClient.getAccessToken();
            URL imageUrl = new URL(IMAGE_HOST + "?access_token=" + accessToken);
            HttpURLConnection imageConn = (HttpURLConnection) imageUrl.openConnection();
            imageConn.setRequestMethod("POST");
            imageConn.setConnectTimeout(5000);
            imageConn.setReadTimeout(5000);
            imageConn.setDoOutput(true);
            imageConn.setDoInput(true);
            imageConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            OutputStream out = imageConn.getOutputStream();
            DataOutputStream writer = new DataOutputStream(out);
            writer.writeBytes("{\"scenes\":" + SCENES + ",\"image\":\"" + getImageBase64(imagePath) + "\"}");
            writer.flush();
            writer.close();

            InputStream imageIn = imageConn.getInputStream();
            BufferedReader imageReader = new BufferedReader(new InputStreamReader(imageIn));
            StringBuilder imageSb = new StringBuilder();
            String imageLine = null;
            while ((imageLine = imageReader.readLine()) != null) {
                imageSb.append(imageLine);
            }

            JSONObject imageJson = new JSONObject(imageSb.toString());
            JSONObject result = imageJson.getJSONObject("result");
//            System.out.println(result.toString());

            imageConn.disconnect();
            String jsonstring=result.toString();
            ImageRecognitionInformationEntity information = imageRecognitionService.dealingJson(jsonstring);
            return R.ok().put("data",information);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }


    @PostMapping("/identify")
    public R imageRecognition2(@RequestParam("imageFile") MultipartFile imageFile) {
        System.out.println("into identify");
        if(imageFile == null){
            System.out.println("图片为空！");
        }

        try {
            String accessToken = baiduApiClient.getAccessToken();
            URL imageUrl = new URL(IMAGE_HOST + "?access_token=" + accessToken);
            HttpURLConnection imageConn = (HttpURLConnection) imageUrl.openConnection();
            imageConn.setRequestMethod("POST");
            imageConn.setConnectTimeout(5000);
            imageConn.setReadTimeout(5000);
            imageConn.setDoOutput(true);
            imageConn.setDoInput(true);
            imageConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            OutputStream out = imageConn.getOutputStream();
            DataOutputStream writer = new DataOutputStream(out);
            writer.writeBytes("{\"scenes\":" + SCENES + ",\"image\":\"" + getImageBase64_2(imageFile) + "\"}");
            writer.flush();
            writer.close();

            InputStream imageIn = imageConn.getInputStream();
            BufferedReader imageReader = new BufferedReader(new InputStreamReader(imageIn));
            StringBuilder imageSb = new StringBuilder();
            String imageLine = null;
            while ((imageLine = imageReader.readLine()) != null) {
                imageSb.append(imageLine);
            }

            JSONObject imageJson = new JSONObject(imageSb.toString());
            JSONObject result = imageJson.getJSONObject("result");

            imageConn.disconnect();
            String jsonstring = result.toString();
            ImageRecognitionInformationEntity information = imageRecognitionService.dealingJson(jsonstring);
            return R.ok().put("data", information);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

    private String getImageBase64_2(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }


//    @Autowired
//    private BaiduApiConfig baiduApiConfig;
    // 设置静态字段
//    public static String APP_ID="79346971";
//    public static String API_KEY="RVSei6WqY70AgBp4j3dC4L31";
//    public static String SECRET_KEY="Y57pNnnrz0oc6Rrs1NXPseJhLIQrUOXV";
//    // 设置静态字段
//    @Value("${baidu-api.appId}")
//    public static String APP_ID;
//    @Value("${baidu-api.apiKey}")
//    public static String API_KEY;
//    @Value("${baidu-api.apiSecret}")
//    public static String SECRET_KEY;
//
//    static {
//        // 获取Spring容器
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        // 从Spring容器中获取BaiduApiConfig实例
//        BaiduApiConfig baiduApiConfig = context.getBean(BaiduApiConfig.class);
//
//        // 设置静态字段的值
//        APP_ID = baiduApiConfig.getId();
//        API_KEY = baiduApiConfig.getApiKey();
//        SECRET_KEY = baiduApiConfig.getSecretKey();
//    }

//    @GetMapping("/identify")
//    public R identifyPic(){
//        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
//
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("top_num", "3");
//        options.put("filter_threshold", "0.7");
//        options.put("baike_num", "5");
//        String image="D:/watermelon.jpg";
//        // 参数为本地路径
//        JSONObject res = client.dishDetect(image, options);
//        return R.ok(res.toString(2));
//
//    }


}

