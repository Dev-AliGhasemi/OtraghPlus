package ir.mrmoshkel.persistence.home.repository;

import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.persistence.home.entity.HomeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HomeJpaRepository extends JpaRepository<HomeEntity, Long> {

    @Transactional
    @Query("UPDATE HomeEntity home SET home.reserveState = :reserveState WHERE home.id = :id")
    @Modifying
    void reserve(@Param("id") Long id, @Param("reserveState") ReserveState reserveState);

    Page<HomeEntity> findAll(Pageable pageable);

}
