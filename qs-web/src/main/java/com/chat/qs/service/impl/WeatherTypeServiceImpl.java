package com.chat.qs.service.impl;

import com.chat.qs.service.WeatherTypeService;
import com.chat.qs.mapper.WeatherTypeMapper;
import com.chat.qs.entity.WeatherType;
import com.chat.qs.vo.WeatherTypeVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 天气类型
 * @author: Administrator
 * @date: 2020-04-24
 */
@Service
public class WeatherTypeServiceImpl implements WeatherTypeService {

    @Autowired
    private WeatherTypeMapper weatherTypeMapper;

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @Override
    public List<WeatherType> selectList(WeatherTypeVo weatherTypeVo) {
        return weatherTypeMapper.selectList(weatherTypeVo);
    }

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @Override
    public WeatherType selectOne(WeatherTypeVo weatherTypeVo) {
        return weatherTypeMapper.selectOne(weatherTypeVo);
    }

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @Override
    public int insert(WeatherType weatherType) {
        return weatherTypeMapper.insert(weatherType);
    }

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @Override
    public int update(WeatherType weatherType) {
        return weatherTypeMapper.update(weatherType);
    }

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    @Override
    public int delete(Long id) {
        return weatherTypeMapper.delete(id);
    }
}
