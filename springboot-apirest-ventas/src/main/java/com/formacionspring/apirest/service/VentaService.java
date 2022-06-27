package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.model.entity.Cliente;
import com.formacionspring.apirest.model.entity.Producto;
import com.formacionspring.apirest.model.entity.Venta;


public interface VentaService {
	
	public List<Venta> mostrarTodos();
	public Venta mostrarPorId(Long id);
	public Venta guardar(Venta venta);
	public void borrar(Long id);
	
	//public List<Producto> mostrarProductos();
	public List<Cliente> mostrarClientes();
	
	public List<Producto> mostrarProductos();

}