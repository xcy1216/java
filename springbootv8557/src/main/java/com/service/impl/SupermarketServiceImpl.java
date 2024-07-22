package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.FruitDao;
import com.dao.SupermarketDao;
import com.entity.FruitEntity;
import com.entity.SupermarketEntity;
import com.service.FruitService;
import com.service.SupermarketService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("supermarketService")
public class SupermarketServiceImpl extends ServiceImpl<SupermarketDao, SupermarketEntity> implements SupermarketService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SupermarketEntity> page = this.selectPage(
                new Query<SupermarketEntity>(params).getPage(),
                new EntityWrapper<SupermarketEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<SupermarketEntity> wrapper) {
        Page<SupermarketEntity> page =new Query<SupermarketEntity>(params).getPage();
        page.setRecords(baseMapper.selectPage(page,wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
}
