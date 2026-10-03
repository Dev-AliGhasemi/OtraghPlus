package ir.mrmoshkel.user.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import ir.mrmoshkel.user.valueobject.Email;
import ir.mrmoshkel.user.valueobject.Password;
import ir.mrmoshkel.user.valueobject.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserCommand extends UserCommand {
    private String firstName;
    private String lastName;
    private String username;
    private Password password;
    private Email email;
    private PhoneNumber phoneNumber;
}
