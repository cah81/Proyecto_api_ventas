package com.formacionspring.apirest;
/*
 * Crear una apirest completa en eclipse
	con estas entidades 
	Producto(id,nombre,cod_producto,
	tipo,precio,fecha_registro,cantidad,imagen)
	Proveedor(id,nombre,nif,email,telefono,direccion)
	Cliente(id,nombre,apellido,nif,email,telefono)
	Venta(id,id_producto,id_cliente,total,fecha_venta)
 * 
 * 
 * 
 * 
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =@Info(title ="Api Clientes",version="1.0",description ="Crud completo api restful"))
public class SpringbootApirestVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApirestVentasApplication.class, args);
	}

}
