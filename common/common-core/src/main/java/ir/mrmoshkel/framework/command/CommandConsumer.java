package ir.mrmoshkel.framework.command;

@FunctionalInterface
public interface CommandConsumer<ID,T extends BaseCommand<ID>> {
    void handle(T t);
}
