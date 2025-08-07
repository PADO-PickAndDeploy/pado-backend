package org.pado.api.domain.connection;

import org.pado.api.domain.component.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> findByFrom_component(Component fromComponent);
    List<Connection> findByTo_component(Component toComponent);
    List<Connection> findByFrom_port(Long fromPort);
    List<Connection> findByTo_port(Long toPort);
}
