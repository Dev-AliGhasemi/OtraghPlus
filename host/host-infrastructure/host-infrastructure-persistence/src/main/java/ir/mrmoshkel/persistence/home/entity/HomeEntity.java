package ir.mrmoshkel.persistence.home.entity;

import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.persistence.framework.BaseEntity;
import ir.mrmoshkel.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Table(name = "homes")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HomeEntity extends BaseEntity<UUID> implements Persistable<UUID> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false, updatable = false)
    private UserEntity host;
    @Column(name = "price_per_night", nullable = false)
    private Long pricePerNight;
    @Embedded
    private AddressEmbeddable address;
    @Enumerated(EnumType.STRING)
    @Column(name = "reserve_state", nullable = false)
    private ReserveState reserveState = ReserveState.READY_TO_RESERVED;

    @Transient
    private boolean isNew = false;

    @PostPersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
