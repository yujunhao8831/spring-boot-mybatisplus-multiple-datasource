package com.aidijing.controller;

import com.aidijing.order.domain.Order;
import com.aidijing.order.service.OrderService;
import com.aidijing.pay.PayService;
import com.aidijing.user.domain.User;
import com.aidijing.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
@RestController
public class DemoController {

    @Autowired
    private UserService  userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService   payService;


    @GetMapping( "users-tx" )
    public ResponseEntity< User > saveUser () {
        return ResponseEntity.ok(
                userService.save(
                        new User().setEmail( "yujunhao_8831@yahoo.com" )
                                  .setUsername( RandomStringUtils.randomNumeric( 1 , 10 ) )
                                  .setPassword( RandomStringUtils.randomNumeric( 1 , 10 ) )
                )
        );
    }

    @GetMapping( "users" )
    public ResponseEntity< List< User > > listUser () {
        return ResponseEntity.ok( userService.list() );
    }

    @GetMapping( "orders" )
    public ResponseEntity< List< Order > > listOrder () {
        return ResponseEntity.ok( orderService.list() );
    }

    @GetMapping( "pays" )
    public ResponseEntity< List< String > > listPay () {
        return ResponseEntity.ok( payService.list() );
    }

}
