INSERT INTO proveedor(direccion, email, nif, nombre, telefono) VALUES ("Barcelona","test1@gmail.com","018336A","empresas del mar",6963325);
INSERT INTO proveedor(direccion, email, nif, nombre, telefono) VALUES ("madrid","test2@gmail.com","018336A","atunes no mas",6963325);
INSERT INTO proveedor(direccion, email, nif, nombre, telefono) VALUES ("Cadiz","tes3t@gmail.com","018336A","mercadona",6963325);
INSERT INTO proveedor(direccion, email, nif, nombre, telefono) VALUES ("Valencia","test4@gmail.com","018336A","ahorra mas",6963325);
INSERT INTO proveedor(direccion, email, nif, nombre, telefono) VALUES ("bilbao","test5@gmail.com","018336A","Del monte",6963325);



INSERT INTO producto ( cantidad, cod_producto, fecha_registro,nombre, precio, tipo) VALUES (10,"123456789","2022-06-16","atun del mar",10,"Enlatado");
INSERT INTO producto ( cantidad, cod_producto, fecha_registro,nombre, precio, tipo) VALUES (5,"15856789","2022-06-16","sardina",10,"Enlatado");
INSERT INTO producto ( cantidad, cod_producto, fecha_registro,nombre, precio, tipo) VALUES (10,"129656789","2022-06-16","mariscos",10,"Enlatado");
INSERT INTO producto ( cantidad, cod_producto, fecha_registro,nombre, precio, tipo) VALUES (10,"122456789","2022-06-16","vieiras",10,"Enlatado");
INSERT INTO producto ( cantidad, cod_producto, fecha_registro,nombre, precio, tipo) VALUES (10,"123456779","2022-06-16","cangrejo",10,"Enlatado");


INSERT INTO clientes (nombre, apellido, email, nif, telefono) VALUES ("carlos","Angulo","carles1981@gmail.com","y8955555",589632);
INSERT INTO clientes (nombre, apellido, email, nif, telefono) VALUES ("Jose","Moreno","tesss@gmail.com","ccdcd555",98655232);
INSERT INTO clientes (nombre, apellido, email, nif, telefono) VALUES ("rosana","maguina","test3@gmail.com","y8vvv55555",123456789);



INSERT INTO ventas( fecha_venta, total,id_producto,id_cliente) VALUES ("2022-05-15",5.5,1,1);