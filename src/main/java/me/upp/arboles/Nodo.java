package me.upp.arboles;

import lombok.Getter;
import lombok.Setter;

public class Nodo {

    @Getter @Setter
    private int info;
    @Getter @Setter
    private Nodo izq;
    @Getter @Setter
    private Nodo der;

    public Nodo(int info) {
        this.info = info;
    }

}
