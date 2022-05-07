package com.wbj.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbj.reggie.common.CustomException;
import com.wbj.reggie.entity.Category;
import com.wbj.reggie.entity.Dish;
import com.wbj.reggie.entity.Setmeal;
import com.wbj.reggie.mapper.CategoryMapper;
import com.wbj.reggie.mapper.DishMapper;
import com.wbj.reggie.mapper.SetmealMapper;
import com.wbj.reggie.service.CategoryService;
import com.wbj.reggie.service.DishService;
import com.wbj.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishMapper dishMapper;
//
//    @Autowired
//    private SetmealMapper setmealMapper;

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;


    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(dishLambdaQueryWrapper);
        if (count>0){
            throw new CustomException("当前页面关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count1 = setmealService.count(setmealLambdaQueryWrapper);
        if (count1>0){
            throw new CustomException("当前页面关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
