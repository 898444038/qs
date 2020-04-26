package com.chat.qs.service.impl;

import com.chat.qs.service.UnknownQuestionService;
import com.chat.qs.vo.UnknownQuestionVo;
import com.chat.qs.mapper.UnknownQuestionMapper;
import com.chat.qs.entity.UnknownQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未知问题库
 * @author: Administrator
 * @date: 2020-04-23
 */
@Service
public class UnknownQuestionServiceImpl implements UnknownQuestionService {

    @Autowired
    private UnknownQuestionMapper unknownQuestionMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public List<UnknownQuestion> selectList(UnknownQuestionVo unknownQuestionVo) {
        return unknownQuestionMapper.selectList(unknownQuestionVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public UnknownQuestion selectOne(UnknownQuestionVo unknownQuestionVo) {
        return unknownQuestionMapper.selectOne(unknownQuestionVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public int insert(UnknownQuestion unknownQuestion) {
        return unknownQuestionMapper.insert(unknownQuestion);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public int update(UnknownQuestion unknownQuestion) {
        return unknownQuestionMapper.update(unknownQuestion);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public int delete(Long id) {
        return unknownQuestionMapper.delete(id);
    }
}
