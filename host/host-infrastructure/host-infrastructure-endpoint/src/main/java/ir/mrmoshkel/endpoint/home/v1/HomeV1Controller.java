package ir.mrmoshkel.endpoint.home.v1;

import ir.mrmoshkel.endpoint.home.v1.dto.HomeDto;
import ir.mrmoshkel.endpoint.home.v1.mapper.HomeMapper;
import ir.mrmoshkel.endpoint.validation.OnCreate;
import ir.mrmoshkel.endpoint.validation.OnUpdate;
import ir.mrmoshkel.home.command.CreateHomeCommand;
import ir.mrmoshkel.home.command.DeleteHomeCommand;
import ir.mrmoshkel.home.command.ReserveHomeCommand;
import ir.mrmoshkel.home.command.UpdateHomeCommand;
import ir.mrmoshkel.home.handler.command.home.command.handler.HomeCommandHandler;
import ir.mrmoshkel.home.handler.query.HomeQueryHandler;
import ir.mrmoshkel.home.query.FindAllHomeQuery;
import ir.mrmoshkel.home.query.FindByIdHomeQuery;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.valueobject.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/home")
public class HomeV1Controller {

    //TODO command dispatcher to prevent inject many handlers
    private final HomeQueryHandler homeQueryHandler;
    private final HomeCommandHandler homeCommandHandler;

    private final HomeMapper homeMapper;

    public HomeV1Controller(HomeQueryHandler homeQueryHandler, HomeCommandHandler homeCommandHandler,
                            HomeMapper homeMapper) {
        this.homeQueryHandler = homeQueryHandler;
        this.homeCommandHandler = homeCommandHandler;
        this.homeMapper = homeMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<HomeDto>> findAll(
            @PageableDefault(size = 20)
            @SortDefault(sort = "hostId", direction = Sort.Direction.ASC)
            Pageable pageable) {
        List<HomeDto> homeDtos = homeQueryHandler.handle(FindAllHomeQuery.builder().offset(pageable.getOffset()).pageSize(pageable.getPageSize()).build())
                .stream().map(HomeMapper.INSTANCE::toHomeDto).toList();
        return ResponseEntity.ok(new PageImpl<>(homeDtos, pageable, homeDtos.size()));
    }

    @GetMapping("/{homeId}")
    public HomeDto findById(@PathVariable("homeId") Long homeId) {
        return homeMapper.toHomeDto(homeQueryHandler.handle(FindByIdHomeQuery.builder().id(homeId).build()));
    }

    @PostMapping
    public void createHome(@Validated(OnCreate.class) @RequestBody HomeDto homeDto) {
        homeCommandHandler.handle(homeMapper.toCreateHomeCommand(homeDto));
    }

    @DeleteMapping("/{homeId}")
    public void deleteHome(@PathVariable("homeId") Long homeId) {
        homeCommandHandler.handle(DeleteHomeCommand.builder().id(homeId).build());
    }

    @PutMapping("/reserve/{homeId}")
    public void reserveHome(@PathVariable("homeId") Long homeId) {
        homeCommandHandler.handle(ReserveHomeCommand.builder().id(homeId).build());
    }

    @PutMapping("/{homeId}")
    public void updateHome(@PathVariable Long homeId, @Validated(OnUpdate.class) @RequestBody HomeDto homeDto) {
        homeCommandHandler.handle(UpdateHomeCommand.builder().id(homeId)
                .pricePerNight(new Price(homeDto.getPricePerNight()))
                .address(new Address(homeDto.getAddress(), null, null, null, null)).build());
    }
}
