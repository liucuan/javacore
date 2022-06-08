package com.gh.tone.core;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static final String[] ops = {"+", "-", "*", "/"};
    public static final String[] actions = {"redo", "undo"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入:");
        Stack<BigDecimal> stack = new Stack<>();
        while (true) {
            BigDecimal n1, n2;
            String r;
            String input = sc.next();
            if (!isOps(input)) {
                stack.push(new BigDecimal(input));
            } else {
                n1 = stack.pop();
                if (stack.isEmpty()) {
                    n2 = n1;
                } else {
                    n2 = stack.pop();
                }
                r = cal(n1, n2, input);
                System.out.println(r);
                System.out.println(stack);
            }
        }
    }

    private static boolean isOps(String input) {
        for (String op : ops) {
            if (op.endsWith(input)) {
                return true;
            }
        }
        return false;
    }

    private static String cal(BigDecimal n1, BigDecimal n2, String op) {
        switch (op) {
            case "+":
                return n1.add(n2).toString();
            case "-":
                return n1.subtract(n2).toString();
            case "*":
                return n1.multiply(n2).toString();
            case "/":
                return BigDecimal.ZERO.equals(n2) ? "error" : n1.divide(n2).toString();
        }
        return "error";
    }

}
