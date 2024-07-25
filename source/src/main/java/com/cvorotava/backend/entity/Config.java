package com.cvorotava.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "config")
public class Config implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String notification_emails;
    private Integer benjamin_year;
    private Integer alevin_year;
    private Integer infantil_year;
    private Integer cadete_year;
    private Integer juvenil_year;
    private Integer junior_year;
    private String mod_date;
}
