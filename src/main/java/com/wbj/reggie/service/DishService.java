package com.wbj.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbj.reggie.entity.Dish;
import com.wbj.reggie.entity.DishDto;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
