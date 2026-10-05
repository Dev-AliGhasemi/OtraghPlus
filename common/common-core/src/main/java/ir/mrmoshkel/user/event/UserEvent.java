package ir.mrmoshkel.user.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public class UserEvent extends BaseEvent<UUID> {
}
