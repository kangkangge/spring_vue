package com.tbc.demo.catalog.fanse;

import lombok.Data;

/**
 * @Classname FanSeTest
 * @Description TODO
 * @Date 2021/6/18 13:01
 * @Created by gekang
 */
@Data
public class FanSeUser {
    @ApiOperation(value = "名字")
    private String userName;
    @ApiOperation(value = "密码")
    private String password;

}
