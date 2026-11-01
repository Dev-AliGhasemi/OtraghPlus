package ir.mrmoshkel.common.core.user.command;

import ir.mrmoshkel.common.core.user.valueobject.Email;
import ir.mrmoshkel.common.core.user.valueobject.Password;
import ir.mrmoshkel.common.core.user.valueobject.PhoneNumber;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreateUserCommand extends UserCommand {
    private String firstName;
    private String lastName;
    private String username;
    private Password password;
    private Email email;
    private PhoneNumber phoneNumber;
}
