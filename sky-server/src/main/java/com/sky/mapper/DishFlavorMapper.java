package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品id集合批量删除口味数据
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id查询对应的口味数据
     * @param dishId
     * @return
     */
    @Select("SELECT * FROM dish_flavor WHERE dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
    
}
