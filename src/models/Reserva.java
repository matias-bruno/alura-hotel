/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author matias-bruno
 */
public class Reserva {
    
    private Integer id;
    
    private String fechaEntrada;
    
    private String fechaSalida;
    
    private Integer valor;
    
    private String formaPago;
        
    public static String[] formasDePago = {"Tarjeta de Crédito", "Tarjeta de Débito", "Efectivo"};
    
    // Por ahora simularemos un único precio para todas las habitaciones
    private static Integer valorPorDia = 100;
    
    // Este valor se debe calcular dinámicamente
    public static Integer precioTotal(Date fecha1, Date fecha2) {   
        long diffInMillies = Math.abs(fecha2.getTime() - fecha1.getTime());
        return ((Long)(TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) * valorPorDia)).intValue();
    }

    public Reserva(String fechaEntrada, String fechaSalida, int valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Reserva(int id, String fechaEntrada, String fechaSalida, int valor, String formaPago) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    @Override
    public String toString() {
        return  this.fechaEntrada + " " +
                this.fechaSalida + " " + 
                this.formaPago + " " +
                String.valueOf(this.valor);
    }
    
    
}
