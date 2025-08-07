package org.pado.api.domain.connection;

import org.pado.api.domain.common.BaseTimeEntity;
import org.pado.api.domain.component.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Connection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Component from_component;

    @ManyToOne(fetch = FetchType.LAZY)
    private Component to_component;

    private Long from_port;
    private Long to_port;
}
