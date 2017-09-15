package com.aidijing.user.service;

import com.aidijing.user.domain.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 披荆斩棘
 * @since 2017-09-08
 */
public interface UserService extends IService<User> {


    List<User> list ();

    User save ( User user );
}
