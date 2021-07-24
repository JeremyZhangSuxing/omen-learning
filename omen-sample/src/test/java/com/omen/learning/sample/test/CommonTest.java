package com.omen.learning.sample.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Knight
 * @date : 2021/7/14 4:52 下午
 */
@ExtendWith(MockitoExtension.class)
class CommonTest {

    @Mock
    HashMap<String, Integer> hashMap;

    @Captor
    ArgumentCaptor<String> keyCaptor;

    @Captor
    ArgumentCaptor<Integer> valueCaptor;

    @Test
    public void saveTest()
    {
        hashMap.put("A", 10);
        hashMap.put("b",20);
        Mockito.verify(hashMap,new Times(2)).put(keyCaptor.capture(), valueCaptor.capture());
        assertEquals("b", keyCaptor.getValue());
        assertEquals(new Integer(20), valueCaptor.getValue());
    }
}
