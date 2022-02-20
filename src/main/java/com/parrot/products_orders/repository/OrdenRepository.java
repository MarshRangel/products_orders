package com.parrot.products_orders.repository;

import com.parrot.products_orders.entity.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Ordenes, Long> {
}
