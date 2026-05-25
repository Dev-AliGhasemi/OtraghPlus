package ir.mrmoshkel.framework.command;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseCommand<ID> {
    private ID id;
}
