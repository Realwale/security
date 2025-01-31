package com.keepo.springsecurity.data.resp;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseWrapper<T>{
    public T data;
    public boolean success;
    public String message;
    public int statusCode;
    public MetaData meta;


    public ResponseWrapper(T data, String message, int statusCode) {
        this.success = true;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }
    private ResponseWrapper(String message, int statusCode) {
        this.success = true;
        this.message = message;
        this.statusCode = statusCode;
    }
    private ResponseWrapper(String message, HttpStatus status) {
        this.success = false;
        this.message = message;
        this.statusCode = status.value();
    }

    private ResponseWrapper(T data, int statusCode) {
        this.success = false;
        this.data = data;
        this.statusCode = statusCode;
    }

    private ResponseWrapper(Pageable page, long totalElements, T data, String message, int statusCode) {
        meta = new MetaData();
        this.success = true;
        this.data = data;
        this.message = message;
        this.meta.size = page.getPageSize();
        this.meta.page = page.getPageNumber();
        this.meta.totalElements = totalElements;
        this.statusCode = statusCode;
    }

    public static <T> ResponseWrapper<T> of(Pageable page, long totalElements, T data, String message, int statusCode ) {
        return new ResponseWrapper<>(page, totalElements, data, message,statusCode);
    }

    public static <T> ResponseWrapper<T> ok(T data, String  message, int statusCode) {
        return new ResponseWrapper<>(data,message, statusCode);
    }

    public static <T> ResponseWrapper<T> ok(T data, int statusCode) {
        return new ResponseWrapper<>(data, statusCode);
    }

    public static <T> ResponseWrapper<T> of(String  message, int statusCode) {
        return new ResponseWrapper<>(message, statusCode);
    }

    public static <T> ResponseWrapper<T> failed(String message, int statusCode) {
        return new ResponseWrapper<>( message, statusCode);
    }

    public static <T> ResponseWrapper<T> created(T data, String message, int statusCode) {
        return new ResponseWrapper<>(data, message, statusCode);
    }

    public static <T> ResponseWrapper<T> failed(T data, int statusCode) {
        return new ResponseWrapper<>(data, statusCode);
    }

    public static <T> ResponseWrapper<T> message(String message, int statusCode) {
        return new ResponseWrapper<>( message, statusCode);
    }

    public static class MetaData {
        public int size;
        public int page;
        public long totalElements;
    }
}