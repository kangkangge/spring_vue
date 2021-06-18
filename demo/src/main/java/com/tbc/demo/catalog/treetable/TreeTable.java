package com.tbc.demo.catalog.treetable;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.common.model.TagManager;
import com.tbc.demo.config.DataSourceConfig;
import com.tbc.demo.utils.UUIDGenerate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

/**
 * 测试树形结构 , 优化递归查询问题
 */
@Slf4j
public class TreeTable {

    private NamedParameterJdbcTemplate template = DataSourceConfig.jdbcTemplate;

    @Test
    public void insertData() {
        String sql = "insert into t_tag_manager values (:tag_id,:parent_id,:tag_name,:corp_code,:create_by,:create_time,:last_modify_by,:last_modify_time,:opt_time,:use_count,:tag,:search_count,:status,:show_order,:tag_desc)";
        HashMap<String, Object> map = new HashMap<>();
        map.put("corp_code", "测试公司");
        map.put("parent_id", null);
        map.put("create_by", null);
        map.put("create_time", new Date());
        map.put("last_modify_by", "gkk");
        map.put("last_modify_time", new Date());
        map.put("opt_time", null);
        map.put("use_count", 0);
        map.put("tag", true);
        map.put("search_count", 2);
        map.put("status", null);
        map.put("tag_desc", "null");
        map.put("show_order", 123);
        for (int i = 0; i < 50; i++) {

            String uuid = UUIDGenerate.uuid();
            map.put("tag_id", uuid);
            map.put("tag_name", "1级菜单");
            template.update(sql, map);
            for (int j = 0; j < 50; j++) {
                String uuid1 = UUIDGenerate.uuid();
                map.put("tag_id", uuid1);
                map.put("tag_name", "2级菜单");
                map.put("parent_id", uuid);
                template.update(sql, map);
                for (int k = 0; k < 50; k++) {
                    map.put("tag_id", uuid1);
                    map.put("tag_name", "3级菜单");
                    map.put("parent_id", uuid1);
                    template.update(sql, map);
                }
            }
        }
    }

    public TagManager serialNumber(String first, String recursion, int startNumber, int count, List<TagManager> list) {
        if (StrUtil.hasBlank(first, recursion) || count < 1 || 0 < startNumber) {
            return null;
        }
        return null;
    }

    @Test
    public void test() {
        List<TagManager> objects = new ArrayList<>();
        for (int i = 0; i < 999; i++) {
            TagManager tagManager = new TagManager();
            tagManager.setTagId(i + "");
            System.out.println(new Date());
            tagManager.setCreateTime(new Date());
            objects.add(tagManager);
            objects.add(tagManager);
        }
        Set<TagManager> set = new TreeSet<>(Comparator.comparing(TagManager::getTagId));
        Set<TagManager> set1 = new TreeSet<>(Comparator.comparing(TagManager::getCreateTime));
        set1.addAll(set);
        set.addAll(objects);
        System.out.println(JSONObject.toJSONString(set1));
    }
}
