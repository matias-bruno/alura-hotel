/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Reserva;

/**
 *
 * @author matias-bruno
 */
public class ReservaDAO {
    // Una instancia de conexión a la base de datos para nuestra entidad
    private final Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }
    public void guardar(Reserva reserva) {
        try {
            
            try (PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO RESERVAS "
                        + "(fecha_entrada, fecha_salida, valor, forma_pago)"
                        + " VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, reserva.getFechaEntrada());
                statement.setString(2, reserva.getFechaSalida());
                statement.setInt(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
    
                statement.execute();
        
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el reserva: %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();
        
        String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";

        try {
    
            try (PreparedStatement statement = con
                    .prepareStatement(sql)) {
                statement.execute();
        
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        resultado.add(new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getString("FECHA_ENTRADA"),
                                resultSet.getString("FECHA_SALIDA"),
                                resultSet.getInt("VALOR"),
                                resultSet.getString("FORMA_PAGO")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    public int eliminar(Integer id) {
        try {

            try (PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?")) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(Integer id, String fechaEntrada, String fechaSalida, Integer valor, String formaDePago) {
         try {

            try (PreparedStatement statement = con.prepareStatement(
                    "UPDATE RESERVAS SET "
                    + " fecha_entrada = ?, "
                    + " fecha_salida = ?,"
                    + " valor = ?,"
                    + " forma_pago = ?"
                    + " WHERE ID = ?")) {
                statement.setString(1, fechaEntrada);
                statement.setString(2, fechaSalida);
                statement.setInt(3, valor);
                statement.setString(4, formaDePago);
                statement.setInt(5, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
