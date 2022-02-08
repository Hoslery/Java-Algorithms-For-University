/*
    Лекция №2. Динамическое программирование
    Задача №1. Подматрица из единиц
    Формулировка задачи: Вводится матрица a(m,n) из нулей и единиц.
    Найти в ней прямоугольную подматрицу из одних единиц максимального
    размера (т.е. с максимальным произведением высоты на длину).
 */

/*
    Программа выполнена на языке Java
    Сложность алгоритма O(m*n), так как требуется только один обход матрицы
 */

package com.toropov.lecture2;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Toropov Egor IVT-31
 */
public class SubmatrixOfUnits {

    public static void main(String[] args) {

        int m;//кол-во строк матрицы
        int n;//кол-во столбцов матрицы


        //Ввод первоначальных параметров с клавиатуры
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("\nВведите кол-во строк в матрице: m = ");

            try {
                m = Integer.parseInt(scanner.next());
                //проверка на то, что пользователь ввел число > 0
                if(m>0)
                    break;
                else
                    System.out.println("\nКол-во строк должно быть > 0!");
            }

            //выбрасывание исключение, если на вход пользователь подал не число
            catch(NumberFormatException e){
                System.out.println("\nНеверный ввод, введите число!");
            }
        }
        while (true){
            System.out.print("\nВведите кол-во столбцов в матрице: n = ");

            try {
                n = Integer.parseInt(scanner.next());
                //проверка на то, что пользователь ввел число > 0
                if(n>0)
                    break;
                else
                    System.out.println("\nКол-во столбцов должно быть > 0!");
            }

            //выбрасывание исключение, если на вход пользователь подал не число
            catch(NumberFormatException e){
                System.out.println("\nНеверный ввод, введите число!");
            }
        }

        int[][] a= new int[m][n]; //матрица размера m*n

        //Заполнение матрицы случайным образом 0 или 1
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                a[i][j] = (int)(Math.round(Math.random()));
            }
        }

        //Вывод матрицы на экран
        System.out.println("\nМатрица: ");
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }


        //Вывод работы программы
        System.out.print("\nПлощадь максимального прямоугольника, состоящего из единиц равна "
                + maxRectangle(m, n, a) +"\n");

    }


    //метод служит для вычисления площади
    static int maxHist(int m, int n, int row[])
    {
        // Создаем пустой стек. Стек содержит индексы массива row[]
        // Значения, хранящиеся в стеке всегда идут в порядке
        // возрастания.
        Stack<Integer> result = new Stack<Integer>();

        int top_val; // Вершина стека

        int max_area = 0; // инициализируем максимальную площадь в текущей
        // строке (или гистограмме)

        int area = 0; // инициализируем площадь c текущей вершиной

        // Пробегаем по всем столбцам данной гистограммы (или строки)
        int i = 0;
        while (i < n) {
            // Если значение в данном столбце (row[i]) >=, чем значение от вершины стека (row[result.peek()])
            // ,то добавляем индекс данного столбца в стек
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);

            else {
                //Если значение в данном столбце (row[i]) меньше row[result.peek()],
                //то вычисляем площадь прямоугольника с вершиной стека.
                //"i" - это "правый индекс" для вершины.
                //Элемент перед вершиной стека это "левый индекс"
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;

                if (!result.empty())
                    area = top_val * (i - result.peek() - 1);

                max_area = Math.max(area, max_area);
            }
        }


        // извлекаем оставшиеся значения из стека и
        // рассчитываем площадь с каждым из них
        while (!result.empty()) {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * i;
            if (!result.empty())
                area = top_val * (i - result.peek() - 1);

            max_area = Math.max(area, max_area);
        }
        return max_area;
    }

    // Возвращает площадь максимального прямоугольника(состоящего из единиц) в матрице a[][]
    static int maxRectangle(int m, int n, int a[][])
    {
        // Вычисляет площадь для первой строки и инициализируйте ее как result
        int result = maxHist(m, n, a[0]);

        // Цикл по строкам, чтобы найти максимальную прямоугольную область, рассматривая каждую строку как гистограмму
        for (int i = 1; i < m; i++) {

            for (int j = 0; j < n; j++)

                // Если a[i][j] == 1 тогда добавляем a[i -1][j]  (т.е добавляем
                // элемент матрицы иэ предыдущей строки и того же столбца).
                // Перезаписываем значение a[i][j]
                if (a[i][j] == 1)
                    a[i][j] += a[i - 1][j];

            // Обновить result если площадь с текущей строкой больше
            result = Math.max(result, maxHist(m, n, a[i]));
        }

        return result;
    }
}
