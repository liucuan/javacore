package com.gh.tone.vavr;


import io.vavr.Function2;
import io.vavr.Function3;

/**
 * 函数式编程都是关于值和使用函数转换值的。 <br> Java 8只提供了一个接受一个参数的函数和一个接受两个参数的双函数。 <br> Vavr提供最多8个参数的函数。 <br>
 * 这些功能接口分别称为Function0、Function1、Function2、Function3等。 <br> 如果你需要一个抛出检查异常的函数，你可以使用CheckedFunction1,
 * CheckedFunction2等等。
 */
public class FunctionDemo {

    public static void main(String[] args) {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(1, 2));
        Function3<String, String, String, String> append = Function3.of(
            new Function3<String, String, String, String>() {
                @Override
                public String apply(String s, String s2, String s3) {
                    return s + s2 + s3;
                }
            });
        System.out.println(append.apply("1", "2", "3"));
    }
}
