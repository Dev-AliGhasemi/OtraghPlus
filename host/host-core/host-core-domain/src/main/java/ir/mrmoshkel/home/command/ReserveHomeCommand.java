package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ReserveHomeCommand extends BaseCommand<Long> {

}
