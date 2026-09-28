package ir.mrmoshkel.endpoint.home.v1.mapper;

import ir.mrmoshkel.endpoint.home.v1.dto.HomeDto;
import ir.mrmoshkel.framework.BaseMapper;
import ir.mrmoshkel.home.command.CreateHomeCommand;
import ir.mrmoshkel.home.model.HomeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeMapper extends BaseMapper {
    HomeMapper INSTANCE = Mappers.getMapper(HomeMapper.class);

    @Mapping(source = "pricePerNight.price", target = "pricePerNight")
    @Mapping(source = "address.street", target = "address")
    HomeDto toHomeDto(HomeModel homeModel);

    @Mapping(source = "pricePerNight", target = "pricePerNight.price")
    @Mapping(source = "address", target = "address.street")
    HomeModel toHomeModel(HomeDto homeDto);

    @Mapping(source = "pricePerNight", target = "pricePerNight.price")
    CreateHomeCommand toCreateHomeCommand(HomeDto homeDto);

}
