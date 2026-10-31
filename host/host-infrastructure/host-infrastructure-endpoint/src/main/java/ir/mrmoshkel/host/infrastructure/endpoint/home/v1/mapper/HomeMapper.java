package ir.mrmoshkel.host.infrastructure.endpoint.home.v1.mapper;

import ir.mrmoshkel.host.infrastructure.endpoint.home.v1.dto.HomeDto;
import ir.mrmoshkel.common.infrastructure.framework.BaseMapper;
import ir.mrmoshkel.host.core.domain.home.command.CreateHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.DeleteHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.ReserveHomeCommand;
import ir.mrmoshkel.host.core.domain.home.command.UpdateHomeCommand;
import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import ir.mrmoshkel.host.core.domain.home.query.FindAllHomeQuery;
import ir.mrmoshkel.host.core.domain.home.query.FindByIdHomeQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeMapper extends BaseMapper {
    HomeMapper INSTANCE = Mappers.getMapper(HomeMapper.class);

    @Mapping(source = "pricePerNight.price", target = "pricePerNight")
    HomeDto toHomeDto(HomeModel homeModel);

    @Mapping(source = "pricePerNight", target = "pricePerNight.price")
    HomeModel toHomeModel(HomeDto homeDto);

    @Mapping(source = "pricePerNight", target = "pricePerNight.price")
    CreateHomeCommand toCreateHomeCommand(HomeDto homeDto);

    @Mapping(source = "uuid", target = "id")
    DeleteHomeCommand toDeleteHomeCommand(UUID uuid);

    @Mapping(source = "uuid", target = "id")
    ReserveHomeCommand toReserveHomeCommand(UUID uuid);

    @Mapping(source = "homeDto.pricePerNight", target = "pricePerNight.price")
    @Mapping(source = "uuid", target = "id")
    UpdateHomeCommand toUpdateHomeCommand(HomeDto homeDto, UUID uuid);

    FindAllHomeQuery toFindAllHomeQuery(Long offset, Integer pageSize);

    @Mapping(source = "uuid", target = "id")
    FindByIdHomeQuery toFindByIdHomeQuery(UUID uuid);

}
