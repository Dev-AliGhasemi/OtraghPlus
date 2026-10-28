package ir.mrmoshkel.home.entity;

import ir.mrmoshkel.framework.entity.BaseAggregateRoot;
import ir.mrmoshkel.home.command.CreateHomeCommand;
import ir.mrmoshkel.home.command.DeleteHomeCommand;
import ir.mrmoshkel.home.command.ReserveHomeCommand;
import ir.mrmoshkel.home.command.UpdateHomeCommand;
import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.home.event.HomeCreatedEvent;
import ir.mrmoshkel.home.event.HomeDeletedEvent;
import ir.mrmoshkel.home.event.HomeReservedEvent;
import ir.mrmoshkel.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder
@AggregateRoot
public class Home extends BaseAggregateRoot<UUID> {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
    private ReserveState reserveState;

    private static final String NAME = "HOME";

    private Home(UUID id , Long hostId, Price pricePerNight, Address address) {
        super(id, NAME);
        raiseEvent(HomeCreatedEvent.builder().aggregateId(id).hostId(hostId).priceEventData(pricePerNight.toData()).addressEventData(address.toData()).build());
    }

    public static Home createHome(CreateHomeCommand createHomeCommand) {
        if(Objects.isNull(createHomeCommand))
            throw new IllegalArgumentException("Create home command can't be null");
        else if (Objects.isNull(createHomeCommand.getPricePerNight()))
            throw new IllegalArgumentException("Price per night can't be null");
        else if (Objects.isNull(createHomeCommand.getAddress()))
            throw new IllegalArgumentException("Address can't be null");
        else if (Objects.isNull(createHomeCommand.getHostId()))
            throw new IllegalArgumentException("HostId can't be null");
        return new Home(createHomeCommand.getId(), createHomeCommand.getHostId(), createHomeCommand.getPricePerNight(), createHomeCommand.getAddress());
    }

    public void reserveHome(ReserveHomeCommand reserveHomeCommand) {
        raiseEvent(HomeReservedEvent.builder().aggregateId(reserveHomeCommand.getId()).build());
    }

    private void apply(HomeCreatedEvent homeCreatedEvent) {
        this.id = homeCreatedEvent.getAggregateId();
        this.hostId = homeCreatedEvent.getHostId();
        this.pricePerNight = homeCreatedEvent.getPriceEventData().toValueObject();
        this.address = homeCreatedEvent.getAddressEventData().toValueObject();
        this.reserveState = ReserveState.READY_TO_RESERVED;
    }

    private void apply(HomeReservedEvent homeReservedEvent) {
        this.reserveState = ReserveState.RESERVED;
    }

    private void apply(HomeUpdatedEvent homeUpdatedEvent) {
        this.pricePerNight = homeUpdatedEvent.getPrice().toValueObject();
        this.address = homeUpdatedEvent.getAddress().toValueObject();
    }

    private void apply(HomeDeletedEvent homeDeletedEvent) {
        this.reserveState = ReserveState.HOME_DELETED;
    }


    public void update(UpdateHomeCommand updateHomeCommand) {
        if(Objects.isNull(updateHomeCommand))
            throw new IllegalArgumentException("Update home command can't be null");
        else if (Objects.isNull(updateHomeCommand.getPricePerNight()))
            throw new IllegalArgumentException("Price per night can't be null");
        else if (Objects.isNull(updateHomeCommand.getAddress()))
            throw new IllegalArgumentException("Address can't be null");
        raiseEvent(HomeUpdatedEvent.builder().aggregateId(updateHomeCommand.getId())
                .price(updateHomeCommand.getPricePerNight().toData())
                .address(updateHomeCommand.getAddress().toData())
                .build());
    }

    public void delete(DeleteHomeCommand deleteHomeCommand) {
        if(Objects.isNull(deleteHomeCommand))
            throw new IllegalArgumentException("Update home command can't be null");
        else if (Objects.isNull(deleteHomeCommand.getId()))
            throw new IllegalArgumentException("Id can't be null");
        raiseEvent(HomeDeletedEvent.builder().aggregateId(deleteHomeCommand.getId()).build());
    }

    @Override
    public String getName() {
        return NAME;
    }
}
