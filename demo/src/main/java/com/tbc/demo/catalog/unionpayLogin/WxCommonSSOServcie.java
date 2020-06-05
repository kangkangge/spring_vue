package com.tbc.demo.catalog.unionpayLogin;


import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

public interface WxCommonSSOServcie {

    /**
     * 通过userId删除
     * @param id
     * @return
     */
    int deleteByUserId(String id);

    /**
     * 通过对接传来的userId删除
     * @param id
     * @return
     */
    int deleteByOtherId(String id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(WxCommonSSO record);

    /**
     * 通过userId查询
     * @param id
     * @return
     */
    WxCommonSSO selectByOtherId(String id);

    /**
     * 通过userId查询
     * @param id
     * @return
     */
    WxCommonSSO selectByUserId(String id);


    /**
     * 通过手机号查询
     * @param phone
     * @return
     */
    WxCommonSSO selectByPhone(String phone);

    /**
     * 通过userId更新userid corpcode 不允许修改
     * @param record
     * @return
     */
    int updateByUserId(WxCommonSSO record);


    /**
     * 通过therUserId更新userid corpcode 不允许修改
     * @param wxCommonSSO
     * @return
     */
    int updateByOtherUserId(WxCommonSSO wxCommonSSO);

    /**
     * 通过手机号更新userid corpcode 不允许修改
     * @param wxCommonSSO
     * @return
     */
    int updateByOtherPhone(WxCommonSSO wxCommonSSO);

    /**
     * 定时同步,没有就添加,有就删除
     * @param record
     * @return map   key:  updata 更新的总数    key: insert 新增的总数
     */
    Map<String, Integer> updateByList(List<WxCommonSSO> record);
}