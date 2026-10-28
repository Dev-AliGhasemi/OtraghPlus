package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.Command;

@SuperBuilder
@Command
public class ReserveHomeCommand extends HomeCommand {

}
