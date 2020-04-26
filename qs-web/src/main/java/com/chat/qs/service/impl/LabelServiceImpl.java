package com.chat.qs.service.impl;

import com.chat.qs.service.LabelService;
import com.chat.qs.entity.Label;
import com.chat.qs.vo.LabelVo;
import com.chat.qs.mapper.LabelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题标签
 * @author: Administrator
 * @date: 2020-04-22
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public List<Label> selectList(LabelVo labelVo) {
        return labelMapper.selectList(labelVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public Label selectOne(LabelVo labelVo) {
        return labelMapper.selectOne(labelVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int insert(Label label) {
        return labelMapper.insert(label);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int update(Label label) {
        return labelMapper.update(label);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int delete(Long id) {
        return labelMapper.delete(id);
    }
}
