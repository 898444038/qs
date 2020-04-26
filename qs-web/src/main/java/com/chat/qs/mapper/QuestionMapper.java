package com.chat.qs.mapper;

import com.chat.qs.entity.Question;
import com.chat.qs.vo.QuestionVo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 问题库
 * @author: Administrator
 * @date: 2020-04-22
 */
@Mapper
public interface QuestionMapper {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    List<Question> selectList(QuestionVo questionVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    Question selectOne(QuestionVo questionVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int insert(Question question);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int update(Question question);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    int delete(Long id);

}
