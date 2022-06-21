--creacion de la base de datosPARA EL FRAGMENTO NorteAmerica
CREATE DATABASE Norteamerica
GO
--SHARDING REALIZADO EN EL SERVIDOR VINCULADO -> DESKTOP-FAT50UF\BDD01
use Norteamerica
GO
-- CREAMOS LOS ESQUEMAS SALES Y PRODUCTION PARA LOS FRAGMENTOS CORRESPONDIENTES DE LAS TABLAS
create schema Sales
create schema Production

SELECT TerritoryID
FROM Sales.SalesTerritory
WHERE [Group]='North America'

-- FRAGMENTO DE CUSTOMER CORRESPONDIENTE A NorteAmerica -> TerritoryID=1,2,3,4,5,6

SELECT c.* INTO Sales.Customer
FROM AdventureWorks2019.Sales.Customer c
where TerritoryID BETWEEN 1 AND 6
--FRAGMENTO DE SalesOrderHeader CORRESPONDIENTE A [GROUP] EN SalesTerritory -> 'North America'
SELECT ORH.* INTO Sales.SalesOrderHeader
FROM AdventureWorks2019.Sales.SalesOrderHeader ORH
WHERE TerritoryID in (SELECT TerritoryID
						FROM AdventureWorks2019.Sales.SalesTerritory
						WHERE [Group]='North America') 

--Fragmento SalesOrderDetail Pacifico A PARTIR DE UN JOIN DE SalesOrderDetail CON EL FRAGMENTO DE SalesOrderHeader CORRESPONDIENTE A NorteAmerica
SELECT DISTINCT sod.* INTO Sales.SalesOrderDetail
FROM AdventureWorks2019.Sales.SalesOrderDetail sod
JOIN (SELECT * FROM Norteamerica.Sales.SalesOrderHeader) st
ON sod.SalesOrderID = st.SalesOrderID
GO
