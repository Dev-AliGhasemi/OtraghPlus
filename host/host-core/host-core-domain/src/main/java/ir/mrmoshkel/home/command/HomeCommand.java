package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@SuperBuilder
public abstract class HomeCommand extends BaseEvent<UUID> {

}
