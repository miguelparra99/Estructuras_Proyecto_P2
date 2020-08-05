package Tree;

import Apoyo.HuffmanInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {

    private BinaryNode<T> root;
    private String elementos[];

    public BinaryTree() {
        this.root = new BinaryNode<>();
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                }
                if (subtree.root.getRight() != null) {
                    stack.push(subtree.root.getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }

    public BinaryNode<T> recursiveSearch(T content, Comparator cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            //if (this.root.getContent().equals(content)) {
            if (cmp.compare(content, this.root.getContent()) == 0) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content, cmp);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content, cmp);
                    }
                }
                return tmp;
            }
        }
    }

    public BinaryNode<T> iterativeSearch(T content, Comparator cmp) {
        Queue<BinaryNode<T>> Cola = new LinkedList<>();
        Cola.add(this.root);

        if (this.isEmpty()) {
            return null;
        } else {
            while (Cola.size() != 0) {
                BinaryNode<T> Viajero = Cola.poll();
                if (Viajero.getLeft().getRoot() != null) {
                    if (cmp.compare(content, Viajero.getLeft().getRoot().getContent()) == 0) {
                        return root.getLeft().getRoot();
                    } else {
                        Cola.add(root.getLeft().getRoot());
                    }
                }
                if (Viajero.getRight().getRoot() != null) {
                    if (cmp.compare(content, Viajero.getRight().getRoot().getContent()) == 0) {
                        return root.getRight().getRoot();
                    } else {
                        Cola.add(root.getRight().getRoot());
                    }
                }

            }
            return null;
        }
    }

    public void inOrderRecursive() {
        if (this.isEmpty()) {
            return;
        } else {
            if (this.root.getLeft() != null) {
                this.root.getLeft().inOrderRecursive();
            }
            System.out.println(this.root.getContent().toString());
            if (this.root.getRight() != null) {
                this.root.getRight().inOrderRecursive();

            }

        }
    }

    public void preOrdenRecursive() {
        if (this.isEmpty()) {
            return;
        } else {
            System.out.println(this.root.getContent());
            if (this.root.getLeft() != null) {
                this.root.getLeft().preOrdenRecursive();
            }

            if (this.root.getRight() != null) {
                this.root.getRight().preOrdenRecursive();

            }

        }
    }

    public void postOrdenRecursive() {

        if (this.isEmpty()) {
            return;
        } else {

            if (this.root.getLeft() != null) {
                this.root.getLeft().postOrdenRecursive();
            }

            if (this.root.getRight() != null) {
                this.root.getRight().postOrdenRecursive();
            }
            System.out.println(this.root.getContent());

        }

    }

    public void inOrderIterativa() {
        Stack<BinaryTree<T>> stack = new Stack();
        if (this.isEmpty()) {

        } else {
            stack.push(this);
            Boolean valor = true;

            while (valor) {
                BinaryTree<T> subtree = stack.pop();
                stack.push(subtree);

                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                } else {
                    valor = false;
                }
            }
            while (!stack.isEmpty()) {
                BinaryTree<T> ayuda = stack.pop();

                System.out.println(ayuda.getRoot().getContent());
                if (ayuda.getRight() != null) {
                    stack.push(ayuda.getRight());
                    if (ayuda.getRight().getLeft() != null) {
                        stack.push(ayuda.getRight().getLeft());
                    }
                }
            }

        }
    }

    public void preOrdenIterativa() {
        Stack<BinaryTree<T>> stack = new Stack();
        Stack<BinaryTree<T>> stack2 = new Stack();
        Queue<BinaryTree<T>> Cola = new LinkedList();
        Stack<BinaryTree<T>> stack3 = new Stack();
        if (this.isEmpty()) {

        } else {
            stack.push(this);
            Boolean valor = true;

            while (valor) {
                BinaryTree<T> subtree = stack.pop();
                stack.push(subtree);

                if (subtree.root.getLeft() != null) {
                    stack.push(subtree.root.getLeft());
                } else {
                    valor = false;
                }
            }
            while (!stack.isEmpty()) {
                stack2.push(stack.pop());
            }
            while (!stack2.isEmpty()) {
                Cola.add(stack2.pop());
            }
            while (!Cola.isEmpty()) {
                BinaryTree<T> ayuda = Cola.poll();

                System.out.println(ayuda.getRoot().getContent());
                if (ayuda.getRight() != null) {

                    stack3.push(ayuda.getRight());
                }

            }
            while (!stack3.isEmpty()) {
                BinaryTree<T> ayuda = stack3.pop();
                System.out.println(ayuda.getRoot().getContent());

                if (ayuda.getRight() != null) {
                    stack3.push(ayuda.getRight());

                }
                if (ayuda.getLeft() != null) {

                    stack3.push(ayuda.getLeft());
                }

            }

        }

    }

    public void posordenIterative() {
        if (this.isEmpty()) {
            return;
        } else {
            Stack<BinaryTree<T>> Pila = new Stack<>();
            Stack<BinaryTree<T>> PilaTree = new Stack<>();
            Pila.push(this);

            while (!Pila.isEmpty()) {
                BinaryTree<T> tree = Pila.pop();
                PilaTree.push(tree);
                if (tree.root.getLeft() != null) {
                    Pila.push(tree.root.getLeft());
                }
                if (tree.root.getRight() != null) {
                    Pila.push(tree.root.getRight());
                }
            }

            while (!PilaTree.isEmpty()) {
                System.out.print(PilaTree.pop().getRoot().getContent());
            }
        }
    }

    /////////////////////////DEBER/////////////////////////////
    /////MINIMO RECURSIVO E ITERATIVO//////////////
    public PriorityQueue<Integer> AyudaMinRecursive() {
        PriorityQueue<Integer> ColaNumeros = new PriorityQueue<>();
        if (this.isEmpty()) {
            return null;
        } else {
            if (this.root.getLeft() != null) {
                ColaNumeros.offer((Integer) root.getLeft().getRoot().getContent());
                this.root.getLeft().AyudaMinRecursive();
            }
            if (this.root.getRight() != null) {
                ColaNumeros.offer((Integer) root.getRight().getRoot().getContent());
                this.root.getRight().AyudaMinRecursive();

            }
        }
        ColaNumeros.offer((Integer) root.getContent());
        return ColaNumeros;
    }

    public int getMinRecursive() {
        PriorityQueue<Integer> a = AyudaMinRecursive();
        return a.peek();
    }

    public PriorityQueue<Integer> AyudaMinIterativo() {
        PriorityQueue<Integer> ColaNumeros = new PriorityQueue<>();
        if (this.isEmpty()) {
            return null;
        } else {
            Stack<BinaryTree<T>> Pila = new Stack<>();
            Stack<BinaryTree<T>> PilaTree = new Stack<>();
            Pila.push(this);

            while (!Pila.isEmpty()) {
                BinaryTree<T> tree = Pila.pop();
                PilaTree.push(tree);
                if (tree.root.getLeft() != null) {
                    Pila.push(tree.root.getLeft());
                }
                if (tree.root.getRight() != null) {
                    Pila.push(tree.root.getRight());
                }
            }
            while (!PilaTree.isEmpty()) {
                ColaNumeros.offer((Integer) PilaTree.pop().getRoot().getContent());
            }
        }
        return ColaNumeros;
    }

    public int getMinInterativo() {
        PriorityQueue<Integer> a = AyudaMinIterativo();
        return a.peek();
    }

    ////////CONTAR NIVELES RECURSIVO E ITERATIVO/////
    public int countLevelsRecursive() {
        int altura = 0;
        if (this.root == null) {
            return 0;
        }
        if (isLeaf()) {
            return 1;
        }
        if (this.isLeaf() != true) {
            if (this.getLeft() != null) {
                altura = Math.max(altura, this.getLeft().countLevelsRecursive());
            }
            if (this.getRight() != null) {
                altura = Math.max(altura, this.getRight().countLevelsRecursive());
            }
            altura++;
        }
        return altura;
    }

    public int countLevelsIterativo() {
        Stack<HashMap<Integer, BinaryTree<T>>> pila = new Stack();
        HashMap<Integer, BinaryTree<T>> dict = new HashMap<>();
        int valor = 0;
        dict.put(valor, this);

        pila.push(dict);
        if (this.isEmpty()) {
        } else {

            try {

                while (!pila.isEmpty()) {
                    HashMap mapa = pila.pop();
                    BinaryTree<T> arbol = (BinaryTree<T>) mapa.get(valor);
                    dict.put(valor, arbol);
                    if (mapa.keySet().contains(valor)) {
                        valor++;
                        if (arbol.getRoot().getLeft() != null) {
                            dict = new HashMap<>();
                            dict.put(valor, arbol.getLeft());
                            pila.push(dict);

                        }
                        if (arbol.getRoot().getRight() != null) {
                            dict = new HashMap<>();
                            dict.put(valor, arbol.getRight());
                            pila.push(dict);
                        }

                    }

                }
            } catch (EmptyStackException e) {

            }

        }
        return valor;
    }

    ////////ISIDENTICAL RECURSIVO E ITERATIVO /////
    public String binaryTreeAStringIterativa(BinaryTree<T> nodo) {
        String palabra = "";
        if (this.isEmpty()) {
            return null;
        } else {
            Stack<BinaryTree<T>> Pila = new Stack<>();
            Stack<BinaryTree<T>> PilaTree = new Stack<>();
            Pila.push(nodo);

            while (!Pila.isEmpty()) {
                BinaryTree<T> tree = Pila.pop();
                PilaTree.push(tree);
                if (tree.root.getLeft() != null) {
                    Pila.push(tree.root.getLeft());
                }
                if (tree.root.getRight() != null) {
                    Pila.push(tree.root.getRight());
                }
            }

            while (!PilaTree.isEmpty()) {
                palabra = palabra + PilaTree.pop().getRoot().getContent();
            }

        }
        return palabra;
    }

    public Boolean isIdenticalIterativa(BinaryTree<T> nodo) {
        String Tree1 = binaryTreeAStringIterativa(this);
        String Tree2 = binaryTreeAStringIterativa(nodo);
        if (Tree1.equals(Tree2)) {
            return true;
        } else {
            return false;
        }
    }

    public String binaryTreeAStringRecursiva(BinaryTree<T> nodo) {
        String palabra = "";
        if (nodo.isEmpty()) {
            return null;
        } else {

            if (nodo.root.getLeft() != null) {
                palabra += binaryTreeAStringRecursiva(nodo.root.getLeft());
            }

            if (nodo.root.getRight() != null) {
                palabra += binaryTreeAStringRecursiva(nodo.root.getRight());
            }
            palabra = palabra + nodo.root.getContent();

        }
        return palabra;
    }

    public Boolean isIdenticalRecursiva(BinaryTree<T> nodo) {
        String Tree1 = binaryTreeAStringRecursiva(this);
        String Tree2 = binaryTreeAStringRecursiva(nodo);
        if (Tree1.equals(Tree2)) {
            return true;
        } else {
            return false;
        }
    }

    /////ARBOL ZURDO RECURSIVO E ITERATIVO/////////
    public int contarRecursivo(BinaryTree<T> nodo) {
        int cantidad = 1;
        if (nodo.root.getLeft() != null) {

            cantidad += contarRecursivo(nodo.getRoot().getLeft());
        }
        if (nodo.root.getRight() != null) {
            cantidad += contarRecursivo(nodo.getRoot().getRight());
        }

        return cantidad;
    }

    public Boolean isLeftyRecursivo() {
        if (this.isEmpty()) {
            return true;
        } else if (this.isLeaf()) {
            return true;
        } else {
            int der = contarRecursivo(this.getRight());
            int izq = contarRecursivo(this.getLeft());
            if (der >= izq) {
                return false;
            } else {
                return true;
            }
        }
    }

    public int contarIterativo(BinaryTree<T> nodo) {
        Stack<BinaryTree<T>> pila = new Stack();
        int cantidad = 1;
        pila.push(nodo);
        if (this.isEmpty()) {
            return 0;
        } else {
            while (!pila.isEmpty()) {
                BinaryTree<T> prueba = pila.pop();
                if (prueba.getRoot().getLeft() != null) {
                    pila.push(prueba.root.getLeft());
                    cantidad++;
                }
                if (prueba.getRoot().getRight() != null) {
                    pila.push(prueba.root.getRight());
                    cantidad++;
                }
            }
            return cantidad;
        }
    }

    public Boolean isLeftyInterativo() {
        if (!this.isEmpty()) {
            int der = contarIterativo(this.root.getRight());
            int izq = contarIterativo(this.root.getLeft());
            if (der >= izq) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    ///////////MAYOR POR NIVEL///////////////////
    public void BotaRecursivo(BinaryTree<T> nodo, int nivel) {
        if (!nodo.isEmpty()) {

            if (elementos[nivel] != null) {
                elementos[nivel] += nodo.getRoot().getContent().toString() + " ";
            } else {
                elementos[nivel] = " ";
                elementos[nivel] += nodo.getRoot().getContent().toString() + " ";
            }
            if (nodo.getRoot().getLeft() != null) {
                BotaRecursivo(nodo.getRoot().getLeft(), nivel + 1);
            }

            if (nodo.getRoot().getRight() != null) {
                BotaRecursivo(nodo.getRoot().getRight(), nivel + 1);
            }
        }
    }

    public void largestValueOfEachLevelRecursivo() {
        elementos = new String[this.countLevelsRecursive()];
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = " ";

        }
        BotaRecursivo(this, 0);
        for (int i = 0; i < elementos.length; i++) {
            String[] parts = elementos[i].split(" ");
            int mayor = 0;
            for (String part : parts) {
                try {
                    int valor = Integer.parseInt(part);
                    if (valor > mayor) {
                        mayor = valor;
                    }
                } catch (NumberFormatException e) {
                }
            }
            System.out.println("El mayor del nivel : " + (i + 1) + " es : " + mayor);
        }
    }

    public void BotaIterativo(BinaryTree<T> nodo) {
        int nivel = 0;
        //System.out.println(elementos.length+"umerooo permitido");
        if (this.isEmpty()) {

        } else {
            Stack<BinaryTree<T>> Pila = new Stack<>();
            Pila.push(nodo);
            while (!Pila.isEmpty()) {
                BinaryTree<T> tree = Pila.pop();

                try {
                    if (elementos[nivel] != null) {
                        elementos[nivel] += tree.getRoot().getContent().toString() + " ";
                    }
                    if (tree.root.getLeft() != null) {
                        Pila.push(tree.root.getLeft());

                    }
                    if (tree.root.getRight() != null) {
                        Pila.push(tree.root.getRight());
                    }
                    nivel++;
                } catch (Exception e) {

                }
            }

        }
    }

    public void largestValueOfEachLevelIterativo() {
        elementos = new String[this.countLevelsRecursive()];
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = " ";

        }
        BotaIterativo(this);
        for (int i = 0; i < elementos.length; i++) {
            String[] parts = elementos[i].split(" ");
            int mayor = 0;
            for (String part : parts) {
                try {
                    int valor = Integer.parseInt(part);
                    if (valor > mayor) {
                        mayor = valor;
                    }
                } catch (NumberFormatException e) {
                }
            }
            System.out.println("El mayor del nivel : " + (i + 1) + " es : " + mayor);
        }
    }

    /////////////Los nodos con un solo hijo////////////////
    public void countNodesWithOnlyChildRecursivo(BinaryTree<T> nodo) {
        int valor = 0;

        if (!nodo.isEmpty()) {
            if (nodo.getRoot().getLeft() != null) {
                countNodesWithOnlyChildRecursivo(nodo.getRoot().getLeft());
                valor = 1;
            }

            if (nodo.getRoot().getRight() != null) {
                countNodesWithOnlyChildRecursivo(nodo.getRoot().getRight());
                valor++;
            }
            if (valor == 1) {
                System.out.println("Este nodo tiene solo un hijo" + nodo.getRoot().getContent());
            }

        }

    }

    public void countNodesWithOnlyChildIterativo(BinaryTree<T> nodo) {
        Stack<BinaryTree<T>> pila = new Stack();
        pila.push(nodo);
        int valor = 0;

        if (this.isEmpty()) {
        } else {
            while (!pila.isEmpty()) {
                BinaryTree<T> prueba = pila.pop();
                if (prueba.getRoot().getLeft() != null) {
                    pila.push(prueba.root.getLeft());
                    valor = 1;

                }
                if (prueba.getRoot().getRight() != null) {
                    pila.push(prueba.root.getRight());
                    valor++;
                }

                if (valor == 1) {
                    System.out.println("Este nodo tiene solo un hijo" + prueba.getRoot().getContent());
                }
                valor = 0;
            }
        }
    }
//////////////// ARBOLES BALANCEADOS//////////////////

    public int Ayuda1Recursiva(BinaryTree<T> nodo) {
        int DerNivel = 0;
        int IzqNivel = 0;
        int DerElem = 0;
        int IzqElem = 0;

        if (nodo.isLeaf()) {

        } else {
            if (nodo.getLeft() != null) {
                IzqNivel = nodo.getLeft().countLevelsRecursive();
                IzqElem = contarRecursivo(nodo.getLeft());
                Ayuda1Recursiva(nodo.getLeft());
            }
            if (nodo.getRight() != null) {
                DerNivel = nodo.getRight().countLevelsRecursive();
                DerElem = contarRecursivo(nodo.getRight());
                Ayuda1Recursiva(nodo.getRight());
            }
        }
        int diferenciaNiveles = Math.abs(IzqNivel - DerNivel);
        int diferenciaElementos = Math.abs(DerElem - IzqElem);
        if (diferenciaNiveles <= 1 && diferenciaElementos <= 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void isHeightBalancedRecursive(BinaryTree<T> nodo) {
        if (Ayuda1Recursiva(nodo) == 1) {
            System.out.println("Arbol valido");
        } else {
            System.out.println("Arbol no valido");
        }
    }

    public int Ayuda1Iterativa(BinaryTree<T> nodo) {
        Stack<BinaryTree<T>> stack = new Stack();
        int DerNivel = 0;
        int IzqNivel = 0;
        int DerElem = 0;
        int IzqElem = 0;
        if (nodo.isEmpty()) {
        } else {
            stack.push(nodo);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (!subtree.isLeaf()) {
                    if (subtree.root.getLeft() != null) {
                        IzqNivel = subtree.getLeft().countLevelsIterativo();
                        IzqElem = contarIterativo(subtree.getLeft());
                        stack.push(subtree.root.getLeft());
                    }
                    if (subtree.root.getRight() != null) {
                        DerNivel = nodo.getRight().countLevelsIterativo();
                        DerElem = contarIterativo(nodo.getRight());
                        stack.push(subtree.root.getRight());
                    }
                    if (subtree.isLeaf()) {
                    }

                } else {

                }
                int diferenciaNiveles = Math.abs(IzqNivel - DerNivel);
                int diferenciaElementos = Math.abs(DerElem - IzqElem);
                if (diferenciaNiveles <= 1 && diferenciaElementos <= 1) {
                    return 1;
                } else {
                    return 20;
                }
            }
        }
        return 0;
    }

    public void isHeightBalancedIterativo(BinaryTree<T> nodo) {
        if (Ayuda1Iterativa(nodo) <= 1) {
            System.out.println("Arbol valido");
        } else {
            System.out.println("Arbol no valido");
        }
    }

    public String CodificarArbol(BinaryTree<HuffmanInfo> arbol, String caracter) {
        String codificacion="";

        if (arbol.getLeft() != null && arbol.getLeft().getRoot().getContent().getText().indexOf(caracter) != -1) {
            codificacion="1"+CodificarArbol(arbol.getLeft(),caracter);
        }

        if (arbol.getRight() != null && arbol.getRight().getRoot().getContent().getText().indexOf(caracter) != -1) {
            codificacion="0"+CodificarArbol(arbol.getRight(),caracter);
        }
        return codificacion;
    }

    public Hashtable<String,HuffmanInfo> CodificarArbolTodos(Map<String,Integer> mapCaracter) {
        Hashtable<String,HuffmanInfo> mapacodificado=new Hashtable<>();
        mapCaracter.forEach((k,v) ->{ 
          
          String codificacion =CodificarArbol((BinaryTree<HuffmanInfo>) this,k);
          mapacodificado.put(k, new HuffmanInfo(k,v,Long.parseLong(codificacion)));
      });
        return mapacodificado;
    }
}
