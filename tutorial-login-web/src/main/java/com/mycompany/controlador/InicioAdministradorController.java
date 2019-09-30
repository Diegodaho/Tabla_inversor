/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controlador;

import com.mycompany.dto.DTOCuenta;
import com.mycompany.dto.DTOInversor;
import com.mycompany.entity.Cuenta;
import com.mycompany.entity.Inversor;
import com.mycompany.interfaces.InversorFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author narukom
 */
@Named
@SessionScoped
public class InicioAdministradorController implements Serializable{

   
    
    private String nombre;
    private String cuenta;
    private List<Inversor> listaInversores;
    private Inversor inv;
    
    @EJB
    InversorFacadeLocal inversorCon;
    /**
     * Creates a new instance of InicioAdministradorController
     */
    public InicioAdministradorController() {
        listaInversores=new ArrayList();
    }
    
    @PostConstruct
    public void _init(){
        listaInversores = inversorCon.findAll();
    }
    
  
    
    public void agregarInversor(){
        DTOInversor dtoInversor = new DTOInversor();
        DTOCuenta dtoCuenta = new DTOCuenta();
        dtoInversor.setNombre(nombre);
        dtoCuenta.setNumeroCuenta(cuenta);
        inversorCon.crearInversor(dtoInversor, dtoCuenta);
        FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado",
                    "Inversor "+nombre+" agregado satisfactoriamente"));
    }
    
    public void obtenerInversores(){
        listaInversores = inversorCon.findAll();
    }
    
    public void onRowEdit(RowEditEvent event) {
        inv = inversorCon.find(((Inversor) event.getObject()).getId());
        inv.setNombre(((Inversor) event.getObject()).getNombre());
        if(inv.getCuenta()!=null){
            inv.getCuenta().setNumeroCuenta(cuenta);
        }else{
            Cuenta eCuenta = new Cuenta();
            eCuenta.setNumeroCuenta(cuenta);
            inv.setCuenta(eCuenta);
            eCuenta.setInversor(inv);
        }
        inversorCon.edit(inv);
        FacesMessage msg = new FacesMessage("Inversor Editado", ((Inversor) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición cancelada para el inversor ", ((Inversor) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void eliminarInversor(Inversor inversorSeleccionado) {
        FacesMessage msg = new FacesMessage("Usuario", inversorSeleccionado.getNombre() + " eliminado");
        inversorCon.remove(inversorSeleccionado);
        obtenerInversores();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public List<Inversor> getListaInversores() {
        return listaInversores;
    }

    public void setListaInversores(List<Inversor> listaInversores) {
        this.listaInversores = listaInversores;
    }
    
    
}
