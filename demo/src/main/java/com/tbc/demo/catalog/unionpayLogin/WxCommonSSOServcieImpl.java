package com.tbc.demo.catalog.unionpayLogin;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
public class WxCommonSSOServcieImpl implements WxCommonSSOServcie {


    private JdbcTemplate jdbcTemplate = WxCommonTest.jdbcTemplate();


    @Override
    public int deleteByUserId(String id) {
        Assert.hasText(id, "id can not be empty");
        return jdbcTemplate.update("delete from t_ps_wx_common_sso where user_id =?", id);
    }

    @Override
    public int deleteByOtherId(String id) {
        Assert.hasText(id, "id can not be empty");
        return jdbcTemplate.update("delete from t_ps_wx_common_sso where other_user_id =?", id);
    }

    @Override
    public int insert(WxCommonSSO wxCommonSSO) {
        Assert.notNull(wxCommonSSO, "add  data can not be empty");
        String id = UUID.randomUUID().toString().replace("-", "");
        Date date = new Date();
        Object[] param = {id, wxCommonSSO.getOtherUserId(), wxCommonSSO.getUserId(), wxCommonSSO.getCorpCode(), wxCommonSSO.getPhone(), wxCommonSSO.getParam(), wxCommonSSO.getParam1(), wxCommonSSO.getParam3(), wxCommonSSO.getParam3(), wxCommonSSO.getParam4(), date, date};
        return jdbcTemplate.update("insert into t_ps_wx_common_sso values (?,?,?,?,?,?,?,?,?,?,?,?)", param);
    }

    @Override
    public WxCommonSSO selectByOtherId(String id) {
        Object[] param = {id};
        List<WxCommonSSO> query = jdbcTemplate.query(" select * from t_ps_wx_common_sso where other_user_id=? ", param, new BeanPropertyRowMapper<>(WxCommonSSO.class));
        return CollectionUtils.isEmpty(query) ? null : query.get(0);
    }

    @Override
    public WxCommonSSO selectByUserId(String id) {
        Object[] param = {id};
        List<WxCommonSSO> query = jdbcTemplate.query(" select * from t_ps_wx_common_sso where user_id=? ", param, new BeanPropertyRowMapper<>(WxCommonSSO.class));
        return CollectionUtils.isEmpty(query) ? null : query.get(0);
    }

    @Override
    public WxCommonSSO selectByPhone(String phone) {
        Object[] param = {phone};
        List<WxCommonSSO> query = jdbcTemplate.query(" select * from t_ps_wx_common_sso where phone=? ", param, new BeanPropertyRowMapper<>(WxCommonSSO.class));
        return CollectionUtils.isEmpty(query) ? null : query.get(0);
    }

    @Override
    public int updateByUserId(WxCommonSSO wxCommonSSO) {
        Assert.notNull(wxCommonSSO, "wxCommonSSO can not be empty");
        Assert.hasText(wxCommonSSO.getUserId(), "wxCommonSSO can not be empty");
        return updateByCondition(wxCommonSSO, "user_id", wxCommonSSO.getUserId());
    }

    public int updateByOtherUserId(WxCommonSSO wxCommonSSO) {
        Assert.notNull(wxCommonSSO, "wxCommonSSO can not be empty");
        Assert.hasText(wxCommonSSO.getOtherUserId(), "wxCommonSSO can not be empty");
        return updateByCondition(wxCommonSSO, "other_user_id", wxCommonSSO.getOtherUserId());
    }

    public int updateByOtherPhone(WxCommonSSO wxCommonSSO) {
        Assert.notNull(wxCommonSSO, "wxCommonSSO can not be empty");
        return updateByCondition(wxCommonSSO, "phone", String.valueOf(wxCommonSSO.getPhone()));
    }

    public int updateByCondition(WxCommonSSO wxCommonSSO, String field, String where) {
        Assert.notNull(wxCommonSSO, "update data con not be empty");

        Date date = new Date();
        StringBuffer sql = new StringBuffer("update t_ps_wx_common_sso set ");
        List<Object> params = new ArrayList<>();
        Boolean flag = false;
        String phone = wxCommonSSO.getPhone();
        String param = wxCommonSSO.getParam();
        String param1 = wxCommonSSO.getParam1();
        String param2 = wxCommonSSO.getParam2();
        String param3 = wxCommonSSO.getParam3();
        String param4 = wxCommonSSO.getParam4();
        flag = addParam(sql, params, flag, phone, " phone = ? ");
        flag = addParam(sql, params, flag, param, " param = ? ");
        flag = addParam(sql, params, flag, param1, " param1 = ? ");
        flag = addParam(sql, params, flag, param2, " param2 = ? ");
        flag = addParam(sql, params, flag, param3, " param3 = ? ");
        flag = addParam(sql, params, flag, param4, " param4 = ? ");
        if (flag) {
            sql.append(" , ");
        }
        sql.append(" last_modify_time = ? ");
        params.add(date);
        sql.append(" where " + field + " =? ");
        params.add(where);
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public Map<String, Integer> updateByList(List<WxCommonSSO> record) {
        if (CollectionUtils.isEmpty(record)) {
            throw new IllegalArgumentException("WxCommonSSO can not be empty");
        }
        Map<String, Integer> result = new HashMap<>();
        log.info("共计传入总数为:[{}]", record.size());
        List<List<WxCommonSSO>> lists = ListPageUtil.splitList(record, 200);
        int updata = 0;
        int insert = 0;
        for (List<WxCommonSSO> fromlist : lists) {
            //批量查询有效数据
            String sql = "select * from t_ps_wx_common_sso where user_id in  (:userIds)";
            Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
            param.put("userIds", fromlist.stream().map(WxCommonSSO::getUserId).collect(toList()));
            NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                    new NamedParameterJdbcTemplate(jdbcTemplate);
            List<WxCommonSSO> queryList = namedParameterJdbcTemplate.query(sql, param, new BeanPropertyRowMapper<WxCommonSSO>(WxCommonSSO.class));
            List<String> userIds = queryList.stream().map(WxCommonSSO::getUserId).collect(toList());
            for (WxCommonSSO wxCommonSSO : fromlist) {
                //需要更新的数据
                if (userIds.contains(wxCommonSSO.getUserId())) {
                    updateByUserId(wxCommonSSO);
                    updata += 1;
                } else {
                    //需要新增的数据
                    insert(wxCommonSSO);
                    insert += 1;
                }


            }
            log.info("共计添加:[{}]条,更新:[{}]条数据", insert, updata);
        }
        result.put("updata", updata);
        result.put("insert", insert);
        return result;
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
