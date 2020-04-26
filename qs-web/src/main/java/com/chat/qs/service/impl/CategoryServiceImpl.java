package com.chat.qs.service.impl;

import com.chat.qs.mapper.CategoryMapper;
import com.chat.qs.vo.CategoryVo;
import com.chat.qs.service.CategoryService;
import com.chat.qs.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题分类
 * @author: Administrator
 * @date: 2020-04-22
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public List<Category> selectList(CategoryVo categoryVo) {
        return categoryMapper.selectList(categoryVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public Category selectOne(CategoryVo categoryVo) {
        return categoryMapper.selectOne(categoryVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int update(Category category) {
        return categoryMapper.update(category);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int delete(Long id) {
        return categoryMapper.delete(id);
    }
}
