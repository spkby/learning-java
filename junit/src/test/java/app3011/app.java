package app3011;


import static app3011.TwoDivNum.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class app {


    @Test
    public void testAnything(){

        String result = "123";

        assertThat(result, is(anything()));
    }


    @Test
    public void testObjMatchers(){

        Object o = new Object();

        assertThat(o, is(nullValue()));

        assertThat(o, instanceOf(String.class));

        assertThat(o, equalTo(o));

    }


    @Test
    public void testCollectionMatchers(){

        Map<String,String> names = new HashMap<>();
        names.put("петр","петров");
        names.put("иван","иванов");


        assertThat(names, hasEntry("иван","иванов"));

        assertThat(names, hasKey("иван"));

        assertThat(names, hasValue("иванов"));


        ArrayList<String> deps = new ArrayList<>();

        deps.add("IT");
        deps.add("Adv");
        deps.add("Cleaning");


        assertThat(deps,hasItem("IT"));

        //assertThat(deps,hasItems("IT","Energy"));
        assertThat(deps,hasItems("IT","Adv"));

        Integer[] a = new Integer[] {1,2,3};

        assertThat(a,arrayContaining(2,1));
    }


    @Test
    public void testNumberMatchers(){

        double d = 1.08;

        assertThat(d, closeTo(1.05,0.1));

        assertThat(d,greaterThan(1.08));

        assertThat(d,lessThanOrEqualTo(1.08));

        String s = "str";


        assertThat(s, equalToIgnoringCase("sTr"));

        assertThat(s, equalToIgnoringWhiteSpace(" s t r"));
    }


    @Test
    public void testCombineMatchers(){

        String s = "Hello";
        String s2 = "Hello o";

        assertThat(s,allOf(notNullValue(), equalToIgnoringWhiteSpace(s2)));

        assertThat(s,anyOf(notNullValue(), equalToIgnoringWhiteSpace(s2)));

        assertThat(s,not(equalToIgnoringWhiteSpace(s2)));


        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(1.5);
        doubles.add(1.3);
        doubles.add(1.7);

        assertThat(doubles, hasItems(greaterThan(1.6)));
        assertThat(doubles, hasItems(not(greaterThan(1.6))));
        assertThat(doubles, not(hasItems(greaterThan(1.6))));


    }

    @Test
    public void TDMatchers(){

        int i =1101;

        assertThat(i,is(DivideBy2()));

        Integer[] ints = new Integer[]{123,4,453,23};

        assertThat(ints, arrayContainingInAnyOrder(ints));
    }


}
