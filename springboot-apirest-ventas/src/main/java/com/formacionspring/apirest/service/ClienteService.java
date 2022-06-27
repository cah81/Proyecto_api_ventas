package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.model.entity.Cliente;


public interface ClienteService {
	
	public List<Cliente> mostrarTodos();
	public Cliente mostrarPorId(Long id);
	public Cliente guardar(Cliente cliente);
	public void borrar(Long id);

}
