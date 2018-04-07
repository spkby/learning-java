import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.time.*;

public class App1 {

    public static void main(String[] args) {


        Date date = new Date();

        System.out.println(date);
        Date date1 = new Date(118, 0, 1);
        System.out.println(date1);
        System.out.println(date.after(date1));
        System.out.println(date.getTime());


        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar);

        System.out.println(calendar.get(Calendar.MONTH));

        calendar.add(Calendar.MONTH, 2);

        System.out.println(calendar.getTime());


        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.DECEMBER, 31, 23, 59, 59);


        Calendar c2 = Calendar.getInstance();
        c1.set(2017, Calendar.JANUARY, 1, 0, 0, 1);


        long i1 = c1.getTimeInMillis();
        long i2 = c2.getTimeInMillis();

        long i = i2 - i1;

        System.out.println(i);


        TimeZone timeZone = TimeZone.getTimeZone("Europe/Berlin");
        System.out.println(timeZone.getID());
        System.out.println(timeZone.getRawOffset() / (3600 * 1000.0));

        calendar = Calendar.getInstance(timeZone, Locale.GERMANY);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        System.out.println(formatter.format(new Date()));

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy MMMM ", Locale.CHINESE);
        System.out.println(formatter1.format(new Date()));


        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);




        LocalDateTime d1 = LocalDateTime.of(2015,12,31,23,59,59);
        LocalDateTime d2 = LocalDateTime.of(2016,01,01,0,0,00);

        System.out.println(ChronoUnit.SECONDS.between(d2,d1));

    }



}
