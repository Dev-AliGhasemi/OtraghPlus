package ir.mrmoshkel.home.handler.event;


import ir.mrmoshkel.framework.EventHandler;
import ir.mrmoshkel.home.event.HomeCreatedEvent;
import ir.mrmoshkel.home.event.HomeDeletedEvent;
import ir.mrmoshkel.home.event.HomeReservedEvent;
import ir.mrmoshkel.home.event.HomeUpdatedEvent;
import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.repository.HomeRepository;

public class HomeEventHandler implements EventHandler {

    private final HomeRepository homeRepository;

    public HomeEventHandler(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }


    public void handle(HomeCreatedEvent homeCreatedEvent) {
        HomeModel homeModel = HomeModel.builder()
                .hostId(homeCreatedEvent.getHostId())
                .pricePerNight(homeCreatedEvent.getPrice())
                .address(homeCreatedEvent.getAddress())
                .build();
        homeRepository.save(homeModel);
    }

    public void handle(HomeUpdatedEvent homeUpdatedEvent) {
        homeRepository.findById(homeUpdatedEvent.getId()).ifPresentOrElse(homeModel -> {
            homeModel.setAddress(homeUpdatedEvent.getAddress());
            homeModel.setPricePerNight(homeUpdatedEvent.getPrice());
            homeRepository.save(homeModel);
        }, () -> {
            throw new RuntimeException("No entity found with id " + homeUpdatedEvent.getId());
        });
    }

    public void handle(HomeDeletedEvent homeDeletedEvent) {
        homeRepository.findById(homeDeletedEvent.getId()).ifPresentOrElse(homeModel -> homeRepository.deleteById(homeModel.getId()),
                () -> {
                    throw new RuntimeException("No entity found with id " + homeDeletedEvent.getId());
                });
    }

    public void handle(HomeReservedEvent homeReservedEvent) {
        homeRepository.findById(homeReservedEvent.getId()).ifPresentOrElse(homeRepository::reserve, () -> {
            throw new RuntimeException("No entity found with id " + homeReservedEvent.getId());
        });
    }
}
