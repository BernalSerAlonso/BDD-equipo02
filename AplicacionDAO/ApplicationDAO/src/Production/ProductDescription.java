/*
En esta clase se encuentran los Set y Get para la  tabla ProductDescription con su respectivo tipo de dato
*/
package Production;

import java.sql.Date;

public class ProductDescription {
    private int ProductDescriptionID;
    private String Description;
    private String rowguid;
    private Date ModifiedDate;

    public int getProductDescriptionID() {
        return ProductDescriptionID;
    }

    public void setProductDescriptionID(int ProductDescriptionID) {
        this.ProductDescriptionID = ProductDescriptionID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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
