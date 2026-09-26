package ir.mrmoshkel.exception.result;

import ir.mrmoshkel.exception.errors.Error;
import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private T data;

    public ResultData(T data, boolean isSuccess, Error error) {
        super(isSuccess,error);
        this.data = data;
    }
}
