/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaces;

import com.mycompany.dto.DTOCuenta;
import com.mycompany.dto.DTOInversor;
import com.mycompany.entity.Inversor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author narukom
 */
@Local
public interface InversorFacadeLocal {

    void create(Inversor inversor);

    void edit(Inversor inversor);

    void remove(Inversor inversor);

    Inversor find(Object id);

    List<Inversor> findAll();

    List<Inversor> findRange(int[] range);

    int count();
    
    public void crearInversor(DTOInversor dtoInversor, DTOCuenta dtoCuenta);
    
    public void cerarInver2(DTOInversor dtoinversor);
}
