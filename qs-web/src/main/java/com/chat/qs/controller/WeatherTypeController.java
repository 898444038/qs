package com.chat.qs.controller;

import com.chat.qs.service.WeatherTypeService;
import com.chat.qs.entity.WeatherType;
import com.chat.qs.vo.WeatherTypeVo;

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
 * 天气类型
 * @author: Administrator
 * @date: 2020-04-24
 */
@RestController
@RequestMapping("/weather/type")
public class WeatherTypeController {

    @Autowired
    private WeatherTypeService weatherTypeService;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @GetMapping("/selectList")
    public List<WeatherType> selectList(WeatherTypeVo weatherTypeVo){
        return weatherTypeService.selectList(weatherTypeVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @GetMapping("/selectOne")
    public WeatherType selectOne(WeatherTypeVo weatherTypeVo){
        return weatherTypeService.selectOne(weatherTypeVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody WeatherType weatherType){
        return weatherTypeService.insert(weatherType);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @PutMapping("/update")
    public int update(@RequestBody WeatherType weatherType){
        return weatherTypeService.update(weatherType);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return weatherTypeService.delete(id);
    }
}
