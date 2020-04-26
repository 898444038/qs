package com.chat.qs.service;

import com.chat.qs.vo.UnknownQuestionVo;
import com.chat.qs.entity.UnknownQuestion;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未知问题库
 * @author: Administrator
 * @date: 2020-04-23
 */
public interface UnknownQuestionService {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    List<UnknownQuestion> selectList(UnknownQuestionVo unknownQuestionVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    UnknownQuestion selectOne(UnknownQuestionVo unknownQuestionVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    int insert(UnknownQuestion unknownQuestion);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    int update(UnknownQuestion unknownQuestion);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    int delete(Long id);

}
