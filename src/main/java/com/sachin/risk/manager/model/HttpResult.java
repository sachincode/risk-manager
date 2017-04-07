package com.sachin.risk.manager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shicheng.zhang
 * @date 17-3-24
 * @time 上午11:20
 * @Description:
 */
public class HttpResult<T> {

    private static final int OK = 0;
    private static final int ERROR = -1;

    public final int status;
    public final String message;
    public final T data;

    public HttpResult() {
        this.status = OK;
        this.message = null;
        this.data = null;
    }

    @JsonCreator
    public HttpResult(@JsonProperty("status") int status, @JsonProperty("message") String message,
            @JsonProperty("data") T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> HttpResult<T> success() {
        return new HttpResult<>(OK, null, null);
    }

    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<>(OK, null, data);
    }

    public static <T> HttpResult<T> success(String message, T data) {
        return new HttpResult<>(OK, message, data);
    }

    public static <T> HttpResult<T> error() {
        return new HttpResult<>(ERROR, null, null);
    }

    public static <T> HttpResult<T> error(T data) {
        return new HttpResult<>(ERROR, null, data);
    }

    public static <T> HttpResult<T> error(String message, T data) {
        return new HttpResult<>(ERROR, message, data);
    }

}
