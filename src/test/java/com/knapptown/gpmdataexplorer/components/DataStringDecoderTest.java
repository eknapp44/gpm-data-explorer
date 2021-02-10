package com.knapptown.gpmdataexplorer.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataStringDecoderTest {

    private DataStringDecoder dataStringDecoder;

    @BeforeEach
    public void setup() {
        dataStringDecoder = new DataStringDecoder();
    }

    @Test
    public void testDataStringDecode() {
        final String encoded = "Noel Gallagher&#39;s High Flying Birds (Deluxe Edition)";
        final String expected = "Noel Gallagher's High Flying Birds (Deluxe Edition)";

        String decoded1 = dataStringDecoder.decodeDataString(encoded);
        assertEquals(decoded1, expected);
    }

    @Test
    public void testDataStringDecode2() {
        final String encoded = "90&#39;s/00&#39;s Alt Rock";
        final String expected = "90's/00's Alt Rock";

        String decoded1 = dataStringDecoder.decodeDataString(encoded);
        assertEquals(decoded1, expected);
    }

}
