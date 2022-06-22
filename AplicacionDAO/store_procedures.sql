USE AdventureWorks2019Sales

GO
CREATE OR ALTER PROCEDURE sp_insert_OrderDetail(
    @ProductID varchar(20),
    @OrderQty varchar(20),
    @Salida varchar(20) OUTPUT,--Regresa el @SalesOrderID O los errores
    @Salida_2 varchar(20) OUTPUT,--Regresar el precio del producto,
    @SalesOrderID  int
)
AS
    CREATE TABLE #temp(
        Existe int
    )
    DECLARE @UnitPriceDiscount float
    DECLARE @SpecialOfferID int

    IF (@SalesOrderID = -1)--Nueva orderID
        BEGIN
            SET @SalesOrderID =  (SELECT max(SalesOrderID)
            FROM Sales.SalesOrderDetail)
            SET @SalesOrderID = @SalesOrderID+1
        END
    ELSE --Misma Orden
        BEGIN
            SET @SalesOrderID =  (SELECT max(SalesOrderID)
            FROM Sales.SalesOrderDetail)
            SET @SalesOrderID = @SalesOrderID   
        END

    --@SQL ES UNA CADENA DE CARACTERES QUE NOS AYUDARA A HACER LA CONSULTAS DISTRUBUIDAS CON EL LINKED SERVER BDD01B
    DECLARE @Sql VARCHAR(8000)
    --CONSULTA EN EL SERVIDOR VINCULADO BDD01B (QUE CONTIENE SCHEMA PRODUCTION), VERIFIDANDO SI LA CANTIDAD ES SUFICIENTE
    SET @Sql = 'SELECT EXISTS(SELECT * FROM AdventureWorks2019Production.Production.ProductInventory WHERE ProductID = '+ @ProductID +' AND Quantity >= '+@OrderQty +'  ) as "EXISTE?"'
    SET @Sql = 'SELECT * FROM OPENQUERY(BDD01B, ''' + REPLACE(@Sql, '''', '''''') + ''')'
    INSERT INTO #temp
    --INCERTAMOS LOS DATOS DE LA CONSULTA DISTRIBUIDA EN LA TABLA TEMPORAL
    EXEC(@Sql)

    DECLARE @flag INT;
    SELECT @flag = Existe FROM #temp
    --VERIFICAMOS LA EXISTENCIA DEL PRODUCTO
    IF @flag = 1--Existe
        --CORROBORAMOS LA EXISTENCIA
        BEGIN
            --VERIFICAMOS SI EL PRODUCTO TIENE ALGUN DESCUENTO Y EL VALOR DEL MISMO (EN CASO DE EXISTIR)
            --NO TIENE DESCUENTO
            IF 0 <= @OrderQty AND @OrderQty <=10
                BEGIN
                    SET @UnitPriceDiscount=0.00;
                    SET @SpecialOfferID=1;
                END
            --DESCUANTO ENTRE 11 Y 14
            ELSE IF 11 <= @OrderQty AND @OrderQty <=14 
                BEGIN
                    SET @UnitPriceDiscount=0.02;
                    SET @SpecialOfferID=2;
                END
            --DESCUANTO ENTRE 15 Y 24
            ELSE IF 15 <= @OrderQty AND @OrderQty <=24
                BEGIN
                    SET @UnitPriceDiscount=0.05;
                    SET @SpecialOfferID=3;
                END
            --DESCUANTO ENTRE 25 Y 40
            ELSE IF 25 <= @OrderQty AND @OrderQty <=40
                BEGIN
                    SET @UnitPriceDiscount=0.10;
                    SET @SpecialOfferID=4;
                END
            --DESCUANTO ENTRE 41 Y 60
            ELSE IF 41 <= @OrderQty AND @OrderQty <=60
                BEGIN
                    SET @UnitPriceDiscount=0.15;
                    SET @SpecialOfferID=5;
                END
            --DESCUANTO MAYOR A 60
            ELSE IF 61 <= @OrderQty
                BEGIN
                    --PRINT'Volume Discount over 60';
                    SET @UnitPriceDiscount=0.20;
                    SET @SpecialOfferID=6;
                END
            ELSE
                BEGIN
                    --PRINT'DESCUENTO: 0.00';
                    SET @UnitPriceDiscount=0.00;
                    SET @SpecialOfferID=1;
                END

            --UNA VEZ VALIDADO EL DESCUENTO PASAMOS A LA INSERCION
            DECLARE @rowguid VARCHAR(8000)
                SET @rowguid = NEWID()
            DECLARE @rowguid_2 VARCHAR(8000)
                SET @rowguid_2 = NEWID()

            --SI NO EXISTE HACEMOS LA INCERCION EN ESPECIAL PRODUCT
            IF NOT EXISTS (SELECT * FROM SALES.SpecialOfferProduct
                WHERE SpecialOfferID = @SpecialOfferID AND ProductID = @ProductID) 
                BEGIN
                    INSERT INTO sales.SpecialOfferProduct
                        (SpecialOfferID,ProductID,rowguid,ModifiedDate)
                    VALUES
                        (@SpecialOfferID, @ProductID, @rowguid, SYSDATETIME())
                END
            --SI EXISTE HACEMOS UN UPDATE DE SPECIAL PRODUCT
            ELSE 
                BEGIN
                    UPDATE sales.SpecialOfferProduct
                        SET SpecialOfferID = @SpecialOfferID, ProductID = @ProductID, rowguid = @rowguid, ModifiedDate= SYSDATETIME()
                        WHERE ProductID = @ProductID;
                END

            --OBTENEMOS EL PRECIO DEL PRODUCTO (TOMANDO EN CUENTA LAS OFERTAS)
            CREATE TABLE #temp_StandarCost(
                Existe float
            )

            DECLARE @Sql_StandarCost VARCHAR(8000)
            --CONSULTA BDD01B PARA VERIFICAR EXISTENCIA DEL PROCTO Y SUFICIENCIA DE STOCK, SACANDO EL PRECIO DEL PRODUCTO (EN CASO DE EXISTIR)
            SET @Sql_StandarCost = 'SELECT StandardCost FROM Adventureworks2019Production.Production.product WHERE ProductID = '+ @ProductID +' '
            SET @Sql_StandarCost = 'SELECT * FROM OPENQUERY(BDD01B, ''' + REPLACE(@Sql_StandarCost, '''', '''''') + ''')'

            INSERT INTO #temp_StandarCost
            EXEC(@Sql_StandarCost)
            SELECT * FROM #temp_StandarCost
            SELECT @Salida_2 = Existe FROM #temp_StandarCost

            INSERT INTO SALES.SalesOrderDetail
                (SalesOrderID,CarrierTrackingNumber,OrderQty,ProductID,SpecialOfferID,UnitPrice,UnitPriceDiscount,rowguid,ModifiedDate)
            VALUES
                (@SalesOrderID, '4911-403C-98', @OrderQty, @ProductID, @SpecialOfferID, @Salida_2, @UnitPriceDiscount, @rowguid_2, SYSDATETIME())

            Set @Salida = @SalesOrderID

        END;
        
    --------------------------
    --El PRODUCTO NO EXISTE EN CATALOGO O NO HAY DISPONIVILIDAD
    ELSE
        BEGIN	
            CREATE TABLE #temp2(
                Existe int
            )
            DECLARE @Sql_verificarProducto VARCHAR(8000)
            --CONSULTA BDD01B para verificar si existe el articulo 
            SET @Sql_verificarProducto = 'SELECT EXISTS(SELECT * FROM AdventureWorks2019Production.Production.ProductInventory WHERE ProductID = '+ @ProductID +') as "EXISTE?"'
            SET @Sql_verificarProducto ='SELECT * FROM OPENQUERY(BDD01B, ''' + REPLACE(@Sql_verificarProducto, '''', '''''') + ''')'

            INSERT INTO #temp2
            EXEC(@Sql_verificarProducto)

            DECLARE @flag2 INT;
            SELECT @flag2 = Existe FROM #temp2

            --EL ARTICULO EXISTE
            IF (@flag2 = 1)
                BEGIN
                    PRINT'EL PRODUCTO EXISTE'
                    SET @Salida = -1;

                    CREATE TABLE #temp3
                    (
                        Existe int
                    )
                    DECLARE @Sql_verificarCantidad VARCHAR(8000)
                    --CONSULTA BDD01B PARA OBTENER LA TABLA PRODUCTINVENTORY
                    SET @Sql_verificarCantidad = 'SELECT EXISTS(SELECT * FROM AdventureWorks2019Production.Production.ProductInventory WHERE ProductID = '+ @ProductID +' AND Quantity >= '+@OrderQty +' LIMIT 1 ) as "EXISTE?"'
                    SET @Sql_verificarCantidad = 'SELECT * FROM OPENQUERY(BDD01B, ''' + REPLACE(@Sql_verificarCantidad, '''', '''''') + ''')'

                    INSERT INTO #temp3
                    EXEC(@Sql_verificarCantidad)

                    DECLARE @flag3 INT;
                    SELECT @flag3 = Existe FROM #temp3
                    --CANTIDAD SUFICIENTE			
                    IF (@flag3 = 1)
                        BEGIN
                            SET @Salida = -2;
                        END
                    --CANTIDAD INSUFICIENTE
                    ELSE
                        BEGIN
                            SET @Salida = -3;
                        END
                END 
            --EL PRODUCTO NO ESTA LISTADO EN CATALOGO
            ELSE IF(@flag2 = 0)
                BEGIN
                    SET @Salida = -4;
                END 
            -- HUBO UN ERROR
            ELSE
                BEGIN
                    SET @Salida = -5;
                END
            SET @Salida_2 = -1;
        END;

    print @Salida
    print @Salida_2




----------------------------------------------fin sp_insert_OrderDetail-----------

GO
CREATE OR ALTER PROCEDURE sp_insert_OrderHeader(
    @SalesOrderID int
)
AS
    BEGIN
        DECLARE @SubTotal_cal float
        SET @SubTotal_cal = ((SELECT SUM(SALES.SalesOrderDetail.LineTotal)
        FROM Sales.SalesOrderDetail
        where Sales.SalesOrderDetail.SalesOrderID = @SalesOrderID))

        INSERT INTO SALES.SalesOrderHeader
            (RevisionNumber,OrderDate,DueDate,ShipDate,Status,OnlineOrderFlag,
            CustomerID,BillToAddressID,ShipToAddressID,ShipMethodID,SubTotal,TaxAmt,Freight,rowguid,ModifiedDate)
        VALUES
            (8, SYSDATETIME(), SYSDATETIME(), SYSDATETIME(), 5, 1, 27918, 21592, 21592, 5,
                @SubTotal_cal, @SubTotal_cal*.08, @SubTotal_cal*.08*0.3125, NEWID(), SYSDATETIME())
    END

----------------------------------------------sp_insert_OrderHeader-----------

GO
CREATE OR ALTER PROCEDURE sp_ConsultaPrecio(
    @ProductID varchar(20),
    @Salida_consulta money OUTPUT
    )
AS
    CREATE TABLE #temp_2(
            Existe money
        )
    DECLARE @Sql_StandardCost VARCHAR(8000)
    SET @Sql_StandardCost = 'SELECT StandardCost FROM Adventureworks2019Production.Production.product WHERE ProductID = '+ @ProductID +' LIMIT 1'
    --SET @Sql = 'SELECT * FROM AdventureWorks2019.PRODUCTInventory WHERE ProductID = '+ @ProductID +' AND Quantity >= '+@cantidad +' LIMIT 1'
    SET @Sql_StandardCost = 'SELECT * FROM OPENQUERY(BDD01B, ''' + REPLACE(@Sql_StandardCost, '''', '''''') + ''')'
    --EXEC(@Sql) 
    INSERT INTO #temp_2
    EXEC(@Sql_StandardCost)
    --SET @salida_2 = (SELECT * FROM #temp)
    SET @Salida_consulta = (SELECT * FROM #temp_2)
    --print @Salida_consulta
    RETURN
