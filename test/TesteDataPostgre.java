package test;

import util.DateUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by marcus.rodrigues on 20/05/2015.
 */
public class TesteDataPostgre {

    public static void main(String[] args) {

        String data1 = "12/05/2015";


        Date data = new Date();

            data = DateUtil.stringToDatePostgre(data1);


        System.out.println("String to date Postgre: "+ data);
        //System.out.println("String to date: "+ dataDate);

    }
}
