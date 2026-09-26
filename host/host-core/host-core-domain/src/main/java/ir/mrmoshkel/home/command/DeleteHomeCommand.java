package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DeleteHomeCommand extends BaseCommand<Long> {

}
