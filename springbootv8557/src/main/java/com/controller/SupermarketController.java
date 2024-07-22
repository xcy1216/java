package com.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.FruitEntity;
import com.entity.SupermarketEntity;
import com.service.FruitService;
import com.service.StoreupService;
import com.service.SupermarketService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
public class SupermarketController {

    @Autowired
    private SupermarketService supermarketService;

    @Autowired
    private StoreupService storeupService;



    @RequestMapping("/getAll")
    public R getAll(@RequestParam Map<String, Object> params, SupermarketEntity supermarket,
                    HttpServletRequest request){
        EntityWrapper<SupermarketEntity> ew = new EntityWrapper<SupermarketEntity>();
        PageUtils page = supermarketService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, supermarket), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SupermarketEntity supermarket = supermarketService.selectById(id);
        return R.ok().put("data", supermarket);
    }

    @RequestMapping("/query")
    public R query(@RequestBody SupermarketEntity supermarket){
        EntityWrapper< SupermarketEntity> ew = new EntityWrapper< SupermarketEntity>();
        ew.eq("fruit_name", supermarket.getFruit_name());
        SupermarketEntity supermarketEntity =  supermarketService.selectOne(ew);
        return R.ok("查询商品成功").put("data", supermarketEntity);
    }

    @RequestMapping("/update")
    public R update(@RequestBody SupermarketEntity supermarket){
        SupermarketEntity supermarketEntity = supermarketService.selectOne(new EntityWrapper<SupermarketEntity>().eq("fruit_name", supermarket.getFruit_name()));
        supermarket.setId(supermarketEntity.getId());
        supermarketService.updateById(supermarket);//全部更新
        return R.ok("修改成功！");
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        supermarketService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("删除成功！");
    }

    @PostMapping("/add")
    public R save(@RequestBody SupermarketEntity supermarket){
//    	ValidatorUtils.validateEntity(user);
        if(supermarketService.selectOne(new EntityWrapper<SupermarketEntity>().eq("fruit_name", supermarket.getFruit_name())) !=null) {
            return R.error("该商品已存在");
        }
        supermarketService.insert(supermarket);
        return R.ok("添加成功！");
    }

    @GetMapping("/getTypes")
    public R getTypes(){
        EntityWrapper<SupermarketEntity> ew = new EntityWrapper<SupermarketEntity>();
        List<SupermarketEntity> supermarketEntities = supermarketService.selectList(ew);
        List<String> collect = supermarketEntities.stream().map(SupermarketEntity::getFruit_type).distinct().collect(Collectors.toList());
        return R.ok().put("data", collect);
    }

    @GetMapping("/getFruitOfType")
    public R getFruitOfType(@RequestParam Map<String, Object> params, SupermarketEntity supermarket,
                            HttpServletRequest request){
        System.out.println(supermarket.getFruit_type());
        EntityWrapper<SupermarketEntity> ew = new EntityWrapper<SupermarketEntity>();
        if(supermarket.getFruit_type() != null && supermarket.getFruit_type().equals("全部")){
            supermarket.setFruit_type(null);
        }
        supermarketService.selectCount(new EntityWrapper<SupermarketEntity>().eq("fruit_type", supermarket.getFruit_type()));
        PageUtils page = supermarketService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, supermarket), params), params));
        return R.ok().put("data", page);
    }

}
