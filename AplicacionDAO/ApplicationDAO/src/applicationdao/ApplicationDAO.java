/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdao;

/**
 *
 * @author Conexion
 */
import java.util.InputMismatchException;
import java.util.Scanner;

/*Sales*/
 /*Customer*/
import DAO.DAOCustomerImpl;
import Interfaces.DAOCustomer;
import Sales.Customer;

/*OrderDetail*/
import DAO.DAOSalesOrderDetailImpl;
import Interfaces.DAOSalesOrderDetail;
import Sales.SalesOrderDetail;

/*OrderHeader*/
import DAO.DAOSalesOrderHeaderImpl;
import Interfaces.DAOSalesOrderHeader;
import Sales.SalesOrderHeader;

/*SpecialOfferProduct*/
import DAO.DAOSpecialOfferProductImpl;
import Interfaces.DAOSpecialOfferProduct;
import Sales.SpecialOfferProduct;

/*Production*/
 /*Product*/
import Production.Product;
import DAO.DAOProductImpl;
import Interfaces.DAOProduct;

/*ProductCategory*/
import Production.ProductCategory;
import DAO.DAOProductCategoryImpl;
import Interfaces.DAOProductCategory;

/*ProductCategory*/
import Production.ProductDescription;
import DAO.DAOProductDescriptionImpl;
import Interfaces.DAOProductDescription;

/*ProductInventory*/
import Production.ProductInventory;
import DAO.DAOProductInventoryImpl;
import Interfaces.DAOProductInventory;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationDAO {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);

        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        int aux2; //Guardaremos la opcion del usuario
        float aux_f;

        while (!salir) {
            System.out.println("****** Selecciona una opcion ******");
            System.out.println("1. Sales");
            System.out.println("2. Product");
            System.out.println("0. Salir");

            try {
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:{
                        System.out.println("1. Sales.Customer");
                        System.out.println("2. Sales.OrderDetail");
                        System.out.println("3. Sales.OrderHeader");
                        System.out.println("4. Sales.SpecialOfferProduct");
                        try {
                            System.out.println("Escribe una de las opciones");
                            aux2 = sn.nextInt();

                            switch (aux2) {
                                case 1:
                                    demoDAO_S_Customer();
                                case 2:
                                    demoDAO_S_OrderDetail();
                                case 3:
                                    demoDAO_S_OrderHeader();
                                case 4:
                                    demoDAO_S_SpecialOfferProduct();
                                default:
                                    System.out.println("Solo números entre 1 y 4");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debes insertar un número");
                            sn.next();
                        }
                    }
                    case 2: {
                        System.out.println("1. Product");
                        System.out.println("2. ProductCategory");
                        System.out.println("3. ProductDescription");
                        System.out.println("4. ProductInventory");

                        try {

                            System.out.println("Escribe una de las opciones");
                            opcion = sn.nextInt();

                            switch (opcion) {
                                case 1:
                                    demoDAO_P_Product();
                                case 2:
                                    demoDAO_P_ProductCategory();
                                case 3:
                                    demoDAO_P_ProductDescription();
                                case 4:
                                    demoDAO_P_ProductInventory();
                                default:
                                    System.out.println("Solo números entre 1 y 4");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debes insertar un número");
                            sn.next();
                        }
                    }
                    case 0:{
                        salir = true;
                    }
                    default :{
                        System.out.println("Solo números entre 0 y 4");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            System.out.println("\n\n");
        }
    }

    static public void pressAnyKeyToContinue() {
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    static public void demoDAO_S_Customer() {
        boolean salir_p = false;
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        System.out.println("DAO Customer");
        Customer c = new Customer();
        c.setTerritoryID(1);
        c.setCustomerID(1);
        try {
            DAOCustomer dao = new DAOCustomerImpl();

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("Not Supported");
                        case 2: {
                            System.out.println("Listado TOP 5 Sales.Customer");

                            for (Customer p : dao.listar()) {
                                System.out.println(p.getCustomerID() + " -- " + p.getPersonID() + " -- " + p.getStoreID() + " -- " + p.getTerritoryID() + " -- " + p.getAccountNumber() + " -- " + p.getModifiedDate());
                            }
                        }
                        case 3: {
                            System.out.println("\n Update TerritoryID y ModifiedDate");

                            System.out.println("Ingrese el CustomerID");
                            c.setCustomerID(aux.nextInt());

                            System.out.println("Ingrese el nuevo valor de TerritoryID");
                            c.setTerritoryID(aux.nextInt());

                            dao.modificar(c);
                        }
                        case 4:
                            System.out.println("Not Supported");
                        case 0:
                            salir_p = true;
                        default:
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_S_OrderDetail() {
        boolean salir_p = false;
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        System.out.println("DAO SalesOrderDetail");

        SalesOrderDetail sod = new SalesOrderDetail();
        sod.setSalesOrderID(75137);
        sod.setOrderQty(1);
        sod.setProductID(921);
        try {
            DAOSalesOrderDetail dao = new DAOSalesOrderDetailImpl();

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("Not Supported");
                        case 2: {
                            for (SalesOrderDetail p : dao.listar()) {
                                System.out.println(p.getSalesOrderID() + " -- " + p.getSalesOrderDetailID() + " -- " + p.getCarrierTrackingNumber() + " -- " + p.getOrderQty() + " -- " + p.getProductID());
                            }
                        }
                        case 3: {
                            System.out.println("\n Update OrderQty");

                            System.out.println("Ingrese el SalesOrderID");
                            sod.setSalesOrderID(aux.nextInt());

                            System.out.println("Ingrese el ProductID");
                            sod.setProductID(aux.nextInt());

                            System.out.println("Ingrese el nuevo valor de OrderQty");
                            sod.setOrderQty(aux.nextInt());

                            dao.modificar(sod);
                        }
                        case 4:
                            System.out.println("Not Supported");
                        case 0:
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_S_OrderHeader() {
        boolean salir_p = false;
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        System.out.println("DAO SalesOrderHeader");

        SalesOrderHeader soh = new SalesOrderHeader();
        soh.setStatus(3);
        soh.setSalesOrderID(75136);
        try {
            DAOSalesOrderHeader dao = new DAOSalesOrderHeaderImpl();

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1 :
                            System.out.println("Not Supported");
                        case 2 : {
                            for (SalesOrderHeader p : dao.listar()) {
                                System.out.println(p.getSalesOrderID() + " -- " + p.getStatus() + " -- " + p.getTotalDue() + " -- " + p.getModifiedDate());
                            }
                        }
                        case 3: {
                            System.out.println("\n Update Status");

                            System.out.println("Ingrese el SalesOrderID");
                            soh.setSalesOrderID(aux.nextInt());

                            System.out.println("Ingrese el nuevo valor de Status");
                            soh.setStatus(aux.nextInt());

                            dao.modificar(soh);
                        }
                        case 4 :
                            System.out.println("Not Supported");
//                            dao.eliminar(soh);
//
//                            for (SalesOrderHeader p : dao.listar()) {
//                                System.out.println(p.getSalesOrderID() + " -- " + p.getStatus() + " -- " + p.getTotalDue() + " -- " + p.getModifiedDate());
//                            }
                        case 0 :
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_S_SpecialOfferProduct() {
        boolean salir_p = false;
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        int opcion;

        System.out.println("DAO SpecialOfferProduct");

        SpecialOfferProduct sop = new SpecialOfferProduct();
//                                    sop.setSpecialOfferID(1);
//                                    sop.setProductID(680);
//                                    sop.setAux(2);
        try {
            DAOSpecialOfferProduct dao = new DAOSpecialOfferProductImpl();

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1 :
                            System.out.println("Not Supported");
                        case 2 : {
                            for (SpecialOfferProduct p : dao.listar()) {
                                System.out.println(p.getSpecialOfferID() + " -- " + p.getProductID() + " -- " + p.getrowguid() + " -- " + p.getModifiedDate());
                            }
                        }
                        case 3 : {
                            System.out.println("Not Supported");
                            System.out.println("\n Update SpecialOfferID y ModifiedDate ");

                            System.out.println("Ingrese el SpecialOfferID");
                            sop.setAux(aux.nextInt());//Antiguo SpecialOfferID

                            System.out.println("Ingrese el ProductID");
                            sop.setProductID(aux.nextInt());

                            System.out.println("Ingrese el nuevo valor de SpecialOfferID");
                            sop.setSpecialOfferID(aux.nextInt());//Nuevo SpecialOfferID

                            dao.modificar(sop);

                            System.out.println("\n");
                        }
                        case 4 :
                            System.out.println("Not Supported");
                        case 0 :
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_P_Product() {
        boolean salir_p = false;

        System.out.println("DAO Product");
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        Product sop = new Product();
        try {
            DAOProduct dao = new DAOProductImpl();
            System.out.println("\n");

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1 : {
                            System.out.println("Create");
                            System.out.println("\n Insert Product ");

                            System.out.println("Ingrese el Nombre");
                            sop.setName(aux.next());

                            System.out.println("Ingrese el ProductNumber");
                            sop.setProductNumber(aux.next());

                            System.out.println("Ingrese el SafetyStockLevel");
                            sop.setSafetyStockLevel(aux.nextShort());

                            System.out.println("Ingrese el ReorderPoint");
                            sop.setReorderPoint(aux.nextShort());

                            System.out.println("Ingrese el StandardCost");
                            sop.setStandardCost(aux.nextFloat());

                            System.out.println("Ingrese el ListPrice");
                            sop.setListPrice(aux.nextFloat());

                            dao.registrar(sop);
                        }
                        case 2 : {
                            System.out.println("Read");
                            for (Product p : dao.listar()) {
                                System.out.println(p.getProductID() + " -- " + p.getName() + " -- " + p.getProductNumber() + " -- " + p.getStandardCost());
                            }
                        }
                        case 3 : {
                            System.out.println("Update");
                            System.out.println("\n Update StandardCost y ModifiedDate ");

                            System.out.println("Ingrese el ProductID");
                            sop.setProductID(aux.nextInt());

                            System.out.println("Ingrese el nuevo valor de StandardCost");
                            sop.setStandardCost(aux.nextFloat());

                            dao.modificar(sop);
                        }
                        case 4 : {
                            System.out.println("\n DELETE Product ");

                            System.out.println("Ingrese el ProductID");
                            sop.setProductID(aux.nextInt());

                            dao.eliminar(sop);
                        }
                        case 0 :
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_P_ProductCategory() {
        boolean salir_p = false;

        System.out.println("DAO Product");
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        ProductCategory sop = new ProductCategory();
        try {
            DAOProductCategory dao = new DAOProductCategoryImpl();
            System.out.println("\n");

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1 : {
                            System.out.println("Create");
                            System.out.println("\n Insert ProductCategory ");

                            System.out.println("Ingrese el Nombre");
                            sop.setName(aux.next());

                            dao.registrar(sop);
                        }
                        case 2 : {
                            System.out.println("Read");
                            for (ProductCategory p : dao.listar()) {
                                System.out.println(p.getProductCategoryID() + " -- " + p.getName() + " -- " + p.getRowguid() + " -- " + p.getModifiedDate());
                            }
                        }
                        case 3 : {
                            System.out.println("Update");
                            System.out.println("\n Update Name ");

                            System.out.println("Ingrese el ProductCategoryID");
                            sop.setProductCategoryID(aux.nextInt());

                            System.out.println("Ingrese el nuevo Nombre");
                            sop.setName(aux.next());

                            dao.modificar(sop);
                        }
                        case 4 : {
                            System.out.println("\n DELETE Product ");

                            System.out.println("Ingrese el ProductCategoryID");
                            sop.setProductCategoryID(aux.nextInt());

                            dao.eliminar(sop);
                        }
                        case 0 :
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_P_ProductDescription() {
        boolean salir_p = false;

        System.out.println("DAO Product");
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        ProductDescription sop = new ProductDescription();
        try {
            DAOProductDescription dao = new DAOProductDescriptionImpl();
            System.out.println("\n");

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1 : {
                            System.out.println("Create");
                            System.out.println("\n Insert ProductDescription ");

                            System.out.println("Ingrese la Description");
                            sop.setDescription(aux.next());

                            dao.registrar(sop);
                        }
                        case 2 : {
                            System.out.println("Read");
                            for (ProductDescription p : dao.listar()) {
                                System.out.println(p.getProductDescriptionID() + " -- " + p.getDescription() + " -- " + p.getRowguid() + " -- " + p.getModifiedDate());
                            }
                        }
                        case 3 : {
                            System.out.println("Update");
                            System.out.println("\n Update Description ");

                            System.out.println("Ingrese el ProductDescriptionID");
                            sop.setProductDescriptionID(aux.nextInt());

                            System.out.println("Ingrese la nueva Description");
                            sop.setDescription(aux.next());

                            dao.modificar(sop);
                        }
                        case 4 : {
                            System.out.println("\n DELETE Product ");

                            System.out.println("Ingrese el ProductDescriptionID");
                            sop.setProductDescriptionID(aux.nextInt());

                            dao.eliminar(sop);
                        }
                        case 0 :
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    static public void demoDAO_P_ProductInventory() {
        boolean salir_p = false;

        System.out.println("DAO Product");
        Scanner sn = new Scanner(System.in);
        Scanner aux = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        ProductInventory sop = new ProductInventory();
        try {
            DAOProductInventory dao = new DAOProductInventoryImpl();
            System.out.println("\n");

            while (!salir_p) {

                System.out.println("1. CREATE");
                System.out.println("2. READ");
                System.out.println("3. UPDATE");
                System.out.println("4. DELETE");
                System.out.println("0. SALIR");

                try {

                    System.out.println("Escribe una de las opciones");
                    opcion = sn.nextInt();

                    switch (opcion) {
                        case 1 : {
                            System.out.println("Create");
                            System.out.println("\n Insert ProductDescription ");

                            System.out.println("Ingrese ProductID");
                            sop.setProductID(aux.nextInt());

                            System.out.println("Ingrese  LocationID");
                            sop.setLocationID(aux.nextInt());

                            System.out.println("Ingrese Shelf");
                            sop.setShelf(aux.next());

                            System.out.println("Ingrese Bin");
                            sop.setbin(aux.nextInt());

                            System.out.println("Ingrese la Cantidad");
                            sop.setQuantity(aux.nextInt());

                            dao.registrar(sop);
                        }
                        case 2 : {
                            System.out.println("Read");
                            for (ProductInventory p : dao.listar()) {
                                System.out.println(p.getProductID() + " -- " + p.getLocationID() + " -- " + p.getShelf() + " -- " + p.getbin() + " -- " + p.getQuantity() + " -- " + p.getrowguid() + " -- " + p.getModifiedDate());
                            }
                        }
                        case 3 : {
                            System.out.println("Update");
                            System.out.println("\n Update Quantity ");

                            System.out.println("Ingrese ProductID");
                            sop.setProductID(aux.nextInt());

                            System.out.println("Ingrese  LocationID");
                            sop.setLocationID(aux.nextInt());

                            System.out.println("Ingrese la nueva Cantidad");
                            sop.setQuantity(aux.nextInt());

                            dao.modificar(sop);
                        }
                        case 4 : {
                            System.out.println("\n DELETE Product ");

                            System.out.println("Ingrese ProductID");
                            sop.setProductID(aux.nextInt());

                            System.out.println("Ingrese  LocationID");
                            sop.setLocationID(aux.nextInt());

                            dao.eliminar(sop);
                        }
                        case 0 :
                            salir_p = true;
                        default :
                            System.out.println("Solo números entre 0 y 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
