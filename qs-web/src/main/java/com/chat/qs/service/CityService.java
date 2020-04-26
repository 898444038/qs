package com.chat.qs.service;

import com.chat.qs.entity.City;
import com.chat.qs.vo.CityVo;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市
 * @author: Administrator
 * @date: 2020-04-23
 */
public interface CityService {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    List<City> selectList(CityVo cityVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    City selectOne(CityVo cityVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    int insert(City city);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    int update(City city);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    int delete(Long id);

}
