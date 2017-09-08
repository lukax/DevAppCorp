package br.uff.ic.devappcorp.utils;

public class Result<T> {

    private final String reason;
    private final T result;

    public Result(String reason) {
        this.reason = reason;
        this.result = null;
    }

    public Result(T resultValue) {
        this.reason = null;
        this.result = resultValue;
    }

    public static <T> Result Failure(String reason) {
        return new Result<T>(reason);
    }

    public static <T> Result<T> Success(T resultValue) {
        return new Result<T>(resultValue);
    }

    public T getResult() {
        return result;
    }
}
