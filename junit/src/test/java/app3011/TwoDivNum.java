package app3011;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class TwoDivNum extends TypeSafeMatcher<Integer>{


    @Override
    protected boolean matchesSafely(Integer integer) {
        return integer % 2 == 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Число является четном");
    }

    @Override
    protected void describeMismatchSafely(Integer item, Description mismatchDescription){
        mismatchDescription.appendText("Не является четным числом");
    }



    public static TwoDivNum DivideBy2(){
        return new TwoDivNum();
    }




}
