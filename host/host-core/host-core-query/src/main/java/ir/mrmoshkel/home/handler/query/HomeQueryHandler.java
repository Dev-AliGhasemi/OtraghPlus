package ir.mrmoshkel.home.handler.query;

import ir.mrmoshkel.framework.query.QueryHandler;
import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.query.FindAllHomeQuery;
import ir.mrmoshkel.home.query.FindByIdHomeQuery;
import ir.mrmoshkel.home.repository.HomeRepository;

import java.util.List;

public class HomeQueryHandler implements QueryHandler {
    private final HomeRepository homeRepository;

    public HomeQueryHandler(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<HomeModel> handle(FindAllHomeQuery findAllHomeQuery) {
        return homeRepository.findAll();
    }

    public HomeModel handle(FindByIdHomeQuery findByIdHomeQuery) {
        return homeRepository.findById(findByIdHomeQuery.getId())
                .orElseThrow(() -> new RuntimeException("No entity found with id " + findByIdHomeQuery.getId()));
    }


}
