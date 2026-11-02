package ir.mrmoshkel.common.infrastructure.framework;

import ir.mrmoshkel.common.core.framework.command.CommandConsumer;
import ir.mrmoshkel.common.core.framework.command.BaseCommand;

public interface CommandDispatcher {
    <ID,T extends BaseCommand<ID>> void registerCommand(Class<T> command, CommandConsumer<ID,T> commandConsumer);
    <ID> void dispatch(BaseCommand<ID> baseCommand);
}