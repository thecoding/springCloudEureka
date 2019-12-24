package com.config;

/**
 * Created by Mirko on 2019/12/18.
 */
public class ConfigTest {

    private static int i = 0;

    public static void main(String[] args) {
//        int a = 5;
//        int b = 3;
//
//        if (a > 4) {
//            System.out.println("a > 4");
//        } else if (b > 3) {
//            System.out.println("b > 3");
//        } else {
//            System.out.println("other");
//        }
        x(8);
        System.out.println(i);
    }

    public static int x(int n) {
        i = i +1;
        if (n <= 3) {
            return 1;
        }else{
            return x(n-2) + x(n-4) +1;
        }
    }
}
