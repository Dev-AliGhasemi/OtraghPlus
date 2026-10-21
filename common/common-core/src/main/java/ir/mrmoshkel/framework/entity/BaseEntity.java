package ir.mrmoshkel.framework.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class BaseEntity<ID> {
    @EqualsAndHashCode.Include
    @Setter
    protected ID id;
    @EqualsAndHashCode.Include
    @Setter
    protected String name;

    protected AuditDetail auditDetail;

    public BaseEntity(ID id, String name) {
        this.name = name;
        this.id = id;

    }

    public void validate() {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        else if (name == null)
            throw new IllegalArgumentException("name is null");
    }

    public String getCode(){
        return "%s:%s".formatted(name, id);
    }

    public abstract String getName();
}
