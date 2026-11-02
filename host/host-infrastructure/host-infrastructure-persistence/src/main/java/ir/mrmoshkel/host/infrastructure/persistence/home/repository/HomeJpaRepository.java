package ir.mrmoshkel.host.infrastructure.persistence.home.repository;

import ir.mrmoshkel.host.core.domain.home.enumeration.ReserveState;
import ir.mrmoshkel.host.infrastructure.persistence.home.entity.HomeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface HomeJpaRepository extends JpaRepository<HomeEntity, UUID> {

    @Transactional
    @Query("UPDATE HomeEntity home SET home.reserveState = :reserveState WHERE home.id = :id")
    @Modifying
    void reserve(@Param("id") UUID id, @Param("reserveState") ReserveState reserveState);

    Page<HomeEntity> findAll(Pageable pageable);

}
