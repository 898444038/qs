package com.chat.qs.mapper;

import com.chat.qs.entity.WeatherType;
import com.chat.qs.vo.WeatherTypeVo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 天气类型
 * @author: Administrator
 * @date: 2020-04-24
 */
@Mapper
public interface WeatherTypeMapper {

	/**
	 * 查询列表
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    List<WeatherType> selectList(WeatherTypeVo weatherTypeVo);

	/**
	 * 查询详情
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    WeatherType selectOne(WeatherTypeVo weatherTypeVo);

	/**
	 * 新增
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    int insert(WeatherType weatherType);

	/**
	 * 根据id更新
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    int update(WeatherType weatherType);

	/**
	 * 根据id删除
	 * @author: Administrator
	 * @date: 2020-04-24
	 */
    int delete(Long id);

}
