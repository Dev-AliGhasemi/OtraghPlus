package ir.mrmoshkel.host.entity;

import ir.mrmoshkel.user.entity.User;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class Host extends User {
    private List<Long> bookingIds;
}
