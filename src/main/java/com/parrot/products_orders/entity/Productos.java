package com.parrot.products_orders.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_producto"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String nombre_producto;

    @Column(name = "precio_unitario")
    private float precio_unitario;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne
    private Usuarios usuario;
}
