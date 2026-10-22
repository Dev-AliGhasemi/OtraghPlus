package ir.mrmoshkel.framework.entity;


import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

    @Builder.Default
    private List<BaseEvent<ID>> events = new ArrayList<>();
    private static final boolean NEW_EVENT = true;
    @Setter
    private Long version;

    public AggregateRoot(ID id, String name) {
        super(id, name);
        events = new ArrayList<>();
    }


    public void markEventsAsCommited() {
        events.clear();
    }

    protected void raiseEvent(BaseEvent<ID> baseEvent) {
        applyChange(baseEvent, NEW_EVENT);
    }

    public void replayEvents(List<BaseEvent<ID>> events) {
        events.forEach(baseEvent -> applyChange(baseEvent, !NEW_EVENT));
    }

    private void applyChange(BaseEvent<ID> baseEvent, boolean isNewEvent) {
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
