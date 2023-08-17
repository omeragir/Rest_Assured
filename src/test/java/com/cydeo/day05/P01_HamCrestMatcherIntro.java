package com.cydeo.day05;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P01_HamCrestMatcherIntro {

    @Test
    public void numbers() {
        //Junit 5 assert equals method
        assertEquals(9, (6 + 3));

        //HamcrestMatchers comes from RestAssured dependency
        //2 static import to get rid class names,directly call assertThat and Matchers
        //import static org.hamcrest.MatcherAssert.*;
        //import static org.hamcrest.Matchers.*;


        //Hamcrest Matchers
        // has 2 overloaded methods
        //-First one will take value to check
        //-Second one will take another Matchers to make it readable / to add new assert functionality

        assertThat(6 + 3, is(9));
        assertThat(6 + 3, is(equalTo(9)));
        assertThat(6 + 3, equalTo(9));

        /**
         * is (someValue)
         * is(equalTo(someValue))
         * equalTo(someValue)
         * ALL of them same is tern of assertion
         */

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));
        /**
         * They are all the same for assertion
         */

        assertThat(5 + 6, is(greaterThanOrEqualTo(11)));
        assertThat(5 + 6, greaterThanOrEqualTo(11));
        assertThat(5 + 6, lessThanOrEqualTo(12));


    }

    @Test
    public void testString() {

        String msg = "Api is fun";
        assertThat(msg, is("Api is fun"));
        assertThat(msg, equalTo("Api is fun"));
        assertThat(msg, equalToIgnoringCase("api Is fun"));

        assertThat(msg,startsWith("Api"));
        assertThat(msg,startsWithIgnoringCase("API"));
        assertThat(msg,endsWithIgnoringCase("fun"));

        assertThat(msg,containsString("is"));
        assertThat(msg,containsStringIgnoringCase("IS"));

        assertThat(msg,not("FUN!"));
        assertThat(msg,is(not("FUN!")));

    }

    @Test
    public void testCollections(){
        List<Integer>numberList= Arrays.asList(3,5,1,77,44,76);//6 elements

        //how to check size of elements
        assertThat(numberList,hasSize(6));

        //how to check 77 is into the collections
        assertThat(numberList,hasItem(77));

        //how to check 44 and 76 is into the collections
        assertThat(numberList,hasItems(44,76));

        //loop through each of the element and make sure they are matching with Matcher inside the everyItem
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));
        //check if you have values and their position is correct
        assertThat(numberList,containsInRelativeOrder(3,5,1,77));
        //check if you have all the vale,position might be different
        assertThat(numberList,containsInAnyOrder(3,5,1,77,44,76));


    }

}