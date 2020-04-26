package com.chat.qs.service.impl;

import com.chat.qs.entity.City;
import com.chat.qs.mapper.CityMapper;
import com.chat.qs.service.CityService;
import com.chat.qs.vo.CityVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市
 * @author: Administrator
 * @date: 2020-04-23
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public List<City> selectList(CityVo cityVo) {
        return cityMapper.selectList(cityVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public City selectOne(CityVo cityVo) {
        return cityMapper.selectOne(cityVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public int insert(City city) {
        return cityMapper.insert(city);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public int update(City city) {
        return cityMapper.update(city);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-23
	 */
    @Override
    public int delete(Long id) {
        return cityMapper.delete(id);
    }
}
