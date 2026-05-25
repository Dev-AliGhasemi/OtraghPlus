package ir.mrmoshkel.framework.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public abstract class BaseEntity<ID> {
    @EqualsAndHashCode.Include
    private ID id;
    private AuditDetail auditDetail;
}
