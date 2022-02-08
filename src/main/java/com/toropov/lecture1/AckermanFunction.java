/*
    Лекция №1. Рекурсия
    Задачи для самостоятельного решения:
    Задача №6. Функция Аккермана
    Формулировка задачи: Напишите программу, вычисляющую функцию
    Аккермана от двух неотрицательных целых аргументов,
    имеющую следующее рекурсивное определение:
    a(0,n)=n+1;
    a(m,0)=a(m-1,1), при m>0;
    a(m,n)=a(m-1,a(m,n-1)), при m>0,n>0.
    В программе предусмотрите подсчет количества
    обращений к функции a.
 */

/*
    Программа выполнена на языке Java
    Сложность для А(1,n) O(n)
    Сложность для A(2,n) O(n^2)
    Сложность для A(3,n) O(4^n)
    Для A(4,4) ~= 2^(2^(10^19729))
 */

package com.toropov.lecture1;


import java.util.Scanner;

/**
 * @author Toropov Egor IVT-31
 */
public class AckermanFunction {

    static int thenNumberOfTimesWasCalled=0; // переменная, отвечающая за кол-во обращений к функции А

    public static void main(String[] args) {

        int m;//первое неотрицательное число
        int n;//второе неотрицательное число


        //Ввод первоначальных параметров с клавиатуры
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("\nВведите первое неотрицательное число: m = ");

            try {
                m = Integer.parseInt(scanner.next());
                //проверка на то, что пользователь ввел число >= 0
                if(m>=0)
                    break;
                else
                    System.out.println("\nЧисло должно быть >= 0!");
            }

            //выбрасывание исключение, если на вход пользователь подал не число
            catch(NumberFormatException e){
                System.out.println("\nНеверный ввод, введите число!");
            }
        }
        while (true){
            System.out.print("\nВведите второе неотрицательное число: n = ");

            try {
                n = Integer.parseInt(scanner.next());
                //проверка на то, что пользователь ввел число >= 0
                if(n>=0)
                    break;
                else
                    System.out.println("\nЧисло должно быть >= 0!");
            }

            //выбрасывание исключение, если на вход пользователь подал не число
            catch(NumberFormatException e){
                System.out.println("\nНеверный ввод, введите число!");
            }
        }


        //Вывод работы программы
        System.out.print("\nРезультат функции Аккермана  = "
                + A(m, n) +"\n");

        System.out.print("\nКол-во обращений к функции А  = "
                + thenNumberOfTimesWasCalled +"\n");
    }


    //функция Аккермана
    static int A(int m, int n)
    {
        thenNumberOfTimesWasCalled++;
        if (m==0)
            return n+1;
        if(m>0 && n==0)
            return A(m-1,1);
        return A(m-1,A(m,n-1));
    }


}
