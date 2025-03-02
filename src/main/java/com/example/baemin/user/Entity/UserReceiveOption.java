package com.example.baemin.user.Entity;

import com.example.baemin.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_receive_options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserReceiveOption extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "receive_option_id")
    private Long receiveOptionId;

    @Column(name = "is_agreed", nullable = false)
    private boolean isAgreed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_option_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ReceiveOption receiveOption;

    @Builder
    public UserReceiveOption(Long userId, Long receiveOptionId, boolean isAgreed) {
        this.userId = userId;
        this.receiveOptionId = receiveOptionId;
        this.isAgreed = isAgreed;
    }
}