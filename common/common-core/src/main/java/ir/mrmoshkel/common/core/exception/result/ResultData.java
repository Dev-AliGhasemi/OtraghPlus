package ir.mrmoshkel.common.core.exception.result;

import ir.mrmoshkel.common.core.exception.errors.Error;
import lombok.Getter;

@Getter
public class ResultData<T> extends Result {
    private T data;

    public ResultData(T data, boolean isSuccess, Error error) {
        super(isSuccess,error);
        this.data = data;
    }
}
