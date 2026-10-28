package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.Command;

import java.util.UUID;

@SuperBuilder
@Command
public abstract class HomeCommand extends BaseCommand<UUID> {

}
