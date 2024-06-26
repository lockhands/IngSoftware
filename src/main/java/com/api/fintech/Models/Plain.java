package com.api.fintech.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "plains", schema = "public")
public class Plain {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_plain")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "status", length = 1)
    private String status;

}
