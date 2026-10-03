package ir.mrmoshkel.framework.command;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseCommand<ID> {
    private ID id;
}
