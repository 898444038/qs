package com.chat.qs.controller;

import com.chat.qs.config.redis.RedissonService;
import com.chat.qs.constant.RedisConstant;
import com.chat.qs.entity.Label;
import com.chat.qs.vo.CategoryVo;
import com.chat.qs.service.CategoryService;
import com.chat.qs.entity.Category;

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
 * 问题分类
 * @author: Administrator
 * @date: 2020-04-22
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
	@Autowired
	private RedissonService redissonService;

	@GetMapping("/selectAll")
	public List<Category> selectAll(CategoryVo categoryVo){
		List<Category> categoryList = redissonService.getRBucket(RedisConstant.CATEGORY_LIST_KEY);
		if(categoryList==null || categoryList.isEmpty()){
			return categoryService.selectList(categoryVo);
		}
		return categoryList;
	}

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectList")
    public List<Category> selectList(CategoryVo categoryVo){
        return categoryService.selectList(categoryVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectOne")
    public Category selectOne(CategoryVo categoryVo){
        return categoryService.selectOne(categoryVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody Category category){
        return categoryService.insert(category);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PutMapping("/update")
    public int update(@RequestBody Category category){
        return categoryService.update(category);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return categoryService.delete(id);
    }
}
