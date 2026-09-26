package ir.mrmoshkel.framework.entity;


import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
public class AggregateRoot<ID> extends BaseEntity<ID> {

    private List<BaseEvent> events = new ArrayList<>();
    private static final boolean NEW_EVENT = true;
    @Setter
    private Long version;

    public void markEventsAsCommited() {
        events.clear();
    }

    protected void raiseEvent(BaseEvent baseEvent) {
        applyChange(baseEvent, NEW_EVENT);
    }

    public void replayEvents(List<BaseEvent> events) {
        events.forEach(baseEvent -> applyChange(baseEvent, !NEW_EVENT));
    }

    private void applyChange(BaseEvent baseEvent, boolean isNewEvent) {
        try {
            Method apply = getClass().getDeclaredMethod("apply", baseEvent.getClass());
            apply.setAccessible(true);
            apply.invoke(this, baseEvent);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            if (isNewEvent)
                events.add(baseEvent);
        }
    }
}
