package ir.mrmoshkel.common.core.user.event;

import ir.mrmoshkel.common.core.framework.event.BaseEvent;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public class UserEvent extends BaseEvent<UUID> {
}
