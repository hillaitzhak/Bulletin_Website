package utils;

import java.time.*;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class DateChecker {
	
	public static String Check(int year, int day, int month )
	{
		LocalDate receivedDate = LocalDate.of(year, month, day);
		LocalDate now = LocalDate.now();
		String result = "good";
		Period diff = Period.between(receivedDate, now);
		 
		if (diff.getMonths() >= 3)
		{
			result = "need to change";
		}
		
		return result;		
	}
	
	
//	/*public static String Check(int year, int day, int month )
//	{
//		LocalDate localDate = LocalDate.now();
//		int Today = localDate.getDayOfMonth();
//		int Thismonth = localDate.getMonthValue();
//		int Thisyear = localDate.getYear();
//		
//		int dayCompare = day;
//		int monthCompare = month;
//		int yearCompare = year;
//
//		if (day == 29 || day == 30 || day == 31)
//		{
//			if (month == 11)
//			{
//				dayCompare = 1;
//				monthCompare = 3;
//				yearCompare = year++;
//			}
//		}
//		else
//			if (day == 31)
//			{
//				if (month == 1 || month == 3 || month == 6 || month == 8)
//				{
//					dayCompare = 1;
//					monthCompare = month + 4;
//				}
//			}
//		else
//			if (month == 10 || month == 11 || month == 12)
//			{
//				monthCompare = 3 - (12 - month);
//				yearCompare = year++;
//			}
//		else
//		{
//			monthCompare = month + 3;
//		}
//		
//		if(Thisyear > yearCompare)
//		{
//			return "need to change1";
//		}
//		
//		if (Thismonth > monthCompare)
//		{
//			return "need to change2";
//		}
//		
//		if (Today > dayCompare)
//		{
//			return "need to change";
//		}
//		return "good";
//	}

}
