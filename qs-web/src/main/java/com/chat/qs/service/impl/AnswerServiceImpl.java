package com.chat.qs.service.impl;

import com.chat.qs.vo.AnswerVo;
import com.chat.qs.service.AnswerService;
import com.chat.qs.mapper.AnswerMapper;
import com.chat.qs.entity.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 答案库
 * @author: Administrator
 * @date: 2020-04-22
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public List<Answer> selectList(AnswerVo answerVo) {
        return answerMapper.selectList(answerVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public Answer selectOne(AnswerVo answerVo) {
        return answerMapper.selectOne(answerVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int insert(Answer answer) {
        return answerMapper.insert(answer);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int update(Answer answer) {
        return answerMapper.update(answer);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @Override
    public int delete(Long id) {
        return answerMapper.delete(id);
    }
}
