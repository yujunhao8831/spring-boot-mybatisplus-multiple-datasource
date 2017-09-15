package com.aidijing.pay.impl;

import com.aidijing.pay.PayService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/15
 */
@Service
public class PayServiceImpl implements PayService {
    @Override
    public List< String > list () {
        List< String > pays = new ArrayList<>();
        for ( int i = 0 ; i < 10 ; i++ ) {
            pays.add( RandomStringUtils.randomNumeric( i ) );
        }
        return pays;
    }
}
