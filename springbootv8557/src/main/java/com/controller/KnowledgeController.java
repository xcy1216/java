package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.KnowledgeEntity;
import com.service.KnowledgeService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/getAll")
    public R getAll(@RequestParam Map<String, Object> params, KnowledgeEntity knowledge,
                    HttpServletRequest request){
        EntityWrapper<KnowledgeEntity> ew = new EntityWrapper<KnowledgeEntity>();
        PageUtils page = knowledgeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, knowledge), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KnowledgeEntity knowledge = knowledgeService.selectById(id);
        return R.ok().put("data", knowledge);
    }

    @RequestMapping("/query")
    public R query(@RequestBody KnowledgeEntity knowledge){
        EntityWrapper< KnowledgeEntity> ew = new EntityWrapper< KnowledgeEntity>();
        ew.eq("title", knowledge.getTitle());
        KnowledgeEntity knowledgeEntity =  knowledgeService.selectOne(ew);
        return R.ok("查询知识成功").put("data", knowledgeEntity);
    }

    @RequestMapping("/update")
    public R update(@RequestBody KnowledgeEntity knowledge){
        KnowledgeEntity knowledgeEntity = knowledgeService.selectOne(new EntityWrapper<KnowledgeEntity>().eq("title", knowledge.getTitle()));
        knowledge.setId(knowledgeEntity.getId());
        knowledgeService.updateById(knowledge);//全部更新
        return R.ok("修改成功！");
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        knowledgeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok("删除成功！");
    }

    @PostMapping("/add")
    public R save(@RequestBody KnowledgeEntity knowledge){
//    	ValidatorUtils.validateEntity(user);
        Date date = new Date();
        knowledge.setDate(date);
        if(knowledgeService.selectOne(new EntityWrapper<KnowledgeEntity>().eq("title", knowledge.getTitle())) !=null) {
            return R.error("该知识已存在");
        }
        knowledgeService.insert(knowledge);
        return R.ok("添加成功！");
    }

}
