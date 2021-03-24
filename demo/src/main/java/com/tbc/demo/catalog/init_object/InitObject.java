package com.tbc.demo.catalog.init_object;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/7/30 16:31
 */
@Data
public class InitObject {

    @Test
    public void initTest(){
        String obj=null;
        obj="111";
        System.out.println(obj);
    }

}
