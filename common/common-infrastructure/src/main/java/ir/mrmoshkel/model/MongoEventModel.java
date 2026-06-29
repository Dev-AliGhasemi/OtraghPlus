package ir.mrmoshkel.model;

import ir.mrmoshkel.framework.BaseEventModel;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@SuperBuilder
@Document("eventStore")
public class MongoEventModel<AGG_ID> extends BaseEventModel<AGG_ID> {
}
