package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServer_Production {

    public Connection conexion;

    //Variables para acceder a la base de datos
    private final String usuario = "sa";
    private final String contraseña = "3PtHF4Jm2G";//Contraseña
    private final String bd = "AdventureWorks2019Production";
    private final String ip = "10.147.20.200\\BDD01";//Nombre de la instancia
    private final String puerto = "1488";//Puerto de la instancia

    private final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String BD_URL = "jdbc:sqlserver://" + ip + ":" + puerto + ";" + "databaseName=" + bd;

    public void conectar() throws Exception {
        try {
            conexion = DriverManager.getConnection(BD_URL, usuario, contraseña);
            System.out.println("CONECTADO CORRECTAMENTE SQLSERVER");
        } catch (SQLException e) {
            System.out.println("ERROR " + e);
        }
    }

    public Connection obtenerconexion() {
        try {
            conexion = DriverManager.getConnection(BD_URL, usuario, contraseña);
        } catch (SQLException e) {
            System.out.println("ERROR " + e);
        }
        return conexion;
    }

    public void cerrar() throws SQLException {
        if (conexion != null) {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        }
    }

}
