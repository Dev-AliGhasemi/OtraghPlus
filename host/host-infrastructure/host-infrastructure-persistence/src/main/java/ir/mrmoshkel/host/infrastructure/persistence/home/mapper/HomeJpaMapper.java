package ir.mrmoshkel.host.infrastructure.persistence.home.mapper;

import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import ir.mrmoshkel.common.infrastructure.framework.BaseMapper;
import ir.mrmoshkel.host.core.domain.home.valueobject.Address;
import ir.mrmoshkel.host.infrastructure.persistence.home.entity.AddressEmbeddable;
import ir.mrmoshkel.host.infrastructure.persistence.home.entity.HomeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeJpaMapper extends BaseMapper {
    HomeJpaMapper INSTANCE = Mappers.getMapper(HomeJpaMapper.class);
    @Mapping(source = "pricePerNight", target = "pricePerNight.price")
    @Mapping(source = "host.id", target = "hostId")
    HomeModel toModel(HomeEntity entity);

    @Mapping(source = "pricePerNight.price", target = "pricePerNight")
    @Mapping(source = "hostId", target = "host.id")
    HomeEntity toEntity(HomeModel homeModel);

    AddressEmbeddable map(Address address);
    Address map(AddressEmbeddable addressEmbeddable);

}