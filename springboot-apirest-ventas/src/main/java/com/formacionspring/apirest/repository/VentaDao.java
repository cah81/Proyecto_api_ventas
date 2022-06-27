package com.formacionspring.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.apirest.model.entity.Cliente;
import com.formacionspring.apirest.model.entity.Producto;
import com.formacionspring.apirest.model.entity.Proveedor;
import com.formacionspring.apirest.model.entity.Venta;
@Repository
public interface VentaDao extends CrudRepository<Venta, Long> {
	
	@Query("from Producto")
	public List<Producto> mostrarTodosProductos();
	
	@Query("from Cliente")
	public List<Cliente> mostrarTodosClientes();
	
	//@Query("From Proveedor")
	//public List<Proveedor> mostrarProveedores();
	
	
	

}
