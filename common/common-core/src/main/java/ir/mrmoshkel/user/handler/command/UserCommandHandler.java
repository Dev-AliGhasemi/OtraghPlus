package ir.mrmoshkel.user.handler.command;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.framework.command.CommandHandler;
import ir.mrmoshkel.user.command.CreateUserCommand;
import ir.mrmoshkel.user.command.UpdateUserCommand;
import ir.mrmoshkel.user.entity.User;

public class UserCommandHandler implements CommandHandler {

    private EventSource<User,Long> eventSource;

    protected void handle(CreateUserCommand createUserCommand) {
        User home = User.createUser(createUserCommand);
        eventSource.save(home);
    }

    protected void handle(UpdateUserCommand updateUserCommand) {
        User user = eventSource.getById(updateUserCommand.getId());
        user.update(updateUserCommand);
        eventSource.save(user);
    }
}
