package ir.mrmoshkel.common.core.user.command;

import ir.mrmoshkel.common.core.framework.command.BaseCommand;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public abstract class UserCommand extends BaseCommand<UUID> {
}
