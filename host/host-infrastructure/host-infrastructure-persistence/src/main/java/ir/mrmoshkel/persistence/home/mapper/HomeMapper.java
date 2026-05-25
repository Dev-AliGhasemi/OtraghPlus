package ir.mrmoshkel.persistence.home.mapper;

import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.persistence.framework.BaseMapper;
import ir.mrmoshkel.persistence.home.entity.HomeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeMapper extends BaseMapper {
    HomeMapper INSTANCE = Mappers.getMapper(HomeMapper.class);
    HomeModel toModel(HomeEntity entity);
    
    HomeEntity toEntity(HomeModel homeModel);

}