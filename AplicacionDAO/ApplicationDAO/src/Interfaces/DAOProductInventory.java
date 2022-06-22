/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla ProductInventory
*/
package Interfaces;

import Production.ProductInventory;
import java.util.List;

public interface DAOProductInventory {
    public void registrar(ProductInventory per) throws Exception;
    public void modificar(ProductInventory per) throws Exception;
    public void eliminar(ProductInventory per) throws Exception;
    public List<ProductInventory> listar() throws Exception;
}
