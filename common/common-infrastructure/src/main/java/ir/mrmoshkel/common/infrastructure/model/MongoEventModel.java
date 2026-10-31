package ir.mrmoshkel.common.infrastructure.model;

import ir.mrmoshkel.common.infrastructure.framework.BaseEventModel;
import ir.mrmoshkel.framework.event.BaseEvent;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@SuperBuilder
@Document("eventStore")
public class MongoEventModel<AGG_ID> extends BaseEventModel<AGG_ID> {
    public MongoEventModel() {
    }

    public MongoEventModel(String id, Date timestamp, String aggregateType, AGG_ID aggregateId, String eventType, long version, BaseEvent<AGG_ID> eventData) {
        super(id, timestamp, aggregateType, aggregateId, eventType, version, eventData);
    }
}
