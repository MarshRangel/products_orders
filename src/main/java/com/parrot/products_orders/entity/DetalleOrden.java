package com.parrot.products_orders.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "details_orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_orden"})})
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

    @Column(name = "precio_total")
    private float precio_total;

    @Column(name = "cantidad")
    private int cantidad;
}
