package ir.mrmoshkel.user.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public abstract class UserCommand extends BaseCommand<UUID> {
}
