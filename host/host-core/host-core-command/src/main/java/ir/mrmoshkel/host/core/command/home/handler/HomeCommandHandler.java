package ir.mrmoshkel.host.core.command.home.handler;

import ir.mrmoshkel.framework.command.BaseCommandHandler;
import ir.mrmoshkel.host.core.domain.home.command.CreateHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.DeleteHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.ReserveHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.UpdateHomeCommand;


public interface HomeCommandHandler extends BaseCommandHandler {
    void handle(CreateHomeCommand createHomeCommand);

    void handle(ReserveHomeCommand reserveHomeCommand) ;

    void handle(UpdateHomeCommand updateHomeCommand) ;

    void handle(DeleteHomeCommand deleteHomeCommand);
}
