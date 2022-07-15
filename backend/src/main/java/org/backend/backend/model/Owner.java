package org.backend.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "owners")
@Getter
@Setter
@ToString
public class Owner {
    @Id
    @SequenceGenerator(name = "owner_id", sequenceName = "owner_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_id")
    @Column(name = "owner_id")
    private Long id;
    private String name;
    private String password;
}
