package com.tbc.demo.catalog.jedis;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String info;
    private Date createDate;
    private int rank;
}
