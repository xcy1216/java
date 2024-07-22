package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.FruitEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface FruitService extends IService<FruitEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Wrapper<FruitEntity> wrapper);
}
