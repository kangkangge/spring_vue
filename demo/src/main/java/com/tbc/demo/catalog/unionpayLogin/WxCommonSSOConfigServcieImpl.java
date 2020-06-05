package com.tbc.demo.catalog.unionpayLogin;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WxCommonSSOConfigServcieImpl implements WxCommonSSOConfigServcie {

    private JdbcTemplate jdbcTemplate = WxCommonTest.jdbcTemplate();

    @Override
    public int deleteByCorpCode(String corpCode) {
        Assert.hasText(corpCode, "corpCode can not be empty");
        return jdbcTemplate.update("delete from t_ps_wx_common_sso_config where corp_code =?", corpCode);
    }

    @Override
    public int insert(WxCommonSSOConfig wxCommonSSO) {
        Assert.notNull(wxCommonSSO, "insert data con not be empty");
        String sql = "insert into t_ps_wx_common_sso_config VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String id = UUID.randomUUID().toString().replace("-", "");
        Date date = new Date();
        Object[] param = {id, wxCommonSSO.getCorpCode(), wxCommonSSO.getDomain(), wxCommonSSO.getKey(), wxCommonSSO.getParam(), wxCommonSSO.getParam1(), wxCommonSSO.getParam2(), wxCommonSSO.getRemark(), wxCommonSSO.getCreateBy(), wxCommonSSO.getLastModifyBy(), date, date};
        return jdbcTemplate.update(sql, param);
    }

    @Override
    public WxCommonSSOConfig selectByCorpCode(String id) {
        Assert.hasText(id, "corpCode can not be empty");
        Object[] param = {id};
        List<WxCommonSSOConfig> query = jdbcTemplate.query("SELECT * FROM t_ps_wx_common_sso_config WHERE corp_code=?", param, new BeanPropertyRowMapper<>(WxCommonSSOConfig.class));
        return CollectionUtils.isEmpty(query) ? null : query.get(0);
    }

    @Override
    public int updateByCorpCode(WxCommonSSOConfig record) {
        Assert.notNull(record, "update data can not be empty");
        Assert.hasText(record.getCorpCode(), "corpCode can not be empty");
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<>();
        Date date = new Date();
        sql.append("update t_ps_wx_common_sso_config set ");
        Boolean flag = false;
        String key = record.getKey();
        String domain = record.getDomain();
        String param = record.getParam();
        String param1 = record.getParam1();
        String param2 = record.getParam2();
        flag = addParam(sql, params, flag, key, " key = ? ");
        flag = addParam(sql, params, flag, domain, " domain = ? ");
        flag = addParam(sql, params, flag, param, " param = ? ");
        flag = addParam(sql, params, flag, param1, " param1 = ? ");
        flag = addParam(sql, params, flag, param2, " param2 = ? ");
        if (flag) {
            sql.append(" , ");
        }
        sql.append(" last_modify_time = ?");
        params.add(date);
        sql.append(" where corp_code=? ");
        params.add(record.getCorpCode());
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    private Boolean addParam(StringBuffer sql, List<Object> params, Boolean flag, String param, String s) {
        if (!StringUtils.isEmpty(param)) {
            if (flag) {
                sql.append(" , ");
            }
            params.add(param);
            sql.append(s);
            return true;
        }
        return flag;
    }
}
