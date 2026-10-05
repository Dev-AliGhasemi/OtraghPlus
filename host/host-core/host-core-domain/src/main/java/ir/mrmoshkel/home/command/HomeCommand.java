package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@SuperBuilder
public abstract class HomeCommand extends BaseCommand<UUID> {

}
