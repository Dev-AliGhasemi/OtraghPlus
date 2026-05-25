package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class BaseEventModel<AGG_ID> {
    private Long id;
    private Date timestamp;
    private String aggregateType;
    private AGG_ID aggregateId;
    private String eventType;
    private long version;
    private BaseEvent eventData;
}