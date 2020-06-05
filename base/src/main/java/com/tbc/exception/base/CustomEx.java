package com.tbc.exception.base;

import lombok.Data;

/**
 * 异常基类
 */
@Data
public class CustomEx extends Exception {

    private static final long serialVersionUID = -5420974096907472856L;

    public String causeBy;

    public CustomEx(String err) {
        super(err);
        setCauseBy(err);
    }

}
