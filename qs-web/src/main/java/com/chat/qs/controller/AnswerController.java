package com.chat.qs.controller;

import com.chat.qs.vo.AnswerVo;
import com.chat.qs.service.AnswerService;
import com.chat.qs.entity.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 答案库
 * @author: Administrator
 * @date: 2020-04-22
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectList")
    public List<Answer> selectList(AnswerVo answerVo){
        return answerService.selectList(answerVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @GetMapping("/selectOne")
    public Answer selectOne(AnswerVo answerVo){
        return answerService.selectOne(answerVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody Answer answer){
        return answerService.insert(answer);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @PutMapping("/update")
    public int update(@RequestBody Answer answer){
        return answerService.update(answer);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-22
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return answerService.delete(id);
    }
}
