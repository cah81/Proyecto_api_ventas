package com.formacionspring.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apirest.model.entity.Cliente;
import com.formacionspring.apirest.model.entity.Producto;
import com.formacionspring.apirest.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService servicio;
	//=============MOSTRAR LISTA TODOS LOS CLIENTES ==========================
	@GetMapping({"/clientes","/"})
	public List<Cliente> index(){
		return servicio.mostrarTodos();
	}
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		
		 Cliente cliente = null;
	        Map<String,Object>  response = new HashMap<>();

	        try {

	            cliente = servicio.mostrarPorId(id);

	        } catch (DataAccessException e) {
	            response.put("mensaje", "Error al realizar en base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        if(cliente == null) {
	            response.put("mensaje", "El producto con ID: "+id+" no existe en la base de datos");
	            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	    }

	
	
	//===============CREAR CLIENTE NUEVO =======================================
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente){
		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		try {
			clienteNew = servicio.guardar(cliente);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente se ha creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	//===============ACTUALIZAR CLIENTE ====================================================
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente,@PathVariable Long id){
		Cliente clienteUpdate = servicio.mostrarPorId(id);
		Map<String,Object> response = new HashMap<>();
		if(clienteUpdate == null) {
			response.put("mensaje", "no existe el registro con id: " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			clienteUpdate.setNombre(cliente.getNombre());
			clienteUpdate.setNif(cliente.getNif());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setEmail(cliente.getEmail());
			
			
			servicio.guardar(clienteUpdate);
			
			
		}catch (DataAccessException e) {
			
			response.put("mensaje", "Error con la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			response.put("mensaje", "El cliente se actualizo correctamente");
			response.put("cliente", clienteUpdate);
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Cliente clienteBorrado = servicio.mostrarPorId(id);
		Map<String,Object> response = new HashMap<>();
		if(clienteBorrado == null) {
			response.put("mensaje","No existe registro con id : " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
	try {	
		servicio.borrar(id);
	}catch(DataAccessException e) {
		response.put("mensaje", "error con la base de datos");
		response.put("cliente", clienteBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);	
	}
	response.put("mensaje","El cliente ha sido eliminado con éxito");
    response.put("cliente", clienteBorrado);
    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
		
}
