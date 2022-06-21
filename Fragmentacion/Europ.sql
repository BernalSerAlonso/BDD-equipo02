--creacion de la base de datos PARA EL FRAGMENTO EUROPE
CREATE DATABASE Europe
GO
--SHARDING REALIZADO EN EL SERVIDOR VINCULADO -> DESKTOP-FAT50UF\BDD01
use Europe
GO
-- CREAMOS LOS ESQUEMAS SALES Y PRODUCTION PARA LOS FRAGMENTOS CORRESPONDIENTES DE LAS TABLAS
create schema Sales
create schema Production

-- FRAGMENTO DE CUSTOMER CORRESPONDIENTE A EUROPE -> TerritoryID=7,8,9
SELECT c.* INTO Sales.Customer
FROM AdventureWorks2019.Sales.Customer c
where TerritoryID IN (7,8,10)
--FRAGMENTO DE SalesOrderHeader CORRESPONDIENTE A [GROUP] EN SalesTerritory -> 'EUROPE'
SELECT ORH.* INTO Sales.SalesOrderHeader
FROM AdventureWorks2019.Sales.SalesOrderHeader ORH
WHERE TerritoryID in (SELECT TerritoryID
						FROM AdventureWorks2019.Sales.SalesTerritory
						WHERE [Group]='EUROPE') 

--Fragmento SalesOrderDetail Pacifico A PARTIR DE UN JOIN DE SalesOrderDetail CON EL FRAGMENTO DE SalesOrderHeader CORRESPONDIENTE EUROPE
SELECT DISTINCT sod.* INTO Sales.SalesOrderDetail
FROM AdventureWorks2019.Sales.SalesOrderDetail sod
JOIN (SELECT * FROM Europe.Sales.SalesOrderHeader) st
ON sod.SalesOrderID = st.SalesOrderID
GO
