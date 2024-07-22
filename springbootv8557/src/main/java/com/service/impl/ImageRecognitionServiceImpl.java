package com.service.impl;

import com.entity.ImageRecognitionInformationEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.service.ImageRecognitionService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ImageRecognitionServiceImpl implements ImageRecognitionService {

    @Override
    public ImageRecognitionInformationEntity dealingJson(String jsonString) {

        // 使用 Gson 解析 JSON 数据
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        // 提取 result 数组
        JsonArray resultArray = jsonObject.getAsJsonObject("ingredient").getAsJsonArray("result");

        // 提取首个水果的名称和可能度（最有可能）
        JsonObject fruitObject = resultArray.get(0).getAsJsonObject();
        double score = fruitObject.get("score").getAsDouble();
        // 创建DecimalFormat对象，并指定百分比格式
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");
        // 格式化数字为百分比形式，并输出结果
        String pos = decimalFormat.format(score);
        String name = fruitObject.get("name").getAsString();
        System.out.println("水果名: " + name + ", 可能度: " + pos);
        ImageRecognitionInformationEntity information = ImageRecognitionInformationEntity.builder()
                .fruitName(name)
                .fruitPossibility(pos)
                .build();
        return information;

    }
}
