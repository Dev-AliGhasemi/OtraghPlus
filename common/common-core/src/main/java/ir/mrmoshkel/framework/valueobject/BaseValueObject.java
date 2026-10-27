package ir.mrmoshkel.framework.valueobject;

public abstract class BaseValueObject<T> {

    public abstract void validate();

    public T toData() {
        throw new UnsupportedOperationException();
    }

}
