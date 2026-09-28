package ir.mrmoshkel.home.handler.command.home.command.handler;

import ir.mrmoshkel.contract.EventSource;
import ir.mrmoshkel.contract.IdGenerator;
import ir.mrmoshkel.framework.command.CommandHandler;
import ir.mrmoshkel.home.command.CreateHomeCommand;
import ir.mrmoshkel.home.command.DeleteHomeCommand;
import ir.mrmoshkel.home.command.ReserveHomeCommand;
import ir.mrmoshkel.home.command.UpdateHomeCommand;
import ir.mrmoshkel.home.entity.Home;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class HomeCommandHandler implements CommandHandler {

    private EventSource<Home,Long> eventSource;
    private IdGenerator<UUID> idGenerator;

    public void handle(CreateHomeCommand createHomeCommand) {
        createHomeCommand.setId(idGenerator.generate());
        Home home = Home.createHome(createHomeCommand);
        eventSource.save(home);
    }

    public void handle(ReserveHomeCommand reserveHomeCommand) {
        Home home = eventSource.getById(reserveHomeCommand.getId());
        home.reserveHome();
        eventSource.save(home);
    }

    public void handle(UpdateHomeCommand updateHomeCommand) {
        Home home = eventSource.getById(updateHomeCommand.getId());
        home.update(updateHomeCommand);
        eventSource.save(home);
    }

    public void handle(DeleteHomeCommand deleteHomeCommand) {
        Home home = eventSource.getById(deleteHomeCommand.getId());
        home.delete(deleteHomeCommand);
        eventSource.save(home);
    }
}
