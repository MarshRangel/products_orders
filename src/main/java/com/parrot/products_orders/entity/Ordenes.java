package com.parrot.products_orders.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_cliente"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ordenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "nombre_cliente")
    private String nombre_cliente;

    @Column(name = "precio")
    private double precio;

    @Column(name = "total")
    private double total;

    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    @Column(name = "fecha_recibida")
    private Date fecha_recibida;

    @ManyToOne
    private Usuarios usuario;

    @OneToOne(mappedBy = "orden")
    private DetalleOrden detalle;
}
