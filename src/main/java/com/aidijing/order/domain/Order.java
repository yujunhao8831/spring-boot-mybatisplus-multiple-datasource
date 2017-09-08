package com.aidijing.order.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
@Data
@EqualsAndHashCode( callSuper = true )
@Accessors( chain = true )
@TableName( "aidijing_order" )
public class Order extends Model< Order > {

    @TableId( value = "id", type = IdType.AUTO )
    private Long   id;
    @TableField( "order_id" )
    private String orderId;
    @TableField( "order_name" )
    private String orderName;

    @Override
    protected Serializable pkVal () {
        return this.id;
    }

}
