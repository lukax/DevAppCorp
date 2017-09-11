package br.uff.ic.devappcorp.utils;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.util.StringUtils;

import javax.naming.OperationNotSupportedException;

public class Result<T> {

    private final boolean isSuccess;
    private final String error;
    private final T value;

    protected Result(T value, boolean isSuccess, String error) {
        if(isSuccess && StringUtils.hasText(error)) throw new IllegalArgumentException();
        if(!isSuccess && !StringUtils.hasText(error)) throw new IllegalArgumentException();

        this.value = value;
        this.isSuccess = isSuccess;
        this.error = error;
    }

    public static <T> Result fail(String error) {
        return new Result<T>(null, false, error);
    }

    public static <T> Result<T> ok(T value) {
        return new Result<T>(value, true, "");
    }

    public static Result ok(){
        return new Result<>(null, true, "");
    }

    public T value() {
        if(!isSuccess) throw new IllegalArgumentException();
        return value;
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure(){
        return !isSuccess;
    }

    public static Result combine(Result... results) {
        StringBuilder sb = new StringBuilder();
        boolean isFailure = false;
        for (Result r: results) {
            if(r.isFailure()){
                isFailure = true;
                sb.append(r.getError() + "\n");
            }
        }
        return isFailure ? Result.fail(sb.toString()) : Result.ok();
    }

}
