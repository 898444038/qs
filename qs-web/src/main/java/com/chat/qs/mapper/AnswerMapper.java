package com.chat.qs.mapper;

import com.chat.qs.vo.AnswerVo;
import com.chat.qs.entity.Answer;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 答案库
 * @author: Administrator
 * @date: 2020-04-22
 */
@Mapper
public interface AnswerMapper {

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
