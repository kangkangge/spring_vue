package com.tbc.demo.catalog.unionpayLogin;

public interface WxCommonSSOConfigServcie {

    int deleteByCorpCode(String corpCode);

    int insert(WxCommonSSOConfig wxCommonSSO);

    WxCommonSSOConfig selectByCorpCode(String id);

    /**
     * 通过传入对象的corpCode更新对应的值
     * @param record
     * @return
     */
    int updateByCorpCode(WxCommonSSOConfig record);
}