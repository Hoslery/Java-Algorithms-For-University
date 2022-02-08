/*
    Лекция №5. Деревья
    Упражнение 3.8
    Формулировка задачи: Реализовать функцию поиска данного элемента X в дереве так,
    чтобы в случае его отсутствия в дереве он добавлялся к нему.
 */

/*
    Программа выполнена на языке Java
 */


package com.toropov.lecture5;

import java.util.Scanner;
import java.util.Stack;

import static com.toropov.lecture5.Tree.Node.printTree;
import static com.toropov.lecture5.Tree.Node.searchOrInsertNode;

public class Tree {
    public static void main(String[]args){

        //Заполнение дерева
        //Переменная root отвечает за корень дерева
        //Мы можем не заполнять дерево таким образом, а использовать метод searchOrInsertNode, который
        //описан ниже и удовлетворяет условию задачи
        Node root = new Node(20,new Node(7,
                new Node(4,null,new Node(6,null,null)),
                new Node(9,null,null)),
                new Node(35,new Node(31,new Node(28,null,null),null),
                        new Node(40,new Node(38,null,null), new Node(52,null,null))));

        int X;//переменная, отвечающая за значение узла дерева

        //Ввод первоначальных параметров с клавиатуры
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("\nВведите значение узла: X = ");

            try {
                X = Integer.parseInt(scanner.next());
                break;
            }

            //выбрасывание исключение, если на вход пользователь подал не число
            catch(NumberFormatException e){
                System.out.println("\nНеверный ввод, введите число!");
            }
        }

        Node searchNode = searchOrInsertNode(root,X);//вызов метода, удовлетворяющего условию задачи

        //Вывод дерева
        printTree(root);

    }

    //класс, отвечающий за узел дерева
    public static class Node {
        int value;//значение данного узла
        Node left;//левый потомок
        Node right;//правый потомок


        //конструктор
        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        //конструктор
        public Node(int value) {
            this.value = value;
        }



        //метод поиска узла со значением введенным пользователем,
        //в случае отсутствия узла с данным значением - добавление его к дереву
        public static Node searchOrInsertNode(Node root,int value) {

            Node newNode = new Node(value); // создание нового узла

            // если корневой узел не существует
            if (root == null) {
                root = newNode;// то новый элемент и есть корневой узел
                return newNode;
            }
            else { // корневой узел занят
                Node currentNode = root; // начинаем с корневого узла
                Node parentNode;
                while (true)
                {
                    parentNode = currentNode;

                    // если такой элемент в дереве уже есть, не сохраняем его
                    if(value == currentNode.value) {
                        System.out.println("Узел со значением " + currentNode.value + " был успешно найден!");
                        return currentNode;
                    }
                    else  if (value < currentNode.value) {   // движение влево
                        currentNode = currentNode.left;

                        if (currentNode == null){ // если был достигнут конец цепочки,
                            parentNode.left = newNode; //  то вставить новый узел слева от родителя
                            System.out.println("Узел со значением " + newNode.value + " не был успешно найден,\n" +
                                    "поэтому мы добавили его к дереву");
                            return newNode;
                        }
                    }
                    else { // движение вправо
                        currentNode = currentNode.right;
                        if (currentNode == null) { // если был достигнут конец цепочки,
                            parentNode.right = newNode;  //то вставить новый узел справа от родителя
                            System.out.println("Узел со значением " + newNode.value + " не был успешно найден,\n" +
                                    "поэтому мы добавили его к дереву");
                            return newNode;
                        }
                    }
                }
            }
        }

        public static void printTree(Node root) { // метод для вывода дерева в консоль
            Stack globalStack = new Stack(); // общий стек для значений дерева
            globalStack.push(root);
            int gaps = 32; // начальное значение расстояния между элементами
            boolean isRowEmpty = false;
            String separator = "-----------------------------------------------------------------";
            System.out.println(separator);// черта для указания начала нового дерева
            while (!isRowEmpty) {
                Stack localStack = new Stack(); // локальный стек для задания потомков элемента
                isRowEmpty = true;

                for (int j = 0; j < gaps; j++)
                    System.out.print(' ');
                while (!globalStack.isEmpty()) { // пока в общем стеке есть элементы
                    Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                    if (temp != null) {
                        System.out.print(temp.value); // выводим его значение в консоли
                        localStack.push(temp.left); // сохраняем в локальный стек, наследники текущего элемента
                        localStack.push(temp.right);
                        if (temp.left != null ||
                                temp.right != null)
                            isRowEmpty = false;
                    }
                    else {
                        System.out.print("__");// - если элемент пустой
                        localStack.push(null);
                        localStack.push(null);
                    }
                    for (int j = 0; j < gaps * 2 - 2; j++)
                        System.out.print(' ');
                }
                System.out.println();
                gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
                while (!localStack.isEmpty())
                    globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
            }
            System.out.println(separator);// подводим черту
        }


    }
}
