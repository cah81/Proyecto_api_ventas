package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.model.entity.Proveedor;

public interface ProveedorService {
	
	
	public List<Proveedor> mostrarTodos();
	public Proveedor mostrarPorId(Long id);
	public Proveedor guardar(Proveedor proveedor);
	public void borrar(Long id);
	

}
