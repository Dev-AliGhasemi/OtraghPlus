package ir.mrmoshkel.host.core.domain.home.command;

import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.Command;

@SuperBuilder
@Command
public class DeleteHomeCommand extends HomeCommand {

}
