package com.servicio.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servicio.productos.entity.Producto;

@Repository
public interface ProductoRespository extends JpaRepository<Producto, Long> {

}
