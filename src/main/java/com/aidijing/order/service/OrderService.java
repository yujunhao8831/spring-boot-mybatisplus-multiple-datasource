package com.aidijing.order.service;

import com.aidijing.order.domain.Order;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
public interface OrderService extends IService< Order > {
    
    List<Order> list ();
}
