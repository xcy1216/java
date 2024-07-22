package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.FruitDao;
import com.dao.KnowledgeDao;
import com.entity.FruitEntity;
import com.entity.KnowledgeEntity;
import com.service.FruitService;
import com.service.KnowledgeService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("knowledgeService")
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeDao, KnowledgeEntity> implements KnowledgeService{
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KnowledgeEntity> page = this.selectPage(
                new Query<KnowledgeEntity>(params).getPage(),
                new EntityWrapper<KnowledgeEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<KnowledgeEntity> wrapper) {
        Page<KnowledgeEntity> page =new Query<KnowledgeEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
}
