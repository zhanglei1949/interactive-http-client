package com.alibaba.graphscope.interactive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class ClientTest {
    private static Client client;

    @BeforeClass
    public static void beforeClass() {
        client = new Client("localhost:10000");
    }

    @Test
    public void testQuery0(){
        client.SubmitCtrlQuery(520201007000000062L, 5, 0.5, 10);
    }

    @Test 
    public void testQuery1(){
        client.SubmitCtrlQuery(1, 1, 1, 1);
    }
}
