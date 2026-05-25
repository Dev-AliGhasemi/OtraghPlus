package ir.mrmoshkel.home.entity;

import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.home.command.CreateHomeCommand;
import ir.mrmoshkel.home.command.DeleteHomeCommand;
import ir.mrmoshkel.home.command.UpdateHomeCommand;
import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.home.event.HomeCreatedEvent;
import ir.mrmoshkel.home.event.HomeDeletedEvent;
import ir.mrmoshkel.home.event.HomeReservedEvent;
import ir.mrmoshkel.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@Getter
@SuperBuilder
public class Home extends AggregateRoot<Long> {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
    private ReserveState reserveState;

    private Home(Long hostId, Price pricePerNight, Address address, List<HomeFacilities> homeFacilities) {
        raiseEvent(HomeCreatedEvent.builder().hostId(hostId).price(pricePerNight).address(address).build());
    }

    public static Home createHome(CreateHomeCommand createHomeCommand) {
        if (Objects.isNull(createHomeCommand) || Objects.isNull(createHomeCommand.getHostId()) ||
                Objects.isNull(createHomeCommand.getAddress()) || Objects.isNull(createHomeCommand.getPricePerNight()))
            throw new IllegalStateException("Can not be null");
        return new Home(createHomeCommand.getHostId(), createHomeCommand.getPricePerNight(), createHomeCommand.getAddress(),
                createHomeCommand.getFacilities());
    }

    public void reserveHome() {
        raiseEvent(HomeReservedEvent.builder().build());
    }

    private void apply(HomeCreatedEvent homeCreatedEvent) {
        this.hostId = homeCreatedEvent.getHostId();
        this.pricePerNight = homeCreatedEvent.getPrice();
        this.address = homeCreatedEvent.getAddress();
        this.reserveState = ReserveState.READY_TO_RESERVED;
    }

    private void apply(HomeReservedEvent homeReservedEvent) {
        this.reserveState = ReserveState.RESERVED;
    }

    private void apply(HomeUpdatedEvent homeUpdatedEvent) {
        this.pricePerNight = homeUpdatedEvent.getPrice();
        this.address = homeUpdatedEvent.getAddress();
    }

    private void apply(HomeDeletedEvent homeDeletedEvent) {
        this.reserveState = ReserveState.HOME_DELETED;
    }


    public void update(UpdateHomeCommand updateHomeCommand) {
        //TODO check validation
        raiseEvent(HomeUpdatedEvent.builder().price(updateHomeCommand.getPricePerNight()).address(updateHomeCommand.getAddress()).build());
    }

    public void delete(DeleteHomeCommand deleteHomeCommand) {
        //TODO check validation
        raiseEvent(HomeDeletedEvent.builder().id(deleteHomeCommand.getId()).build());
    }
}
