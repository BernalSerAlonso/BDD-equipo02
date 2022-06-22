/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla ProductDescription
*/
package Interfaces;

import Production.ProductDescription;
import java.util.List;

public interface DAOProductDescription {
    public void registrar(ProductDescription per) throws Exception;
    public void modificar(ProductDescription per) throws Exception;
    public void eliminar(ProductDescription per) throws Exception;
    public List<ProductDescription> listar() throws Exception;
    
}
