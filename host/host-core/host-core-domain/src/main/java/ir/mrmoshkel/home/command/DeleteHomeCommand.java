package ir.mrmoshkel.home.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.Command;

@SuperBuilder
@Command
public class DeleteHomeCommand extends HomeCommand {

}
