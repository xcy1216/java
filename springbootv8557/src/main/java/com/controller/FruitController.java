package com.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.*;
import com.service.FruitService;
import com.service.StoreupService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @Autowired
    private StoreupService storeupService;



    @RequestMapping("/getAll")
    public R getAll(@RequestParam Map<String, Object> params, FruitEntity fruit,
                    HttpServletRequest request){
        EntityWrapper<FruitEntity> ew = new EntityWrapper<FruitEntity>();
        PageUtils page = fruitService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fruit), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FruitEntity fruit = fruitService.selectById(id);
        return R.ok().put("data", fruit);
    }

    @RequestMapping("/query")
    public R query(@RequestBody FruitEntity fruit){
        EntityWrapper< FruitEntity> ew = new EntityWrapper< FruitEntity>();
        ew.eq("fruit_name", fruit.getFruit_name());
        System.out.println(fruit.getFruit_name());
        FruitEntity fruitEntity =  fruitService.selectOne(ew);
        return R.ok("查询水果成功").put("data", fruitEntity);
    }

    @RequestMapping("/update")
    public R update(@RequestBody FruitEntity fruit){
        FruitEntity fruitEntity = fruitService.selectOne(new EntityWrapper<FruitEntity>().eq("fruit_name", fruit.getFruit_name()));
        fruit.setId(fruitEntity.getId());
        fruitService.updateById(fruit);//全部更新
        return R.ok("修改成功！");
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        fruitService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("删除成功！");
    }

    @PostMapping("/add")
    public R save(@RequestBody FruitEntity fruit){
//    	ValidatorUtils.validateEntity(user);
        if(fruitService.selectOne(new EntityWrapper<FruitEntity>().eq("fruit_name", fruit.getFruit_name())) !=null) {
            return R.error("该水果已存在");
        }
        fruitService.insert(fruit);
        return R.ok("添加成功！");
    }

    @GetMapping("/getTypes")
    public R getTypes(){
        EntityWrapper<FruitEntity> ew = new EntityWrapper<FruitEntity>();
        List<FruitEntity> fruitEntities = fruitService.selectList(ew);
        List<String> collect = fruitEntities.stream().map(FruitEntity::getFruit_type).distinct().collect(Collectors.toList());
        return R.ok().put("data", collect);
    }

    @GetMapping("/getFruitOfType")
    public R getFruitOfType(@RequestParam Map<String, Object> params, FruitEntity fruit,
                            HttpServletRequest request){
        System.out.println(fruit.getFruit_type());
        EntityWrapper<FruitEntity> ew = new EntityWrapper<FruitEntity>();
        if(fruit.getFruit_type() != null && fruit.getFruit_type().equals("全部")){
            fruit.setFruit_type(null);
        }
        fruitService.selectCount(new EntityWrapper<FruitEntity>().eq("fruit_type", fruit.getFruit_type()));
        PageUtils page = fruitService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fruit), params), params));
        return R.ok().put("data", page);
    }


}
