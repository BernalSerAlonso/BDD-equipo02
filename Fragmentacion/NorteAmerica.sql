use AdventureWorks2019;

--TABLAS QUE REQUIEREN FRAGMENTACION DERIVADA DE CUSTOMER
--* SalesOrderHeader
--	* SalesOrderDetail

-- 1.1 FRAGMENTACION HORIZONTAL DE CUSTOMER

Select *
from Sales.Customer C
where TerritoryID between 1 and 6;
--  Norte America {1,2,3,4,5,6}

Select *
from Sales.Customer C
where TerritoryID between 7 and 9;
-- Europa {7,8,10}

Select *
from Sales.Customer C
where TerritoryID = 10;
-- M3	-- Pacifico {10}

Select *
from Sales.Customer C
where TerritoryID > 10;
-- M4  


-- CONSULTAS QUE GENEREN LOS FRAGMENTOS DE CUSTOMER A PARTIR DE TerritoryID

-- FRAGMENTOS HORIZONTALES DE SalesOrderHeader DERIVADOS DE CUSTOMER

CREATE DATABASE Norteamerica
GO

use Norteamerica
GO
create schema Sales

SELECT TerritoryID
FROM Sales.SalesTerritory
WHERE [Group]='North America'


SELECT c.*
INTO Sales.Customer
FROM AdventureWorks2019.Sales.Customer c
where TerritoryID BETWEEN 1 AND 6

SELECT ORH.*
INTO Sales.SalesOrderHeader
FROM AdventureWorks2019.Sales.SalesOrderHeader ORH
WHERE TerritoryID in (SELECT TerritoryID
FROM AdventureWorks2019.Sales.SalesTerritory
WHERE [Group]='North America')

--Fragmento SalesOrderDetail Norteamerica
SELECT DISTINCT sod.*
INTO Sales.SalesOrderDetail
FROM AdventureWorks2019.Sales.SalesOrderDetail sod
    JOIN (SELECT *
    FROM Norteamerica.Sales.SalesOrderHeader) st
    ON sod.SalesOrderID = st.SalesOrderID
GO