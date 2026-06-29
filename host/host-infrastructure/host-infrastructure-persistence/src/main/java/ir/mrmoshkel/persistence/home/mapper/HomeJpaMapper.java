package ir.mrmoshkel.persistence.home.mapper;

import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.framework.BaseMapper;
import ir.mrmoshkel.persistence.home.entity.HomeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeJpaMapper extends BaseMapper {
    HomeJpaMapper INSTANCE = Mappers.getMapper(HomeJpaMapper.class);
    @Mapping(source = "pricePerNight", target = "pricePerNight.price")
    @Mapping(source = "address", target = "address.street")
    HomeModel toModel(HomeEntity entity);

    @Mapping(source = "pricePerNight.price", target = "pricePerNight")
    @Mapping(source = "address.street", target = "address")
    HomeEntity toEntity(HomeModel homeModel);

}