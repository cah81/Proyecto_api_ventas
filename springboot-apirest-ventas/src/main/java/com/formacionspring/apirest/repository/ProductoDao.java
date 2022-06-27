package com.formacionspring.apirest.repository;

import org.springframework.data.repository.CrudRepository;

import com.formacionspring.apirest.model.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}
