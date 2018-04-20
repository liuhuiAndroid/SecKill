package com.lh.seckill.exception;

import com.lh.seckill.result.CodeMsg;

/**
 * Created by lh on 2018/4/19.
 */
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}