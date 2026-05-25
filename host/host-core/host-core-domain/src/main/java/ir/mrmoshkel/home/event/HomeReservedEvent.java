package ir.mrmoshkel.home.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.home.enumeration.ReserveState;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class HomeReservedEvent extends BaseEvent {
}
