package ir.mrmoshkel.home.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@SuperBuilder
public class HomeEvent extends BaseEvent<UUID> {
}
