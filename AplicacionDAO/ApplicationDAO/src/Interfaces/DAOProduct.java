/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla Product
*/
package Interfaces;

import Production.Product;
import java.util.List;

public interface DAOProduct {
    public void registrar(Product per) throws Exception;
    public void modificar(Product per) throws Exception;
    public void eliminar(Product per) throws Exception;
    public List<Product> listar() throws Exception;
    
}
