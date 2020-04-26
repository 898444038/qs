package com.chat.qs.controller;

import com.chat.qs.config.redis.RedissonService;
import com.chat.qs.constant.RedisConstant;
import com.chat.qs.entity.Question;
import com.chat.qs.service.LabelService;
import com.chat.qs.entity.Label;
import com.chat.qs.vo.LabelVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 问题标签
 * @author: Administrator
 * @date: 2020-04-22
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;
	@Autowired
	private RedissonService redissonService;

	@GetMapping("/selectAll")
	public List<Label> selectAll(){
		return redissonService.getRBucket(RedisConstant.LABEL_LIST_KEY);
	}

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectList")
    public List<Label> selectList(LabelVo labelVo){
		return labelService.selectList(labelVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectOne")
    public Label selectOne(LabelVo labelVo){
        return labelService.selectOne(labelVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody Label label){
        return labelService.insert(label);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PutMapping("/update")
    public int update(@RequestBody Label label){
        return labelService.update(label);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return labelService.delete(id);
    }
}
