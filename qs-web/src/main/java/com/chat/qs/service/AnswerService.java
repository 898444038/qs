package com.chat.qs.service;

import com.chat.qs.vo.AnswerVo;
import com.chat.qs.entity.Answer;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 答案库
 * @author: Administrator
 * @date: 2020-04-22
 */
public interface AnswerService {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    List<Answer> selectList(AnswerVo answerVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    Answer selectOne(AnswerVo answerVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int insert(Answer answer);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int update(Answer answer);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int delete(Long id);

}
