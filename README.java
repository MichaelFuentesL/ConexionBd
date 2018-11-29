package conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Alumnos {

    private int rut;
    private String nombre;
    private String apellidos;
    private int estado;

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean eliminar() {
        boolean res = false; // tenemos que demostrar que es verdadero
        try { // y catch sirve para capturar ecepciones (puede ser capturada pueden ser controladas)
            Class.forName("com.mysql.jdbc.Driver");  //  mensionar la libreria del driver de conexion que vamos a ocupar
            String strCon = "jdbc:mysql://localhost/instituto"; // string de conexion con la base de datos y que describe como y donde se creara la conexion a la base de datos
            Connection con = DriverManager.getConnection(strCon, "root", ""); // traiga una conexion desde el string anterior

            String query = "delete from Alumnos where rut = ?"; // una consulta para traer de la base dato
            PreparedStatement smt = con.prepareStatement(query); // no ejecuta consulta para decirle a la bd que la ejecutare que reserve memoria
            smt.setInt(1, rut);
            res = smt.executeUpdate() > 0; //smt.executeUpdate() retorna la cantidad de filas que se afecto a la tabla sirve para los update, insert y 
            smt.close();
            con.close();  // cerrar conexion  por que gastan memoria, tambien se limitan las conexiones con la base de datos.
        } catch (Exception e) {  // a la esepcion la llamo e
            e.printStackTrace();  // 
        }
        return res;
    }

    public boolean guardar() {
        boolean res = false; // tenemos que demostrar que es verdadero
        try { // y catch sirve para capturar ecepciones (puede ser capturada pueden ser controladas)
            Class.forName("com.mysql.jdbc.Driver");  //  mensionar la libreria del driver de conexion que vamos a ocupar
            String strCon = "jdbc:mysql://localhost/instituto"; // string de conexion con la base de datos y que describe como y donde se creara la conexion a la base de datos
            Connection con = DriverManager.getConnection(strCon, "root", ""); // traiga una conexion desde el string anterior

            String query = "insert into alumnos values (?,?,?,?)"; // una consulta para traer de la base dato
            PreparedStatement smt = con.prepareStatement(query); // no ejecuta consulta para decirle a la bd que la ejecutare que reserve memoria
            smt.setInt(1, rut);
            smt.setString(2, nombre);
            smt.setString(3, apellidos);
            smt.setInt(4, estado);
            res = smt.executeUpdate() > 0; //smt.executeUpdate() retorna la cantidad de filas que se afecto a la tabla sirve para los update, insert y 
            smt.close();
            con.close();  // cerrar conexion  por que gastan memoria, tambien se limitan las conexiones con la base de datos.
        } catch (Exception e) {  // a la esepcion la llamo e
            e.printStackTrace();  // 
        }
        return res;
    }

    

    public boolean modificar() {
        boolean res = false; // tenemos que demostrar que es verdadero
        try { // y catch sirve para capturar ecepciones (puede ser capturada pueden ser controladas)
            Class.forName("com.mysql.jdbc.Driver");  //  mensionar la libreria del driver de conexion que vamos a ocupar
            String strCon = "jdbc:mysql://localhost/instituto"; // string de conexion con la base de datos y que describe como y donde se creara la conexion a la base de datos
            Connection con = DriverManager.getConnection(strCon, "root", ""); // traiga una conexion desde el string anterior

            String query = "update Alumnos set nombre=?,  apellidos=?, estado=?  where RUT=?"; // una consulta para traer de la base dato
            PreparedStatement smt = con.prepareStatement(query); // no ejecuta consulta para decirle a la bd que la ejecutare que reserve memoria
            smt.setString(1, nombre);
            smt.setString(2, apellidos);
            smt.setInt(3, estado);
            smt.setInt(4, rut);
            res = smt.executeUpdate() > 0; //smt.executeUpdate() retorna la cantidad de filas que se afecto a la tabla sirve para los update, insert y 
            smt.close();
            con.close();  // cerrar conexion  por que gastan memoria, tambien se limitan las conexiones con la base de datos.
        } catch (Exception e) {  // a la esepcion la llamo e
            e.printStackTrace();  // 
        }
        return res;
    }
    public boolean buscar() {
        boolean res = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String strCon = "jdbc:mysql://localhost/instituto";
            Connection con = DriverManager.getConnection(strCon, "root", "");

            String query = "select * from Alumnos  where rut= "+rut+""; // una consulta para traer de la base dato
            PreparedStatement smt = con.prepareStatement(query); // no ejecuta consulta para decirle a la bd que la ejecutare que reserve memoria
            ResultSet resu = smt.executeQuery();
            while (resu.next()) { 
                rut = resu.getInt("rut");
                nombre = resu.getString("nombre");
                apellidos = resu.getString("apellidos");
                estado = resu.getInt("estado");
            }
            resu.close();
            smt.close();
            con.close();  // cerrar conexion  por que gastan memoria, tambien se limitan las conexiones con la base de datos.
        } catch (Exception e) {  // a la esepcion la llamo e
            e.printStackTrace();  // 
        }
        return res;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            
        Alumnos ob = new Alumnos();
        ob.setRut(17840840);
        if (ob.buscar()) {
                System.out.println("Rut: " + ob.getRut());
                System.out.println("Nombre: " + ob.getNombre());
                System.out.println("Apellidos: " + ob.getApellidos());
                System.out.println("Estado: " + ob.getEstado());
        } else {
            System.out.println("usuario no existe");
        }
   }
}
    
