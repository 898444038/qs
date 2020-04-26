package com.chat.qs.controller;

import com.chat.qs.config.redis.RedissonService;
import com.chat.qs.constant.RedisConstant;
import com.chat.qs.entity.Question;
import com.chat.qs.service.QuestionService;
import com.chat.qs.utils.StringUtils;
import com.chat.qs.vo.QuestionVo;

import com.chat.qs.word.WordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * 问题库
 * @author: Administrator
 * @date: 2020-04-22
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
	private RedissonService redissonService;

	/**
	 * 分页查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
	@GetMapping("/selectPage")
	public List<Question> selectPage(QuestionVo questionVo){
		List<Question> questionList = redissonService.getRBucket(RedisConstant.QUESTION_LIST_KEY);
		if(questionList==null){
			WordHelper.getInstance().initWord();
			questionList = redissonService.getRBucket(RedisConstant.QUESTION_LIST_KEY);
		}
		if(questionList==null){
			return null;
		}
		if(StringUtils.isNotBlank(questionVo.getTitle())){
			Iterator<Question> iterator = questionList.iterator();
			while(iterator.hasNext()){
				Question question = iterator.next();
				if(!question.getTitle().contains(questionVo.getTitle())){
					iterator.remove();
				}
			}
		}
		int fromIndex = questionVo.getFromIndex();
		int toIndex = questionVo.getToIndex();
		if(toIndex > questionList.size()){
			toIndex = questionList.size();
		}
		questionList = questionList.subList(fromIndex,toIndex);
		return questionList;
	}

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectList")
    public List<Question> selectList(QuestionVo questionVo){
        return questionService.selectList(questionVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectOne")
    public Question selectOne(QuestionVo questionVo){
        return questionService.selectOne(questionVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody Question question){
        return questionService.insert(question);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PutMapping("/update")
    public int update(@RequestBody Question question){
        return questionService.update(question);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return questionService.delete(id);
    }
}
