package ir.mrmoshkel.common.core.framework.valueobject;

public abstract class BaseValueObject<T> {

    public abstract void validate();

    public T toData() {
        throw new UnsupportedOperationException();
    }

}
