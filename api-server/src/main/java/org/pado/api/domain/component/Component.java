package org.pado.api.domain.component;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.pado.api.domain.common.BaseTimeEntity;
import org.pado.api.domain.connection.Connection;
import org.pado.api.domain.project.Project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "components")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Component extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Component> children = new ArrayList<>();

    @OneToMany(mappedBy = "from_component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Connection> from_connections = new ArrayList<>();

    @OneToMany(mappedBy = "to_component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Connection> to_connections = new ArrayList<>();

    private Component parent;
    private Long version;
    private String name;
    private ComponentType type;
    private ComponentSubType subtype;
    private String thumnail;
    private LocalDateTime deploy_start_time;
    private LocalDateTime deploy_end_time;
}
