package org.utad.Service;

import org.utad.Model.Productos;

import java.util.List;

interface ProductService {
    //metodo de insertar todos los datos en la base de datos
    void insertAll(List<Productos> productos);

    //devuelve el listado de productos que tienen un stock por debajo del mínimo
    List<Productos> findLowStock(Integer min);


    //devuelve el listado de productos que tienen un precio dentro del rango establecido.
    List<Productos> priceIn(Double min, Double max);

}
