package lib;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class utils {

	public static int monthAsNumber(
			  String  month,
			  Locale  locale,
			  boolean abbreviated,
			  boolean caseInsensitive
			) {
			  DateFormatSymbols dfs = new DateFormatSymbols(locale);
			  String[] months = (abbreviated ? dfs.getShortMonths() : dfs.getMonths());

			  if (caseInsensitive) {
			    for (int i = 0; i < 12; i++) {
			      if (months[i].equalsIgnoreCase(month)) {
			        return i; // month index is zero-based as usual in old JDK pre 8!
			      }
			    }
			  } else {
			    for (int i = 0; i < 12; i++) {
			      if (months[i].equals(month)) {
			        return i; // month index is zero-based as usual in old JDK pre 8!
			      }
			    }
			  }
			  return -1; // no match
			}
	
	public static String getMonthName(final int index, final Locale locale, final boolean shortName)
	{
	    String format = "%tB";

	    if (shortName)
	        format = "%tb";

	    Calendar calendar = Calendar.getInstance(locale);
	    calendar.set(Calendar.MONTH, index);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);

	    return String.format(locale, format, calendar);
	}
	
}
