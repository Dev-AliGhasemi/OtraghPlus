package ir.mrmoshkel.framework.entity;


import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

    @Builder.Default
    private List<BaseEvent<ID>> events = new ArrayList<>();
    private static final boolean NEW_EVENT = true;
    private Long version;

    protected AggregateRoot(ID id, String name) {
        super(id, name);
        events = new ArrayList<>();
    }


    public void markEventsAsCommited() {
        events.clear();
    }

    protected void raiseEvent(BaseEvent<ID> baseEvent) {
        applyChange(baseEvent, NEW_EVENT);
    }

    public void postInitialize(ID id, String name, Long version) {
        this.id = id;
        this.name = name;
        this.version = version;
        if (Objects.isNull(version))
            throw new IllegalArgumentException("version is null");
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
