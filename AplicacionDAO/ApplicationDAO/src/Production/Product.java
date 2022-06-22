/*
En esta clase se encuentran los Set y Get para la  tabla Product con su respectivo tipo de dato
*/
package Production;

import java.sql.Date;

public class Product {

    private int ProductID;
    private String Name;
    private String ProductNumber;
    private int MakeFlag;
    private int FinishedGoodsFlag;
    private String Color;
    private short SafetyStockLevel;
    private short ReorderPoint;
    private float StandardCost;
    private float ListPrice;
    private String Size;
    private String SizeUnitMeasureCode;
    private String WeightUnitMeasureCode;
    private float Weight;
    private int DaysToManufacture;
    private String ProductLine;
    private String Clase;
    private String Style;
    private int ProductSubcategoryID;
    private int ProductModelID;
    private Date SellStartDate;
    private Date SellEndDate;
    private Date DiscontinuedDate;
    private String rowguid;
    private Date ModifiedDate;

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String ProductNumber) {
        this.ProductNumber = ProductNumber;
    }

    public int getMakeFlag() {
        return MakeFlag;
    }

    public void setMakeFlag(int MakeFlag) {
        this.MakeFlag = MakeFlag;
    }

    public int getFinishedGoodsFlag() {
        return FinishedGoodsFlag;
    }

    public void setFinishedGoodsFlag(Byte FinishedGoodsFlag) {
        this.FinishedGoodsFlag = FinishedGoodsFlag;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public short getSafetyStockLevel() {
        return SafetyStockLevel;
    }

    public void setSafetyStockLevel(short SafetyStockLevel) {
        this.SafetyStockLevel = SafetyStockLevel;
    }

    public short getReorderPoint() {
        return ReorderPoint;
    }

    public void setReorderPoint(short ReorderPoint) {
        this.ReorderPoint = ReorderPoint;
    }

    public float getStandardCost() {
        return StandardCost;
    }

    public void setStandardCost(float StandardCost) {
        this.StandardCost = StandardCost;
    }

    public float getListPrice() {
        return ListPrice;
    }

    public void setListPrice(float ListPrice) {
        this.ListPrice = ListPrice;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getSizeUnitMeasureCode() {
        return SizeUnitMeasureCode;
    }

    public void setSizeUnitMeasureCode(String SizeUnitMeasureCode) {
        this.SizeUnitMeasureCode = SizeUnitMeasureCode;
    }

    public String getWeightUnitMeasureCode() {
        return WeightUnitMeasureCode;
    }

    public void setWeightUnitMeasureCode(String WeightUnitMeasureCode) {
        this.WeightUnitMeasureCode = WeightUnitMeasureCode;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float Weight) {
        this.Weight = Weight;
    }

    public int getDaysToManufacture() {
        return DaysToManufacture;
    }

    public void setDaysToManufacture(int DaysToManufacture) {
        this.DaysToManufacture = DaysToManufacture;
    }

    public String getProductLine() {
        return ProductLine;
    }

    public void setProductLine(String ProductLine) {
        this.ProductLine = ProductLine;
    }

    public String getClase() {
        return Clase;
    }

    public void setClase(String Clase) {
        this.Clase = Clase;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String Style) {
        this.Style = Style;
    }

    public int getProductSubcategoryID() {
        return ProductSubcategoryID;
    }

    public void setProductSubcategoryID(int ProductSubcategoryID) {
        this.ProductSubcategoryID = ProductSubcategoryID;
    }

    public int getProductModelID() {
        return ProductModelID;
    }

    public void setProductModelID(int ProductModelID) {
        this.ProductModelID = ProductModelID;
    }

    public Date getSellStartDate() {
        return SellStartDate;
    }

    public void setSellStartDate(Date SellStartDate) {
        this.SellStartDate = SellStartDate;
    }

    public Date getSellEndDate() {
        return SellEndDate;
    }

    public void setSellEndDate(Date SellEndDate) {
        this.SellEndDate = SellEndDate;
    }

    public Date getDiscontinuedDate() {
        return DiscontinuedDate;
    }

    public void setDiscontinuedDate(Date DiscontinuedDate) {
        this.DiscontinuedDate = DiscontinuedDate;
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
