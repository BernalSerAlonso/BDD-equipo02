/*
En esta clase se encuentran los Set y Get para la  tabla ProductCategory con su respectivo tipo de dato
*/
package Production;

import java.sql.Date;

public class ProductCategory {
    private int ProductCategoryID;
    private String Name;
    private String rowguid;
    private Date ModifiedDate;

    public int getProductCategoryID() {
        return ProductCategoryID;
    }

    public void setProductCategoryID(int ProductCategoryID) {
        this.ProductCategoryID = ProductCategoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRowguid() {
        return rowguid;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Date ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }
    
    

    
}
