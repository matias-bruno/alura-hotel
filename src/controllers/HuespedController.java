/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import daos.HuespedDAO;
import factories.ConnectionFactory;
import java.util.List;
import models.Huesped;

/**
 *
 * @author matias-bruno
 */
public class HuespedController {
    // Una instancia del DAO de nuestra entidad
    private final HuespedDAO huespedDAO;
    
    public HuespedController() {
        // Necesitamos una instancia de ConnectionFactory para pasarle una conexión al constructor del DAO
        ConnectionFactory factory = new ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
    }
    public void guardar(Huesped huesped) {
        this.huespedDAO.guardar(huesped);
    }
    public List<Huesped> listar() {
        return this.huespedDAO.listar();
    }
    public Integer eliminar(Integer id) {
        return this.huespedDAO.eliminar(id);
    }
    public Integer modificar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer reservaId) {
        return this.huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, reservaId);
    }
}
