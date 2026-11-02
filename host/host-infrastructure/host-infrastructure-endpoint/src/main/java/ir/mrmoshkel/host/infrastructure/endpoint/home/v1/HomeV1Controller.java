package ir.mrmoshkel.host.infrastructure.endpoint.home.v1;

import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import ir.mrmoshkel.host.infrastructure.endpoint.home.v1.dto.HomeDto;
import ir.mrmoshkel.host.infrastructure.endpoint.home.v1.mapper.HomeMapper;
import ir.mrmoshkel.host.infrastructure.endpoint.validation.OnCreate;
import ir.mrmoshkel.host.infrastructure.endpoint.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.queryhandling.gateway.QueryGateway;
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
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/user/home")
@RequiredArgsConstructor
public class HomeV1Controller {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;
    private final HomeMapper homeMapper;

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<Page<HomeDto>>> findAll(@PageableDefault(size = 20) @SortDefault(sort = "hostId", direction = Sort.Direction.ASC) Pageable pageable) {
        return queryGateway.queryMany(homeMapper.toFindAllHomeQuery(pageable.getOffset(), pageable.getPageSize()), HomeModel.class)
                .thenApply(homeModels -> {
                    List<HomeDto> list = homeModels.stream().map(HomeMapper.INSTANCE::toHomeDto).toList();
                    return ResponseEntity.ok(new PageImpl<>(list, pageable, list.size()));
                });
    }

    @GetMapping("/{homeId}")
    public CompletableFuture<HomeDto> findById(@PathVariable("homeId") UUID homeId) {
        return queryGateway.query(homeMapper.toFindByIdHomeQuery(homeId), HomeModel.class).thenApply(HomeMapper.INSTANCE::toHomeDto);
    }

    @PostMapping
    public void createHome(@Validated(OnCreate.class) @RequestBody HomeDto homeDto) {
        commandGateway.send(homeMapper.toCreateHomeCommand(homeDto));
    }

    @DeleteMapping("/{homeId}")
    public void deleteHome(@PathVariable("homeId") UUID homeId) {
        commandGateway.send(homeMapper.toDeleteHomeCommand(homeId));
    }

    @PutMapping("/reserve/{homeId}")
    public void reserveHome(@PathVariable("homeId") UUID homeId) {
        commandGateway.send(homeMapper.toReserveHomeCommand(homeId));
    }

    @PutMapping("/{homeId}")
    public void updateHome(@PathVariable("homeId") UUID homeId, @Validated(OnUpdate.class) @RequestBody HomeDto homeDto) {
        commandGateway.send(homeMapper.toUpdateHomeCommand(homeDto, homeId));
    }
}
