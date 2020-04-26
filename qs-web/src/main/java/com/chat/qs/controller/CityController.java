package com.chat.qs.controller;

import com.chat.qs.entity.City;
import com.chat.qs.service.CityService;
import com.chat.qs.vo.CityVo;

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
 * 城市
 * @author: Administrator
 * @date: 2020-04-23
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @GetMapping("/selectList")
    public List<City> selectList(CityVo cityVo){
        return cityService.selectList(cityVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @GetMapping("/selectOne")
    public City selectOne(CityVo cityVo){
        return cityService.selectOne(cityVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @PostMapping("/insert")
    public int insert(@RequestBody City city){
        return cityService.insert(city);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @PutMapping("/update")
    public int update(@RequestBody City city){
        return cityService.update(city);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @DeleteMapping("/delete")
    public int delete(Long id){
        return cityService.delete(id);
    }
}
