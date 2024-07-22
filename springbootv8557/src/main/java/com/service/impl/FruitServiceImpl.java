package com.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ForumDao;
import com.dao.FruitDao;
import com.entity.ForumEntity;
import com.entity.FruitEntity;
import com.entity.MessagesEntity;
import com.entity.view.MessagesView;
import com.service.ForumService;
import com.service.FruitService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("fruitService")
public class FruitServiceImpl extends ServiceImpl<FruitDao, FruitEntity> implements FruitService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FruitEntity> page = this.selectPage(
                new Query<FruitEntity>(params).getPage(),
                new EntityWrapper<FruitEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<FruitEntity> wrapper) {
        Page<FruitEntity> page =new Query<FruitEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
}
