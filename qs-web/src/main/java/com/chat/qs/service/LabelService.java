package com.chat.qs.service;

import com.chat.qs.entity.Label;
import com.chat.qs.vo.LabelVo;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题标签
 * @author: Administrator
 * @date: 2020-04-22
 */
public interface LabelService {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    List<Label> selectList(LabelVo labelVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    Label selectOne(LabelVo labelVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int insert(Label label);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int update(Label label);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int delete(Long id);

}
