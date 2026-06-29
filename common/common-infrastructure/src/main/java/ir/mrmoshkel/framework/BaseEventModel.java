package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class BaseEventModel<AGG_ID> {
    @Id
    protected String id;
    protected Date timestamp;
    protected String aggregateType;
    protected AGG_ID aggregateId;
    protected String eventType;
    protected long version;
    protected BaseEvent eventData;
}