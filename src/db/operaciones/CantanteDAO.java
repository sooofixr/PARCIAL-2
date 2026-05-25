package db.operaciones;

import db.DBConnection;
import model.Cantante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

    public void insertarCantante(Cantante c){
        String add_Cantante = "INSERT INTO Cantante (Nombre, Nacionalidad, generoMusical, Edad, CancionReconocida) VALUES (?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement query_insert = conn.prepareStatement(add_Cantante)
        ) {
            query_insert.setString(1,c.getNombre());
            query_insert.setString(2,c.getNacionalidad());
            query_insert.setString(3,c.getgeneroMusical());
            query_insert.setInt(4, c.getEdad());
            query_insert.setString(5,c.getCancionReconocida());
            query_insert.executeUpdate();

            System.out.println("Cantante adicionado con exito");
        }
        catch (SQLException e)
        {
            System.err.println("Error: "+e.getMessage());
        }

    }



}