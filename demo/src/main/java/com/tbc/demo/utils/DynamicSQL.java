package com.tbc.demo.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 动态SQL拼接处理工具类，参数部分使用?|$|#代替。
 * 其中，$代表常量(替换时不加单引号)，#代表变量(替换时要加单引号)
 * created by gekangkang on 2021年3月11日17:06:43
 */
public class DynamicSQL {

    private StringBuilder sql = new StringBuilder();

    private ArrayList<Object> paramsList = new ArrayList<Object>();

    private Map<String, Object> paramsMap = new HashMap<>();

    /**
     * 参数不为NULL、“”的时候，拼接sqltmp。
     *
     * @param <T>
     * @param sqltmp
     * @param param
     * @return
     * @author gekangkang
     */
    public <T> DynamicSQL isNotEmpty(String sqltmp, T param) {
        if (param == null || "".equals(param)) {
            return this;
        }
        this.sql.append(sqltmp);
        addParameter(param, sqltmp);
        return this;
    }

    /**
     * 参数为NULL、“”的时候，拼接sqltmp。
     *
     * @param <T>
     * @param sqltmp
     * @param param
     * @return
     * @author gekangkang
     */
    public <T> DynamicSQL isEmpty(T param, String sqltmp) {
        if (param != null || !"".equals(param)) {
            return this;
        }
        this.sql.append(sqltmp);
        this.paramsList.add(param);
        addParameter(param, sqltmp);
        return this;
    }

    /**
     * param==cmpVal || param equals cmpVal 的时候拼接
     *
     * @param <T>
     * @param sqltmp
     * @param param
     * @param cmpVal
     * @return
     * @author gekangkang
     */
    public <T> DynamicSQL isNotEqual(T param, T cmpVal, String sqltmp) {
        if (this.isBaseType(param) && param == cmpVal) {
            return this;
        }
        if (!this.isBaseType(param) && param.equals(cmpVal)) {
            return this;
        }
        this.sql.append(sqltmp);
        this.paramsList.add(param);
        addParameter(param, sqltmp);
        return this;
    }

    /**
     * param!=cmpVal || param !equals cmpVal 的时候拼接
     *
     * @param <T>
     * @param sqltmp
     * @param param
     * @param cmpVal
     * @return
     * @author gekangkang
     */
    public <T> DynamicSQL isEqual(String sqltmp, T param, T cmpVal) {

        if (this.isBaseType(param) && param != cmpVal) {
            return this;
        }

        if (!this.isBaseType(param) && !param.equals(cmpVal)) {
            return this;
        }
        this.sql.append(sqltmp);
        this.paramsList.add(param);
        addParameter(param, sqltmp);
        return this;
    }

    /**
     * param!=null 时候拼接
     *
     * @param <T>
     * @param sqltmp
     * @param param
     * @return
     * @author gekangkang
     */
    public <T> DynamicSQL isNotNull(String sqltmp, T param) {
        if (param == null) {
            return this;
        }
        this.sql.append(sqltmp);
        this.paramsList.add(param);
        addParameter(param, sqltmp);
        return this;
    }

    /**
     * param==null 时候拼接
     *
     * @param <T>
     * @param sqltmp
     * @param param
     * @return
     * @author gekangkang
     */
    public <T> DynamicSQL isNull(T param, String sqltmp) {
        if (param != null) {
            return this;
        }
        this.sql.append(sqltmp);
        this.paramsList.add(param);
        addParameter(param, sqltmp);
        return this;
    }

    /**
     * 正常拼接
     *
     * @param sqltmp
     * @return
     * @author gekangkang
     */
    public DynamicSQL append(String sqltmp) {
        this.sql.append(sqltmp);
        return this;
    }

    /**
     * 获取动态拼接后的sql，参数?模式。
     *
     * @return
     * @author gekangkang
     */
    public String getSql() {

        String _sql = trimOf();

        return _sql.replaceAll("\\$", "?").replaceAll("#", "?");
    }

    /**
     * 获取参数的集合，有顺序与getSql()获取到的sql中的?参数一一对应。
     *
     * @return
     * @author gekangkang
     */
    public List<Object> getParamsList() {
        this.paramsList.trimToSize();
        return this.paramsList;
    }

    /**
     * 获取参数的集合，有顺序与getSql()获取到的sql中的?参数一一对应。
     *
     * @return
     * @author gekangkang
     */
    public Map<String, Object> getParamsMap() {
        return this.paramsMap;
    }


    /**
     * 获取参数的集合，有顺序与getSql()获取到的sql中的?参数一一对应。
     *
     * @param <T>
     * @param collection
     * @return
     * @author gekangkang
     */
    public <T extends Collection<Object>> T getParams(T collection) {
        this.paramsList.trimToSize();
        collection.addAll(this.paramsList);
        return collection;
    }

    /**
     * 获取参数的集合，有顺序与getSql()获取到的sql中的?参数一一对应。
     *
     * @param <T>
     * @param parameter
     * @param t
     * @return
     * @author gekangkang
     */
    public <T> T getParams(DynamicSQL.Parameter<T> parameter, T t) {
        this.paramsList.trimToSize();
        return parameter.addAll(paramsList, t);
    }

    /**
     * 返回参数赋值之后完整的sql
     */
    @Override
    public String toString() {
        Object[] pas = new String[this.paramsList.size()];
        for (int i = 0; i < pas.length; i++) {
            if (this.paramsList.get(i) == null) {
                pas[i] = null;
            } else {
                pas[i] = String.valueOf(this.paramsList.get(i));
            }
        }
        String _sql = trimOf();
        _sql = _sql.replaceAll("#", "'%s'").replaceAll("\\$", " %s ");

        return String.format(_sql, pas);
    }

    private boolean isBaseType(Object param) {
        return param instanceof Byte || param instanceof Short || param instanceof Integer || param instanceof Long
                || param instanceof Float || param instanceof Double || param instanceof Boolean
                || param instanceof Character || param == null;
    }

    private String trimOf() {
        String _sql = this.sql.toString().replaceAll("\t", " ");

        while (_sql.indexOf("  ") != -1) {
            _sql = _sql.replaceAll("  ", " ");
        }
        return _sql;
    }

    public interface Parameter<T> {
        public T addAll(ArrayList<Object> params, T t);
    }

    public void clear() {
        this.sql.setLength(0);
        this.paramsList.clear();
    }

    private <T> void addParameter(T param, String sqltmp) {
        if (StringUtils.isEmpty(sqltmp)) {
            return;
        }
        if (sqltmp.contains(":")) {
            String substring = sqltmp.substring(sqltmp.lastIndexOf(":"), sqltmp.length()) + " ";
            Matcher matcher = Pattern.compile("(?<=:)\\w*(?=\\s|%|'|\\|\\|)").matcher(substring);
            if (matcher.find()) {
                this.paramsMap.put(matcher.group(), param);
            }
        }
    }
}