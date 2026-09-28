package ir.mrmoshkel.framework.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class BaseEntity<ID> {
    @EqualsAndHashCode.Include
    protected ID id;
    protected AuditDetail auditDetail;
}
