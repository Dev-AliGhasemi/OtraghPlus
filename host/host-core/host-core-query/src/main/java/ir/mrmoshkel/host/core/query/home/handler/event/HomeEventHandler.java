package ir.mrmoshkel.host.core.query.home.handler.event;


import ir.mrmoshkel.common.infrastructure.framework.EventHandler;
import ir.mrmoshkel.host.core.domain.home.event.HomeCreatedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeDeletedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeReservedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import ir.mrmoshkel.host.core.query.home.repository.HomeRepository;
import org.jmolecules.event.annotation.DomainEventHandler;

public class HomeEventHandler implements EventHandler {

    private final HomeRepository homeRepository;

    public HomeEventHandler(HomeRepository homeRepository) {
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
