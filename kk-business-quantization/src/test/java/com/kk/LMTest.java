package com.kk;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LMTest {

    @Test
    public void helloWorld()
    {
        System.out.println(sum(555,7));
        System.out.println(jianfa(8,7));

    }

    public int sum(int a,int b)
    {

        return a+b;
    }
    public  int jianfa(int a,int b)
    {
        return a-b;
    }
}
