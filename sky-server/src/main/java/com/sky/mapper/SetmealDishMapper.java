package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.entity.SetmealDish;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询关联的套餐id列表
     * @param dishIds
     * @return
     */
    //select setmeal_id from setmeal_dish where dish_id in (1,2,3)
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    /**
     * 批量插入套餐和菜品的关联关系数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id列表删除关联关系数据
     * @param id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);
    /* void deleteBySetmealIds(List<Long> ids); */

    /**
     * 根据菜品id列表查询关联关系数据
     * @param id
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
    
}
