package com.chat.qs.controller;

import com.chat.qs.service.UnknownQuestionService;
import com.chat.qs.vo.UnknownQuestionVo;
import com.chat.qs.entity.UnknownQuestion;

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
 * 未知问题库
 * @author: Administrator
 * @date: 2020-04-23
 */
@RestController
@RequestMapping("/unknown/question")
public class UnknownQuestionController {

    @Autowired
    private UnknownQuestionService unknownQuestionService;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @GetMapping("/selectList")
    public List<UnknownQuestion> selectList(UnknownQuestionVo unknownQuestionVo){
        return unknownQuestionService.selectList(unknownQuestionVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @GetMapping("/selectOne")
    public UnknownQuestion selectOne(UnknownQuestionVo unknownQuestionVo){
        return unknownQuestionService.selectOne(unknownQuestionVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody UnknownQuestion unknownQuestion){
        return unknownQuestionService.insert(unknownQuestion);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @PutMapping("/update")
    public int update(@RequestBody UnknownQuestion unknownQuestion){
        return unknownQuestionService.update(unknownQuestion);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return unknownQuestionService.delete(id);
    }
}
