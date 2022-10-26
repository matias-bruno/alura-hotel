/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.ReservaDAO;
import factories.ConnectionFactory;
import java.util.List;
import models.Reserva;

/**
 *
 * @author matias-bruno
 */
public class ReservaController {
    // Una instancia del DAO de nuestra entidad
    private final ReservaDAO reservaDAO;
    
    public ReservaController() {
        // Necesitamos una instancia de ConnectionFactory para pasarle una conexión al constructor del DAO
        ConnectionFactory factory = new ConnectionFactory();
        this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
    }
    public void guardar(Reserva reserva) {
        this.reservaDAO.guardar(reserva);
    }
    public List<Reserva> listar() {
        return this.reservaDAO.listar();
    }
    public int eliminar(Integer id) {
        return this.reservaDAO.eliminar(id);
    }
    public int modificar(Integer id, String fechaEntrada, String fechaSalida, Integer valor, String formaDePago) {
        return this.reservaDAO.modificar(id, fechaEntrada, fechaSalida, valor, formaDePago);
    }
}
