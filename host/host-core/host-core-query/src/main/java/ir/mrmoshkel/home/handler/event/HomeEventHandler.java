package ir.mrmoshkel.home.handler.event;


import ir.mrmoshkel.framework.EventHandler;
import ir.mrmoshkel.home.event.HomeCreatedEvent;
import ir.mrmoshkel.home.event.HomeDeletedEvent;
import ir.mrmoshkel.home.event.HomeReservedEvent;
import ir.mrmoshkel.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.repository.HomeBaseRepository;
import org.jmolecules.event.annotation.DomainEventHandler;

public class HomeEventHandler implements EventHandler {

    private final HomeBaseRepository homeRepository;

    public HomeEventHandler(HomeBaseRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @DomainEventHandler
    public void handle(HomeCreatedEvent homeCreatedEvent) {
        HomeModel homeModel = HomeModel.builder()
                .id(homeCreatedEvent.getAggregateId())
                .hostId(homeCreatedEvent.getHostId())
                .pricePerNight(homeCreatedEvent.getPriceEventData().toValueObject())
                .address(homeCreatedEvent.getAddressEventData().toValueObject())
                .build();
        homeRepository.save(homeModel);
    }

    @DomainEventHandler
    public void handle(HomeUpdatedEvent homeUpdatedEvent) {
        homeRepository.findById(homeUpdatedEvent.getAggregateId()).ifPresentOrElse(homeModel -> {
            homeModel.setAddress(homeUpdatedEvent.getAddress().toValueObject());
            homeModel.setPricePerNight(homeUpdatedEvent.getPrice().toValueObject());
            homeRepository.save(homeModel,false);
        }, () -> {
            throw new RuntimeException("No entity found with id " + homeUpdatedEvent.getAggregateId());
        });
    }

    @DomainEventHandler
    public void handle(HomeDeletedEvent homeDeletedEvent) {
        homeRepository.findById(homeDeletedEvent.getAggregateId()).ifPresentOrElse(homeModel -> homeRepository.deleteById(homeModel.getId()),
                () -> {
                    throw new RuntimeException("No entity found with id " + homeDeletedEvent.getAggregateId());
                });
    }

    @DomainEventHandler
    public void handle(HomeReservedEvent homeReservedEvent) {
        homeRepository.findById(homeReservedEvent.getAggregateId()).ifPresentOrElse(homeRepository::reserve, () -> {
            throw new RuntimeException("No entity found with id " + homeReservedEvent.getAggregateId());
        });
    }
}
