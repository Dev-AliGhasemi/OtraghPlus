package ir.mrmoshkel.endpoint.home;

import ir.mrmoshkel.home.handler.query.HomeQueryHandler;
import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.query.FindAllHomeQuery;
import ir.mrmoshkel.home.query.FindByIdHomeQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/home")
public class HomeV1Controller {

    private final HomeQueryHandler homeQueryHandler;

    public HomeV1Controller(HomeQueryHandler homeQueryHandler) {
        this.homeQueryHandler = homeQueryHandler;
    }

    @GetMapping("/list")
    //TODO use HomeDto and also pagination. HomeDto for cutting dependency of view to data model.
    public List<HomeModel> findAll() {
        return homeQueryHandler.handle(new FindAllHomeQuery());
    }

    @GetMapping("/{homeId}")
    public HomeModel findById(@PathVariable("homeId") Long homeId) {
        return homeQueryHandler.handle(new FindByIdHomeQuery(homeId));
    }

}
