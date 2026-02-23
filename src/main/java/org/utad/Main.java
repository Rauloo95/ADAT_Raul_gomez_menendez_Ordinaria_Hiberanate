package org.utad;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.utad.DataProvider.DataProvider;
import org.utad.LecturaCSV.ImporterService;
import org.utad.Model.Productos;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //instanciamos
        ImporterService importer = new ImporterService();
        // Leer CSV (el archivo debe estar en resources)
        List<Productos> listaProductos = importer.leerCSV("productossin.csv");

        //lectura de comprobacion del csv por consola
        for(Productos productos: listaProductos) {
            System.out.println(productos);
        }


        //variable de transaction global
        Transaction ts = null;
        //insertar todos en la base de datos
        try(Session sesion = DataProvider.getFactory().openSession()){
            ts = sesion.beginTransaction();
            System.out.println("Productos a meter ");
            //con for los vamos metiendo con persist
            for(Productos venta : listaProductos){
                sesion.persist(venta);
                System.out.println("Producto ingresado:  " + venta.getNombre());
            }
            //comiteamos cambios
            ts.commit();


        }catch (Exception e){
            e.printStackTrace();

        }


        //buscar por stock minimo
        try(Session sesion = DataProvider.getFactory().openSession()){
            Query<Productos> query = sesion.createQuery("FROM Productos where Stock> :minStock",Productos.class);
            //pasamos parametro de por ejemplo 5
            query.setParameter("minStock", 5);
            List<Productos> productos = query.getResultList();
            //si la list no esta vacia
            if(!listaProductos.isEmpty()){
                System.out.println("Listado de Productos: ");
                for(Productos producto: productos){
                    System.out.println("Producto: " + producto.getNombre() +" Stock: "+ producto.getStock());
                }
                }else{
                System.out.println("No hay productos encontrados ");
            }
        }catch(Exception e){
            e.printStackTrace();
        }



//        //lista de productos con precio comprendida entre:
        try(Session sesion = DataProvider.getFactory().openSession()){
            Query<Productos> query = sesion.createQuery("FROM Productos where Stock BETWEEN :valor1 and :valor2",Productos.class);
            query.setParameter("valor1", 5);
            query.setParameter("valor2", 20);
            List<Productos> productos = query.getResultList();
            if(!listaProductos.isEmpty()){
                System.out.println("Listado de Productos: ");
                for(Productos producto: productos){
                    System.out.println("Producto: " + producto.getNombre() + " Precio: " + producto.getPrecio());
                }
            }else{
                System.out.println("No hay productos encontrados ");
            }
        }catch(Exception e){
            e.printStackTrace();
        }






        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }
}