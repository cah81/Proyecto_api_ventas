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
import org.springframework.web.bind.annotation.RestController;


import com.formacionspring.apirest.model.entity.Proveedor;
import com.formacionspring.apirest.service.ProveedorService;

@RestController
@RequestMapping("/api")
public class ProveedorRestController {
	@Autowired
	private ProveedorService servicio;
	
	@GetMapping({"/proveedores","/"})
	public List<Proveedor> index(){
		
		return servicio.mostrarTodos();
		
	}
	
	@PostMapping("/proveedores")
	public ResponseEntity<?> create(@RequestBody Proveedor proveedor){
		Proveedor proveedorNew = null;
		Map<String,Object> response = new HashMap<>();
		try {
			
			
			proveedorNew = servicio.guardar(proveedor);
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
			response.put("mensaje", "El proveedor ha sido creado con exito");
			response.put("Proveedor", proveedorNew);
		
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/proveedores/{id}")
	public ResponseEntity<?> update(@RequestBody Proveedor proveedor,@PathVariable Long id){
		
		Proveedor proveedorUpdate = servicio.mostrarPorId(id);
		Map<String,Object> response = new HashMap<>();
		if(proveedorUpdate == null) {
			response.put("mensaje", "no existe el registro con id: " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			proveedorUpdate.setNombre(proveedor.getNombre());
			proveedorUpdate.setNif(proveedor.getNif());
			proveedorUpdate.setDireccion(proveedor.getDireccion());
			proveedorUpdate.setTelefono(proveedor.getTelefono());
			proveedorUpdate.setEmail(proveedor.getEmail());
			
			
			servicio.guardar(proveedorUpdate);
			
			
			
		}catch (DataAccessException e) {
			
			response.put("mensaje", "Error con la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El proveedor se actualizo correctamente");
		response.put("proveedor", proveedorUpdate);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/proveedores/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Proveedor proveedorBorrado = servicio.mostrarPorId(id);
		Map<String,Object> response = new HashMap<>();
		if(proveedorBorrado == null) {
			response.put("mensaje","No existe registro con id : " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
	try {	
		servicio.borrar(id);
	}catch(DataAccessException e) {
		response.put("mensaje", "error con la base de datos");
		response.put("proveedor", proveedorBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);	
	}
	response.put("mensaje","El cliente ha sido eliminado con éxito");
    response.put("proveedor", proveedorBorrado);
    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	

}
