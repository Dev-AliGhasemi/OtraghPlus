package ir.mrmoshkel.commanddispatcher;

import ir.mrmoshkel.framework.CommandDispatcher;
import ir.mrmoshkel.framework.command.BaseCommand;
import ir.mrmoshkel.framework.command.CommandConsumer;

import java.util.HashMap;
import java.util.Map;


public class SimpleCommandDispatcher implements CommandDispatcher {

    public final Map<Class<? extends BaseCommand>, CommandConsumer> commandConsumerMap = new HashMap<>();

    @Override
    public <ID, T extends BaseCommand<ID>> void registerCommand(Class<T> command, CommandConsumer<ID, T> commandConsumer) {
        commandConsumerMap.put(command, commandConsumer);
    }

    @Override
    public <ID> void dispatch(BaseCommand<ID> baseCommand) {
        commandConsumerMap.get(baseCommand.getClass()).handle(baseCommand);
    }
}