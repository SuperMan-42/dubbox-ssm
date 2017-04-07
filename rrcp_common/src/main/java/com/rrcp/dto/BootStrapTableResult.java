package com.rrcp.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author hpw
 *         <p>
 *         ajax 请求的返回类型封装JSON结果
 *         <p>
 *         主要用于bootstrap table
 */
public class BootStrapTableResult<T> implements Serializable {


    private static final long serialVersionUID = -4185151304730685014L;


    private List<T> data;


    public BootStrapTableResult(List<T> data) {
        super();
        this.data = data;
    }


    public List<T> getData() {
        return data;
    }


    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BootStrapTableResult [" + "data=" + data + ']';
    }
}
