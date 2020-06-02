package me.upp.arboles;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

public class Arboles {

    @Getter @Setter
    private Nodo raiz;

    public Arboles() { }

    public void insertar(final int info, final Nodo actual) {
        if (vacio()) {
            setRaiz(new Nodo(info));
            return;
        }

        if (info < actual.getInfo()) {
            if (actual.getIzq() == null) {
                actual.setIzq(new Nodo(info));
            } else {
                insertar(info, actual.getIzq());
            }
        } else {
            if (info > actual.getInfo()) {
                if (actual.getDer() == null) {
                    actual.setDer(new Nodo(info));
                } else {
                    insertar(info, actual.getDer());
                }
            } else {
                System.out.println("El nodo ya se encuentra en el arbol");
            }
        }

    }

    public void preorden(final Nodo actual) {
        if (actual == null) return;
        System.out.print(actual.getInfo() + "->");
        preorden(actual.getIzq());
        preorden(actual.getDer());
    }

    public void inorden(final Nodo actual) {
        if (actual == null) return;
        inorden(actual.getIzq());
        System.out.print(actual.getInfo() + "->");
        inorden(actual.getDer());
    }

    public void postorden(final Nodo actual) {
        if (actual == null) return;
        inorden(actual.getIzq());
        inorden(actual.getDer());
        System.out.print(actual.getInfo() + "->");
    }

    public boolean vacio() {
        return (raiz == null);
    }

    public void eliminar(Nodo actual, Nodo preActual) {
        //Esto debe de identificar 3 casos posibles y redirigirlos a la opcion
        System.out.println("Verificacion");

        //Primer caso -> Borrar una hoja | LISTO
        if (actual.getIzq() == null && actual.getDer() == null) {
            System.out.println("Aplica al primero caso");
            primerCaso(actual, preActual);
        }

        //Segundo caso -> Borrar solo un hijo | LISTO
        if (actual.getIzq() != null && actual.getDer() == null ||
            actual.getDer() != null && actual.getIzq() == null) {
            System.out.println("Aplica el segundo caso");
            segundoCaso(actual, preActual);
        }

        //Tercer caso -> Borrar con ambos hijos (izquierda y derecha)
        if (actual.getIzq() != null && actual.getDer() != null) {
            System.out.println("Aplica el tercer caso");
            tercerCaso(actual, preActual);
        }

    }

    public void primerCaso(Nodo actual, Nodo preActual) {
        if (preActual.getIzq().getInfo() == actual.getInfo())  {
            preActual.setIzq(null);
        } else if (preActual.getDer().getInfo() == actual.getInfo()) {
            preActual.setDer(null);
        }
        System.out.println("Ejecucion del primer caso");
        /*        -> Pruebas Nodo 4 y -3 son hojas <-
         * 7->3->0->-3->1->5->4->6->20->15->25->30-> (COMPLETO)
         * 7->3->0->-3->1->5->6->20->15->25->30-> (Se elimina el nodo 4)
         * 7->3->0->1->5->6->20->15->25->30-> (Se elimina el nodo -3)
         */
    }

    public void segundoCaso(Nodo actual, Nodo preActual) {
        Nodo siguiente = (actual.getDer() != null) ? actual.getDer() : actual.getIzq();
        actual.setInfo(siguiente.getInfo());
        actual.setIzq(siguiente.getIzq());
        actual.setDer(siguiente.getDer());
        System.out.println("Ejecucion del segundo caso");
        /*   -> Pruebas nodo 20 tiene como nodo hijo a 15 con datos Pre-Cardados (Caso 2) <-
         * 7->3->0->-3->1->5->4->6->20->15-> (COMPLETO)
         * 7->3->0->-3->1->5->4->6->15-> (Se elimina el nodo 20)
         */
    }

    public void tercerCaso(Nodo actual, Nodo preActual) {

    }

    public Nodo obtenerMayorOMenor(final Nodo derecha, final Nodo izquierda, final boolean mayor) {
        if (derecha == null && izquierda == null) return null;
        if (derecha == null) return izquierda;
        if (izquierda == null) return derecha;
        if (mayor) return (derecha.getInfo() > izquierda.getInfo()) ? derecha : izquierda;
        return (derecha.getInfo() < izquierda.getInfo()) ? derecha : izquierda;
    }

    //Esto busca el penultimoNodo para eliminar referencia
    public void buscarNodo(Nodo preActual, final int info) {
        if (preActual == null || preActual.getIzq() == null || preActual.getDer() == null) return;
        if (preActual.getIzq().getInfo() == info) {
            System.out.println("Valor encontrado : " + preActual.getInfo() + " -> " + preActual.getIzq().getInfo());
            eliminar(preActual.getIzq(), preActual);
            return;
        }
        if (preActual.getDer().getInfo() == info) {
            System.out.println("Valor encontrado : " + preActual.getInfo() + " -> " + preActual.getDer().getInfo());
            eliminar(preActual.getDer(), preActual);
            return;
        }
        buscarNodo(preActual.getIzq(), info);
        buscarNodo(preActual.getDer(), info);
    }

    public static void main(String[] args) {
        int opc;
        final Scanner scanner = new Scanner(System.in);
        final Arboles arboles = new Arboles();

        do {
            System.out.println("\n1) Insertar");
            System.out.println("2) Eliminar");
            System.out.println("3) Preorden");
            System.out.println("4) Inorden");
            System.out.println("5) Postorden");
            System.out.println("6) Salir");
            System.out.println("7) Pre-Cargar datos");
            System.out.println("8) Pre-Cargar datos (Caso 2)");

            opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("Ingrese la informacion");
                    arboles.insertar(scanner.nextInt(), arboles.getRaiz());
                    break;
                case 2:
                    System.out.println("Eliminar - Ingresa el valor a eliminar");
                    arboles.buscarNodo(arboles.getRaiz(), scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Preorden");
                    arboles.preorden(arboles.getRaiz());
                    break;
                case 4:
                    System.out.println("Inorden");
                    arboles.inorden(arboles.getRaiz());
                    break;
                case 5:
                    System.out.println("Postorden");
                    arboles.postorden(arboles.getRaiz());
                    break;
                case 6:
                    System.out.println("Salir");
                    break;
                case 7:
                    int[] datos = {
                            7, 3, 0, -3,
                            1, 5, 4, 6,
                            20, 15, 25, 30
                    };
                    for (int dato : datos) {
                        arboles.insertar(dato, arboles.getRaiz());
                        System.out.print(dato);
                        if (datos[datos.length - 1] != dato)
                            System.out.print(", ");
                    }
                    System.out.println("\nDatos cargados");
                    break;
                case 8:
                    int[] datos2 = {
                            7, 3, 0, -3,
                            1, 5, 4, 6,
                            20, 15
                    };
                    for (int dato : datos2) {
                        arboles.insertar(dato, arboles.getRaiz());
                        System.out.print(dato);
                        if (datos2[datos2.length - 1] != dato)
                            System.out.print(", ");
                    }
                    System.out.println("\nDatos (caso 2) cargados");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opc != 6);

    }

}
