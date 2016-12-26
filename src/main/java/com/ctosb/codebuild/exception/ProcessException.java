package com.ctosb.codebuild.exception;

/**
 * 处理异常信息
 * 
 * @author Alan
 * @date 2015-11-21 上午01:01:21
 * 
 */
public class ProcessException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -230788805503655129L;

    public ProcessException() {}

    public ProcessException(String info) {
        super(info);
    }

    public ProcessException(Throwable throwable) {
        super(throwable);
    }

    public ProcessException(String info, Throwable throwable) {
        super(info, throwable);
    }

}
