package ir.mrmoshkel.exception.result;

import lombok.Getter;
import ir.mrmoshkel.exception.errors.Error;
@Getter
public class Result {
    private boolean isSuccess;
    private Error error;

    protected Result(boolean isSuccess, Error error) {
        if (isSuccess && error == Error.NONE)
            throw new IllegalStateException();
        if (!isSuccess && error == Error.NONE)
            throw new IllegalStateException();
        this.isSuccess = isSuccess;
        this.error = error;
    }

    public static Result success(){
        return new Result(true,Error.NONE);
    }

    public static Result failure(Error error){
        return new Result(false,error);
    }

    public static<T> ResultData<T> success(T data){
        return new ResultData<>(data, true, Error.NONE);
    }
}

