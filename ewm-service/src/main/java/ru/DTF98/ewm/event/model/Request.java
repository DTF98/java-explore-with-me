package ru.DTF98.ewm.event.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.DTF98.ewm.event.enums.RequestState;
import ru.DTF98.ewm.user.model.User;

import jakarta.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ewm_requests")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestState status = RequestState.PENDING;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private Timestamp created;
}
