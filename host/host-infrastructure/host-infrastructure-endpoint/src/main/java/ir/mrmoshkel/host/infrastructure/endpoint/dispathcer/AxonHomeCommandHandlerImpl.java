package ir.mrmoshkel.host.infrastructure.endpoint.dispathcer;

import ir.mrmoshkel.host.core.command.home.handler.HomeCommandHandler;
import ir.mrmoshkel.host.core.domain.home.command.CreateHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.DeleteHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.ReserveHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.UpdateHomeCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("axonHomeCommandHandler")
@RequiredArgsConstructor
public class AxonHomeCommandHandlerImpl implements HomeCommandHandler {

    @Qualifier("homeCommandHandler")
    private final HomeCommandHandler homeCommandHandler;

    @Override
    @CommandHandler
    public void handle(CreateHomeCommand createHomeCommand) {
        homeCommandHandler.handle(createHomeCommand);
    }

    @Override
    @CommandHandler
    public void handle(ReserveHomeCommand reserveHomeCommand) {
        homeCommandHandler.handle(reserveHomeCommand);
    }

    @Override
    @CommandHandler
    public void handle(UpdateHomeCommand updateHomeCommand) {
        homeCommandHandler.handle(updateHomeCommand);
    }

    @Override
    @CommandHandler
    public void handle(DeleteHomeCommand deleteHomeCommand) {
        homeCommandHandler.handle(deleteHomeCommand);
    }
}
