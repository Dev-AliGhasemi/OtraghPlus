package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.command.CommandConsumer;
import ir.mrmoshkel.framework.command.BaseCommand;

public interface CommandDispatcher {
    <ID,T extends BaseCommand<ID>> void registerCommand(Class<T> command, CommandConsumer<ID,T> commandConsumer);
    <ID> void dispatch(BaseCommand<ID> baseCommand);
}