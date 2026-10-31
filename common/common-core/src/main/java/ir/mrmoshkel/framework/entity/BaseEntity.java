package ir.mrmoshkel.framework.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class BaseEntity<ID> {
    @EqualsAndHashCode.Include
    @Identity
    protected ID id;
    @EqualsAndHashCode.Include
    protected String name;

    protected AuditDetail auditDetail;

    protected BaseEntity(ID id, String name) {
        this.name = name;
        this.id = id;
        validate();
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
