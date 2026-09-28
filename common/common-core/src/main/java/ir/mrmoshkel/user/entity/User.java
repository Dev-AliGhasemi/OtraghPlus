package ir.mrmoshkel.user.entity;

import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.user.command.CreateUserCommand;
import ir.mrmoshkel.user.command.UpdateUserCommand;
import ir.mrmoshkel.user.event.UserCreatedEvent;
import ir.mrmoshkel.user.event.UserUpdatedEvent;
import ir.mrmoshkel.user.valueobject.Email;
import ir.mrmoshkel.user.valueobject.Password;
import ir.mrmoshkel.user.valueobject.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class User extends AggregateRoot<Long> {
    private String firstName;
    private String lastName;
    private String username;
    private Password password;
    private Email email;
    private PhoneNumber phoneNumber;

    private User(String name, String firstName, String lastName, String username, Password password, Email email, PhoneNumber phoneNumber){
        super(name);
        raiseEvent(UserCreatedEvent.builder().firstName(firstName).lastName(lastName).username(username).password(password)
                .email(email).phoneNumber(phoneNumber).build());
    }

    public static User createUser(CreateUserCommand createUserCommand) {
        //TODO validation have to be done
        return new User(createUserCommand.getFirstName(), createUserCommand.getLastName(), createUserCommand.getUsername(),
                createUserCommand.getPassword(), createUserCommand.getEmail(), createUserCommand.getPhoneNumber());
    }

    public void update(UpdateUserCommand updateUserCommand) {
        //TODO validation have to be done
        raiseEvent(UserUpdatedEvent.builder().firstName(updateUserCommand.getFirstName()).lastName(updateUserCommand.getLastName())
                .username(updateUserCommand.getUsername()).password(updateUserCommand.getPassword()).email(updateUserCommand.getEmail())
                .phoneNumber(updateUserCommand.getPhoneNumber()).build());
    }

    private void apply(UserCreatedEvent userCreatedEvent) {
        this.firstName = userCreatedEvent.getFirstName();
        this.lastName = userCreatedEvent.getLastName();
        this.username = userCreatedEvent.getUsername();
        this.password = userCreatedEvent.getPassword();
        this.email = userCreatedEvent.getEmail();
        this.phoneNumber = userCreatedEvent.getPhoneNumber();
    }

    private void apply(UpdateUserCommand updateUserCommand) {
        this.firstName = updateUserCommand.getFirstName();
        this.lastName = updateUserCommand.getLastName();
        this.username = updateUserCommand.getUsername();
        this.password = updateUserCommand.getPassword();
        this.email = updateUserCommand.getEmail();
        this.phoneNumber = updateUserCommand.getPhoneNumber();
    }
}
