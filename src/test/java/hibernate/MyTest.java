package hibernate;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

public class MyTest {

	public static void main(String[] args) {
		MyTest mt = new MyTest();
//		mt.printAge();
		mt.testNum();
	}
	
	void testNum(){
		String sid =1 +"-"+String.format("%02d",3)+"-"+String.format("%03d",4) +"-"
		+String.format("%05d",5);
		System.out.println(""+sid);
		String id = String.format("%04d", 5);
		String id2 = String.format("%04d", 500);
		System.out.println(id+"  = "+id2);
	}
	
	void printAge(){
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(d);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
		Date e = new Date();
		LocalDate today = LocalDate.now();
		System.out.println(year +" month " + month + "  day "+ day);
		LocalDate birthdate = new LocalDate(2017, 01, 21);
		LocalDate now = LocalDate.now();// test, in real world without args
		Years age = Years.yearsBetween(birthdate, now);
		Months mn =Months.monthsBetween(birthdate, now);
		System.out.println(""+ age.getYears()  + " " + mn.getMonths() );
	}
}
