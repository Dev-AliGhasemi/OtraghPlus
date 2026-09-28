package ir.mrmoshkel.framework.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class BaseEntity<ID> {
    @EqualsAndHashCode.Include
    protected ID id;
    @EqualsAndHashCode.Include
    protected String name;

    protected AuditDetail auditDetail;

    public BaseEntity(String name) {
        this.name = name;
    }

    public String getCode(){
        return "%s:%s".formatted(name, id);
    }
}
