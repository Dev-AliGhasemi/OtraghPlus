package ir.mrmoshkel.endpoint.home.v1.mapper;

import ir.mrmoshkel.endpoint.home.v1.dto.HomeDto;
import ir.mrmoshkel.framework.BaseMapper;
import ir.mrmoshkel.home.command.CreateHomeCommand;
import ir.mrmoshkel.home.command.DeleteHomeCommand;
import ir.mrmoshkel.home.command.ReserveHomeCommand;
import ir.mrmoshkel.home.command.UpdateHomeCommand;
import ir.mrmoshkel.home.model.HomeModel;
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

}
