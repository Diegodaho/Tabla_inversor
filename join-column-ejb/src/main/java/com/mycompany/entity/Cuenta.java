/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author narukom
 */
@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inversor_id")
    private Inversor inversor;

    public Cuenta() {
    }

    public Cuenta(int id, String numeroCuenta, Inversor inversor) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.inversor = inversor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Inversor getInversor() {
        return inversor;
    }

    public void setInversor(Inversor inversor) {
        this.inversor = inversor;
    }
    
    
}
