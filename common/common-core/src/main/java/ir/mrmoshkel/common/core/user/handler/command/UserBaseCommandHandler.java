package ir.mrmoshkel.common.core.user.handler.command;

import ir.mrmoshkel.common.core.contract.EventSource;
import ir.mrmoshkel.common.core.framework.command.BaseCommandHandler;
import ir.mrmoshkel.common.core.user.command.CreateUserCommand;
import ir.mrmoshkel.common.core.user.command.UpdateUserCommand;
import ir.mrmoshkel.common.core.user.entity.User;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UserBaseCommandHandler implements BaseCommandHandler {

    private EventSource<User, UUID> eventSource;

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
