/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Huesped;

/**
 *
 * @author matias-bruno
 */
public class HuespedDAO {
    // Una instancia de conexión a la base de datos para nuestra entidad
    private final Connection con;
    
    // Un solo constructor con el parametro de la conexión
    public HuespedDAO(Connection con) {
        this.con = con;
    }
    //Aquí van los métodos que acceden a la base de datos a través de la conexión
    public void guardar(Huesped huesped) {
        try {
            
            try (PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO HUESPEDES "
                        + "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id)"
                        + " VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getReservaId());
    
                statement.execute();
        
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el huesped: %s", huesped));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();
        
        String sql = "SELECT * FROM huespedes";
        
        try {
            try(PreparedStatement statement = con.prepareStatement(sql)) {
                // Como es una sentencia preparada se debe ejecutar primero
                statement.execute();
                try(ResultSet resultSet = statement.getResultSet()) {
                    // El ResultSet puede contener varios registros, mientras que existe uno más
                    while(resultSet.next()) {
                        // Se crea un objeto
                        Huesped huesped = new Huesped(resultSet.getInt("id"),
                                                      resultSet.getString("nombre"),
                                                      resultSet.getString("apellido"),
                                                      resultSet.getString("fecha_nacimiento"),
                                                      resultSet.getString("nacionalidad"),
                                                      resultSet.getString("telefono"),
                                                      resultSet.getInt("reserva_id"));
                        // Se agrega a la lista
                        resultado.add(huesped);
                    }
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
    public Integer eliminar(Integer id) {
        try {

            try (PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?")) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer modificar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer reservaId) {
        try {

            try (PreparedStatement statement = con.prepareStatement(
                    "UPDATE HUESPEDES SET "
                    + " nombre = ?, "
                    + " apellido = ?,"
                    + " fecha_nacimiento = ?,"
                    + " nacionalidad = ?,"
                    + " telefono= ?,"
                    + " reserva_id = ?"
                    + " WHERE ID = ?")) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, fechaNacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, reservaId);
                statement.setInt(7, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
