/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla ProductCategory
*/
package Interfaces;

import Production.ProductCategory;
import java.util.List;

public interface DAOProductCategory {
    public void registrar(ProductCategory per) throws Exception;
    public void modificar(ProductCategory per) throws Exception;
    public void eliminar(ProductCategory per) throws Exception;
    public List<ProductCategory> listar() throws Exception;
    
}
