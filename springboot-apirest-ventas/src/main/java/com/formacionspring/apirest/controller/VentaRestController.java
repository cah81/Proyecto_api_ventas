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


import com.formacionspring.apirest.model.entity.Cliente;
import com.formacionspring.apirest.model.entity.Venta;
import com.formacionspring.apirest.service.ClienteService;
import com.formacionspring.apirest.service.ProductoService;
import com.formacionspring.apirest.service.VentaService;

@RestController
@RequestMapping("/api")

public class VentaRestController {
	@Autowired
	private VentaService servicio;
	//PARA VISUALIZAR LAS VENTAS
	@GetMapping({"/ventas","/"})
	public List<Venta> index(){
		
		return servicio.mostrarTodos();
		
	}
/*	//PARA VISUALIZAR LOS CLIENTES
	@GetMapping("/clientes")
	public List<Cliente> showClientes(){
		return servicio.mostrarClientes();
	}*/
/*	//PARA VISUALIZAR PRODUCTOS
	@GetMapping("/productos")
	public List<Cliente> showProductos(){
		return servicio.mostrarClientes();
	}*/
	
//MOSTRAR VENTAS POR ID
@GetMapping("/ventas/{id}")
public Venta show(@PathVariable long id) {
	return servicio.mostrarPorId(id);
}

//crear venta
@PostMapping("/ventas")
public ResponseEntity<?> create(@RequestBody Venta venta){
	Venta ventaNew = null;
	Map<String,Object> response = new HashMap<>();
	try {
		
		ventaNew = servicio.guardar(venta);
		
		
		
	}catch(DataAccessException e) {
		
		response.put("mensaje", "Error en la base de datos");
		response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	response.put("mensaje", "la venta se ha creado con exito");
	response.put("venta", ventaNew);
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

}


//actualizar ventas
@PutMapping("/ventas/{id}")
public ResponseEntity<?> update(@RequestBody Venta venta,@PathVariable Long id){
	Venta ventaUpdate = servicio.mostrarPorId(id);
	Map<String,Object> response = new HashMap<>();
	if(ventaUpdate == null) {
		
		response.put("mensaje", "no existe el registro con id: " + id);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	}
	try {
		
		ventaUpdate.setCliente(venta.getCliente());
		ventaUpdate.setProducto(venta.getProducto());
		ventaUpdate.setFecha_venta(venta.getFecha_venta());
		ventaUpdate.setTotal(venta.getTotal());
		
		
		
		servicio.guardar(ventaUpdate);
	}catch (DataAccessException e) {
		
		response.put("mensaje", "Error con la base de datos");
		response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
	}
	response.put("mensaje", "La venta se actualizo correctamente");
	response.put("venta", ventaUpdate);

	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);



}

//Eliminar la venta
@DeleteMapping("/ventas/{id}")
public ResponseEntity<?> delete(@PathVariable Long id){
	Venta  ventaBorrado = servicio.mostrarPorId(id);
	Map<String,Object> response = new HashMap<>();
	if(ventaBorrado == null) {
		response.put("mensaje","No existe registro con id : " + id);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
	}
	
try {	
	servicio.borrar(id);
}catch(DataAccessException e) {
	response.put("mensaje", "error con la base de datos");
	response.put("venta", ventaBorrado);
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);	
}
response.put("mensaje","El cliente ha sido eliminado con éxito");
response.put("cliente", ventaBorrado);
return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
}



}








