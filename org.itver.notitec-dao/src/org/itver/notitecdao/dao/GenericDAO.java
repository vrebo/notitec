/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itver.notitecdao.dao;

import java.util.List;
import org.itver.notitecentidadbd.entidad.Entidad;

/**
 * Interface que define el comportamiento del patrón DAO (Database Access
 * Object). Esté comportamiento consiste en realizar operaciones CRUD (Create,
 * Read, Update, Delete) dentro de un servidor de base de datos.
 *
 * La interface está paramétrizada para que los miembros de la clase puedan
 * realizar las operaciones CRUD correctamente para cada implementacion de la
 * interface, dado que cada implementación manejará un tipo de entidad distinta
 * y cada entidad tiene un tipo de dato particular para la clave primaria con la
 * que son identificadas de forma única dentro de la base de datos.
 *
 * @author vrebo
 * @param <K> Parámetro del tipo de dato de la clave primaria de la entidad.
 * @param <E> Pátametro de la clase de entidad que manejará el DAO.
 */
public interface GenericDAO<K, E extends Entidad> {

    public boolean persiste(E entidad);

    public E buscaPorId(K id);

    public List<E> buscaTodos();

    public boolean salva(E entidad);

    public boolean elimina(K id);

    public boolean elimina(E entidad);
}
