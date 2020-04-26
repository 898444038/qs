package com.chat.qs.mapper;

import com.chat.qs.vo.CategoryVo;
import com.chat.qs.entity.Category;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 问题分类
 * @author: Administrator
 * @date: 2020-04-22
 */
@Mapper
public interface CategoryMapper {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    List<Category> selectList(CategoryVo categoryVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    Category selectOne(CategoryVo categoryVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int insert(Category category);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int update(Category category);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int delete(Long id);

}
