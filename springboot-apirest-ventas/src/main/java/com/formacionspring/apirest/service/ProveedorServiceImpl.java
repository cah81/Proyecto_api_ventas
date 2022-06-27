package com.formacionspring.apirest.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.formacionspring.apirest.model.entity.Proveedor;
import com.formacionspring.apirest.repository.ProveedorDao;

//aqui vamos a implementar la interface de servicio creada
//al principio marca roja por que hay implementar el metodo abstracto que esta
//solo declarado , si implemento una interfaces es obligatorio implementar 
//el metodo que se declaro
@Service
public class ProveedorServiceImpl implements ProveedorService {

	//llamo a clienteDao se puede implmentar por que ya tiene la anotacion component
	//se puede implmentar en cualquier lado dentro del contenedor principal
	@Autowired
	
	private ProveedorDao proveedorDao;
	
	
	
	
	
	//para mejorar rendimiento a 
	//la hora de consultas
	//aqui debemos listar todos los clientes que tenga para ello usamos del repositorio
	//dao que tiene las funciones para la base de datos
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> mostrarTodos() {
		// TODO Auto-generated method stub
		
		//cast se hace una revalidacion de lo que se va retornar
		//en este caso vamos retornar de tipo cliente
		return (List<Proveedor>) proveedorDao.findAll();
	}


	


	@Override
	@Transactional
	public Proveedor guardar(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return proveedorDao.save(proveedor);
	}


	@Override
	@Transactional
	public void borrar(Long id) {
		proveedorDao.deleteById(id);
		
	}


	@Override
	public Proveedor mostrarPorId(Long id) {
		// TODO Auto-generated method stub
		return proveedorDao.findById(id).orElse(null);
	}


	

}

