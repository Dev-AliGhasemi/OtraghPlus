package ir.mrmoshkel.persistence.home.entity;

import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.persistence.framework.BaseEntity;
import ir.mrmoshkel.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "homes")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HomeEntity extends BaseEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false, updatable = false)
    private UserEntity host;
    @Column(name = "price_per_night", nullable = false)
    private Long pricePerNight;
    @Column(name = "address", nullable = false, length = 1000)
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "reserve_state", nullable = false)
    private ReserveState reserveState = ReserveState.READY_TO_RESERVED;
}
