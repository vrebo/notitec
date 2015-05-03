package org.itver.notitecentidadbd.entidad;

/**
 *
 * @author vrebo
 */
public abstract class Entidad<E> {

    protected E id;

    public E getId() {
        return id;
    }

    public void setId(E id) {
        this.id = id;
    }
}
