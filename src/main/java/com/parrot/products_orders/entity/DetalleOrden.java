package com.parrot.products_orders.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "details", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_orden"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrden {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_orden", nullable = false)
    private String nombre_orden;

    @Column(name = "precio")
    private float precio;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "total")
    private float total;

    @OneToOne
    private Ordenes orden;

    @ManyToOne
    private Productos producto;
}
