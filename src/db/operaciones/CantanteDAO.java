package db.operaciones;

import db.DBConnection;
import model.Cantante;

import java.sql.*;

public class CantanteDAO {

    public void insertarCantante(Cantante c){
        String add_cantantes = "INSERT INTO cantantes (nombre, nacionalidad, generoMusical, edad, cancionReconocida) VALUES (?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query_insert = conn.prepareStatement(add_cantantes)
        ) {
            query_insert.setString(1,c.getNombre());
            query_insert.setString(2,c.getNacionalidad());
            query_insert.setString(3,c.getGeneromusical());
            query_insert.setInt(4, c.getEdad());
            query_insert.setString(5,c.getCancionreconocida());
            query_insert.executeUpdate();

            System.out.println("Cantante adicionado con exito");
        }
        catch (SQLException e)
        {
            System.err.println("Error: "+e.getMessage());
        }

    }



}