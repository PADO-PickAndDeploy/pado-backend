package org.pado.api.domain.credential;

import org.pado.api.domain.common.BaseTimeEntity;
import org.pado.api.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "credentials")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Credential extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;
    private String name;
    private String type;
    private String description;
    public String getCredentialType() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getCredentialType'");
        return this.type;
    }
}
