package com.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 水果识别返回信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageRecognitionInformationEntity {
    //水果名
    public String fruitName;

    //相似度
    public String fruitPossibility;


}
