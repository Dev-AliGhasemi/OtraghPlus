package ir.mrmoshkel.exception.errors;

public record Error(String code, String message) {
    public static final Error NONE = new Error("", "");
    public static Error NullValue = new Error("Error.NullValue", "Null value provided");
}
