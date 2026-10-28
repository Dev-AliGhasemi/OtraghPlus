package ir.mrmoshkel.endpoint.home.v1;

import ir.mrmoshkel.endpoint.home.v1.dto.HomeDto;
import ir.mrmoshkel.endpoint.home.v1.mapper.HomeMapper;
import ir.mrmoshkel.endpoint.validation.OnCreate;
import ir.mrmoshkel.endpoint.validation.OnUpdate;
import ir.mrmoshkel.home.handler.command.home.command.handler.HomeBaseCommandHandler;
import ir.mrmoshkel.home.handler.query.HomeQueryHandler;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/home")
public class HomeV1Controller {

    //TODO command dispatcher to prevent inject many handlers
    use OPEN CQRS
    private final HomeQueryHandler homeQueryHandler;
    private final HomeBaseCommandHandler homeCommandHandler;

    private final HomeMapper homeMapper;

    public HomeV1Controller(HomeQueryHandler homeQueryHandler, HomeBaseCommandHandler homeCommandHandler,
                            HomeMapper homeMapper) {
        this.homeQueryHandler = homeQueryHandler;
        this.homeCommandHandler = homeCommandHandler;
        this.homeMapper = homeMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<HomeDto>> findAll(@PageableDefault(size = 20) @SortDefault(sort = "hostId", direction = Sort.Direction.ASC) Pageable pageable) {
        List<HomeDto> list = homeQueryHandler.handle(homeMapper.toFindAllHomeQuery(pageable.getOffset(), pageable.getPageSize()))
                .stream().map(HomeMapper.INSTANCE::toHomeDto).toList();
        return ResponseEntity.ok(new PageImpl<>(list, pageable, list.size()));
    }

    @GetMapping("/{homeId}")
    public HomeDto findById(@PathVariable("homeId") UUID homeId) {
        return homeMapper.toHomeDto(homeQueryHandler.handle(homeMapper.toFindByIdHomeQuery(homeId)));
    }

    @PostMapping
    public void createHome(@Validated(OnCreate.class) @RequestBody HomeDto homeDto) {
        homeCommandHandler.handle(homeMapper.toCreateHomeCommand(homeDto));
    }

    @DeleteMapping("/{homeId}")
    public void deleteHome(@PathVariable("homeId") UUID homeId) {
        homeCommandHandler.handle(homeMapper.toDeleteHomeCommand(homeId));
    }

    @PutMapping("/reserve/{homeId}")
    public void reserveHome(@PathVariable("homeId") UUID homeId) {
        homeCommandHandler.handle(homeMapper.toReserveHomeCommand(homeId));
    }

    @PutMapping("/{homeId}")
    public void updateHome(@PathVariable("homeId") UUID homeId, @Validated(OnUpdate.class) @RequestBody HomeDto homeDto) {
        homeCommandHandler.handle(homeMapper.toUpdateHomeCommand(homeDto, homeId));
    }
}
