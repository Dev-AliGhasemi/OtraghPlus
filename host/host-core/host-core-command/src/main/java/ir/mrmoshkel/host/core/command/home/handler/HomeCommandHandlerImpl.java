package ir.mrmoshkel.host.core.command.home.handler;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.contract.IdGenerator;
import ir.mrmoshkel.host.core.domain.home.command.CreateHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.DeleteHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.ReserveHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.UpdateHomeCommand;
import ir.mrmoshkel.host.core.domain.home.entity.Home;
import lombok.AllArgsConstructor;
import org.jmolecules.architecture.cqrs.CommandHandler;

import java.util.UUID;

@AllArgsConstructor
public class HomeCommandHandlerImpl implements HomeCommandHandler {

    private EventSource<Home,UUID> eventSource;
    private IdGenerator<UUID> idGenerator;

    @CommandHandler
    public void handle(CreateHomeCommand createHomeCommand) {
        createHomeCommand.setId(idGenerator.generate());
        Home home = Home.createHome(createHomeCommand);
        eventSource.save(home);
    }

    @CommandHandler
    public void handle(ReserveHomeCommand reserveHomeCommand) {
        Home home = eventSource.getById(reserveHomeCommand.getId());
        home.reserveHome(reserveHomeCommand);
        eventSource.save(home);
    }

    @CommandHandler
    public void handle(UpdateHomeCommand updateHomeCommand) {
        Home home = eventSource.getById(updateHomeCommand.getId());
        home.update(updateHomeCommand);
        eventSource.save(home);
    }

    @CommandHandler
    public void handle(DeleteHomeCommand deleteHomeCommand) {
        Home home = eventSource.getById(deleteHomeCommand.getId());
        home.delete(deleteHomeCommand);
        eventSource.save(home);
    }
}
