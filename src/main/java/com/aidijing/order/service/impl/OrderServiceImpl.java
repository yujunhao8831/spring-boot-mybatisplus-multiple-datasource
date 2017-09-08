package com.aidijing.order.service.impl;

import com.aidijing.order.domain.Order;
import com.aidijing.order.mapper.OrderMapper;
import com.aidijing.order.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
@Service
public class OrderServiceImpl extends ServiceImpl< OrderMapper, Order > implements OrderService {
    @Override
    public List< Order > list () {
        return super.selectList( null );
    }
}
