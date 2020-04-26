package com.chat.qs.service.impl;

import com.chat.qs.entity.Question;
import com.chat.qs.service.QuestionService;
import com.chat.qs.mapper.QuestionMapper;
import com.chat.qs.vo.QuestionVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题库
 * @author: Administrator
 * @date: 2020-04-22
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public List<Question> selectList(QuestionVo questionVo) {
        return questionMapper.selectList(questionVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public Question selectOne(QuestionVo questionVo) {
        return questionMapper.selectOne(questionVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int insert(Question question) {
        return questionMapper.insert(question);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int update(Question question) {
        return questionMapper.update(question);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int delete(Long id) {
        return questionMapper.delete(id);
    }
}
