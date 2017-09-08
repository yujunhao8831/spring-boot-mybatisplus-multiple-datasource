package com.aidijing.user.domain;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("aidijing_user")
public class User extends Model< User > {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id", type = IdType.AUTO )
    private Long   id;
    private String username;
    private String password;
    private String email;


    @Override
    protected Serializable pkVal () {
        return this.id;
    }

}
