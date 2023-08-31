package com.cydeo.day11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_Junit5Assertions {

    /**
     *
     * HARD ASSERT -->
     * - Test Execution will be aborted if the assert condition is not met
     * - Rest of the execution will stop
     * - Use Case --> if we are checking critical functionally of the app we can check with hard assert
     *
     */

    @Test
    public void hardAssert(){

        assertEquals(10,5+5);
        System.out.println("---First Assert is DONE");
        assertEquals(10,5+3);
        System.out.println("---Second Assert is DONE");
        assertEquals(10,5+5);
        System.out.println("---Third Assert is DONE");

    }

    /**
     * SOFT ASSERT --> VERIFY (Soft Assertion is implementation of VERIFY
     * -Test execution will continue till end of the code fragment even if one the assertion is failing
     *
     *
     */
    @DisplayName("JUNIT 5 Soft Assertion is implemented")
    @Test
    public void softAssert(){

        assertAll("Learning Soft Assert",
                ()->assertEquals(10,5+5),
                ()->assertEquals(10,5+3),
                ()->assertEquals(10,5+4));

    }
}