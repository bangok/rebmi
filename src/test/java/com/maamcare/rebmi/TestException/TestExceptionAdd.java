package com.maamcare.rebmi.TestException;


import org.junit.Test;


public class TestExceptionAdd {

    @Test
    public void noExceptionSuccess(){
        long start = System.nanoTime();
        int a = 0;
        int b = 0;
        for (int i = 0; i < 1000000; i++) {
            a++;
            b++;
        }
        System.out.println(System.nanoTime() - start);
    }
    @Test
    public void noExceptionSuccessD(){
        long start = System.nanoTime();
        int a = 0;
        int b = 0;
        try {
            for (int i = 0; i < 1000000; i++) {
                a++;
                b++;
            }
        }catch (Exception e) {
        }

        System.out.println(System.nanoTime() - start);
    }
    @Test
    public void haveExceptionSuccess(){
        long start = System.nanoTime();
        int a = 0;
        int b = 0;
        for (int i = 0; i < 1000000; i++) {
            a++;
            b++;
            throw new RuntimeException();

        }
        System.out.println(System.nanoTime() - start);
    }

    @Test
    public void haveExceptionFail(){
        long start = System.nanoTime();
        int a = 0;
        int b = 0;
        for (int i = 0; i < 1000000; i++) {
            try {
                a++;
                throw new RuntimeException();
            } catch (RuntimeException e) {
            }
        }
        System.out.println(System.nanoTime() - start);
    }
}
