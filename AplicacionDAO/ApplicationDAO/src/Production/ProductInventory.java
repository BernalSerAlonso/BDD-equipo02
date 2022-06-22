/*
En esta clase se encuentran los Set y Get para la  tabla ProductInventory con su respectivo tipo de dato
*/
package Production;

import java.sql.Date;
import java.sql.Timestamp;
public class ProductInventory {
        private int ProductID;
        private int LocationID;
        private String Shelf;
        private int bin;
        private int Quantity;
        private String rowguid;
        private Date ModifiedDate;
        
        public int getProductID(){
        return ProductID;
        }
        public void setProductID(int ProductID){
            this.ProductID = ProductID;
        }
        public int getLocationID(){
        return LocationID;
        }
        public void setLocationID(int LocationID){
            this.LocationID = LocationID;
        }
        public String getShelf(){
        return Shelf;
        }
        public void setShelf(String Shelf){
            this.Shelf = Shelf;
        }
        public int getbin(){
        return bin;
        }
        public void setbin(int bin){
            this.bin = bin;
        }
        public int getQuantity(){
        return Quantity;
        }
        public void setQuantity(int Quantity){
            this.Quantity = Quantity;
        }
        public String getrowguid(){
        return rowguid;
        }
        public void setrowguid(String rowguid){
            this.rowguid = rowguid;
        }
        public Date getModifiedDate(){
        return ModifiedDate;
        }
        public void setModifiedDate(Date ModifiedDate){
            this.ModifiedDate = ModifiedDate;
        }
}
