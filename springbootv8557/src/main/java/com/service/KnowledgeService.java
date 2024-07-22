package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.KnowledgeEntity;
import com.entity.SupermarketEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface KnowledgeService extends IService<KnowledgeEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Wrapper<KnowledgeEntity> wrapper);
}
