package ir.mrmoshkel.home.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jmolecules.event.annotation.DomainEvent;

@Getter
@NoArgsConstructor
@SuperBuilder
@DomainEvent
public class HomeDeletedEvent extends HomeEvent {

}
