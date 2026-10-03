package ir.mrmoshkel.home.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public class HomeEvent extends BaseEvent<UUID> {
}
