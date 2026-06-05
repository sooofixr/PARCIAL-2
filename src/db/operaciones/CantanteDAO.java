package db.operaciones;

import db.DBConnection;
import model.Cantante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Cantante> consultarTodos() {
        List<Cantante> lista = new ArrayList<>();
        String sql = "SELECT * FROM cantantes";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cantante c = new Cantante(
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("generomusical"),
                        rs.getInt("edad"),
                        rs.getString("cancionreconocida"),
                        rs.getInt("id"),
                        rs.getTimestamp("registrado")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar todos los registros: " + e.getMessage());
        }
        return lista;
    }
    public Cantante consultarUnRegistro(int id) {
        String sql = "SELECT * FROM cantantes WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cantante(
                            rs.getString("nombre"),
                            rs.getString("nacionalidad"),
                            rs.getString("generomusical"),
                            rs.getInt("edad"),
                            rs.getString("cancionreconocida"),
                            rs.getInt("id"),
                            rs.getTimestamp("registrado")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el registro con ID " + id + ": " + e.getMessage());
        }
        return null;
    }
    public List<Cantante> filtrarPorCriterio(String columna, String valorCriterio) {
        List<Cantante> lista = new ArrayList<>();

        String sql = "SELECT * FROM cantantes WHERE LOWER(" + columna + ") LIKE LOWER(?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + valorCriterio + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cantante c = new Cantante(
                            rs.getString("nombre"),
                            rs.getString("nacionalidad"),
                            rs.getString("generomusical"),
                            rs.getInt("edad"),
                            rs.getString("cancionreconocida"),
                            rs.getInt("id"),
                            rs.getTimestamp("registrado")
                    );
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar el filtro dinámico: " + e.getMessage());
        }
        return lista;
    }

}