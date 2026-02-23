package org.utad.LecturaCSV;

import org.utad.Model.Productos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImporterService {

    public List<Productos> leerCSV(String fileName) {

        //creamos nueva lista
        List<Productos> listProductos = new ArrayList<>();

        //el input stream con utf8 para los acentos

        try {
            //configramos buffered reader
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String linea;

            //si hay cabecera la saltamos
            // Saltar cabecera
            br.readLine();

            //bucle while para leer
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                //los separamos por las comas del csv
                String[] datos = linea.split(";");
                String nombre = datos[0];
                Double precio = Double.parseDouble(datos[1]);
                Integer stock = Integer.parseInt(datos[2]);
                String categoria = datos[3].trim();

                //creamos el objeto
                Productos productos = new Productos();
                //se lo asignamos
                productos.setNombre(nombre);
                productos.setPrecio(precio);
                productos.setStock(stock);
                productos.setCategoria(categoria);


                //lo añadimos
                listProductos.add(productos);
            }
            //cerramos
            br.close();

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + fileName, e);
        }

        return listProductos;
    }
}
