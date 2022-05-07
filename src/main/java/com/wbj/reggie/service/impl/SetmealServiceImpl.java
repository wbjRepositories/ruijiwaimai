package com.wbj.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbj.reggie.entity.Setmeal;
import com.wbj.reggie.mapper.SetmealMapper;
import com.wbj.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper,Setmeal> implements SetmealService{
}
