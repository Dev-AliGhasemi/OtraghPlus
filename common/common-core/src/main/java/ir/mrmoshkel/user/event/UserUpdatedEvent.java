package ir.mrmoshkel.user.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.user.valueobject.Email;
import ir.mrmoshkel.user.valueobject.Password;
import ir.mrmoshkel.user.valueobject.PhoneNumber;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserUpdatedEvent extends BaseEvent {
    private String firstName;
    private String lastName;
    private String username;
    private Password password;
    private Email email;
    private PhoneNumber phoneNumber;
}
