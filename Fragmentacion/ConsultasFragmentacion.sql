
--2  Listar al empleado que atendio mas ordenes 
SELECT SalesPersonID, COUNT(Sales.SalesOrderHeader.TerritoryID) as numeroOrdenesAtendidas
FROM Sales.SalesOrderHeader INNER JOIN Sales.SalesPerson
ON Sales.SalesOrderHeader.SalesPersonID = Sales.SalesPerson.BusinessEntityID
GROUP BY SalesPersonID ORDER BY COUNT(*) DESC
go
-- 3. Listar los datos del cliente con m�s ordenes solicitadas en la regi�n "North America"
SELECT TOP 1 Norteamerica.Sales.Customer.CustomerID, COUNT(Norteamerica.sales.SalesOrderHeader.SalesOrderID) AS NumberOfOrders
FROM Norteamerica.Sales.SalesOrderHeader INNER JOIN Norteamerica.Sales.Customer 
ON Norteamerica.Sales.SalesOrderHeader.CustomerID = Norteamerica.Sales.Customer.CustomerID 
WHERE Norteamerica.Sales.SalesOrderHeader.TerritoryID IN (1,2,3,4,5,6)
GROUP BY Norteamerica.Sales.Customer.CustomerID ORDER BY NumberOfOrders DESC
go
-- 4. Listar el producto m�s solicitado en la regi�n �Europe�
select top 1 so.ProductID, COUNT(*) as Piezas_Vendidas
		from Europe.Sales.SalesOrderDetail so
		inner join Europe.Sales.SalesOrderHeader sh
		on sh.SalesOrderID = so.SalesOrderID 
		where sh.TerritoryID in (7,8,10) 
		group by so.ProductID
		
-- 5. Listar las ofertas que tienen los productos de la categor�a �Bikes�
SELECT * FROM Sales.SpecialOffer WHERE SpecialOfferID IN (
	SELECT SpecialOfferID FROM Sales.SpecialOfferProduct SOP 
	INNER JOIN Production.Product P ON SOP.ProductID = P.ProductID
	INNER JOIN Production.ProductSubcategory PS ON P.ProductSubcategoryID = PS.ProductSubcategoryID
	INNER JOIN Production.ProductCategory PC ON PS.ProductCategoryID = PC.ProductCategoryID WHERE
	[PC].[Name] = 'Bikes')

go
-- 6. Listar los 3 productos menos solicitados en la regi�n �Pacific�
SELECT * FROM OPENQUERY ([DESKTOP-FAT50UF\BDD02], 'select top 3 so.ProductID, COUNT(*) as Cantidad_Productos
		from Pacifico.Sales.SalesOrderDetail so
		inner join Pacifico.Sales.SalesOrderHeader sh
		on sh.SalesOrderID = so.SalesOrderID 
		where sh.TerritoryID = 9 
		group by so.ProductID
		order by 2 asc
		')
		go
-- 7.  Actualizar la subcategor�a de los productos con productId del 1 al 4 a la subcategor�a valida 
-- para el tipo de producto 
declare @productId int,@subcategoriaID int
set @productId=1
set @subcategoriaID=3
IF EXISTS(SELECT ProductSubcategoryID
FROM AdventureWorks2019.Production.ProductSubcategory
WHERE ProductSubcategoryID = @subcategoriaID
	AND ProductCategoryID = (SELECT ProductCategoryID
	FROM AdventureWorks2019.Production.ProductSubcategory
	WHERE ProductSubcategoryID = 
		(select ProductSubcategoryID
	from AdventureWorks2019.Production.Product
	WHERE ProductID = @productID)))
		BEGIN
			update AdventureWorks2019.Production.Product
			set [ProductSubcategoryID] = @subcategoriaID, ModifiedDate = GETDATE()
			where ProductID = @productId
			PRINT('Acualizado')
		END
/*Para cambiar la subcategoria de un producto nececitamos que tenga una (asi validamos que la nueva sea de la misma categoria)
razon por la cual agregamos una subcategoria a los prodctos 2 3 y 4*/


-- 8.  Listar los productos que no est�n disponibles a la venta 
SELECT * FROM Production.Product
WHERE SellEndDate is not NULL
go

-- 9. Listar los clientes del territorio 1 y 4 que no tengan asociado un valor en personId
SELECT CustomerID FROM Sales.Customer 
WHERE (TerritoryID = 1 or TerritoryID = 4) AND PersonID IS NULL
go
-- 10 Listar los clientes del territorio 1 que tengan ordenes en otro territorio
select * from Norteamerica.sales.SalesOrderHeader 
	where TerritoryID = 1 and CustomerID in ( select CustomerID from Norteamerica.sales.SalesOrderHeader 
	where TerritoryID between 2 and 6 )
	union all
	select * from Norteamerica.sales.SalesOrderHeader 
	where TerritoryID = 1 and CustomerID in ( select CustomerID from Europe.Sales.SalesOrderHeader 
	where TerritoryID in (7,8,10) )
	union all
	select * from Norteamerica.sales.SalesOrderHeader 
	where TerritoryID = 1 and CustomerID in (SELECT * FROM OPENQUERY([DESKTOP-FAT50UF\BDD02],'select CustomerID from Pacifico.Sales.SalesOrderHeader 
	where TerritoryID =9') )
	
	
	