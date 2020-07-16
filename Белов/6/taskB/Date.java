package taskB;

import java.util.Objects;

public class Date{
	private final int   day;
	private final Month month;
	private final int   year;
	enum Month{
		JANUARY  (31),
		FEBRUARY (28),
		MARCH    (31),
		APRIL    (30),
		MAY      (31),
		JUNE     (30),
		JULY     (31),
		AUGUST   (31),
		SEPTEMBER(30),
		OCTOBER  (31),
		NOVEMBER (30),
		DECEMBER (31);
		private final int maxDaysNumber;
		Month(final int maxDaysNumberInMonth){
			this.maxDaysNumber = maxDaysNumberInMonth;
		}
		public int getMaxDaysNumber(){
			return maxDaysNumber;
		}
	}
	public Date( final int day
			   , final Month month
			   , final int year){
		if(1>day || month.getMaxDaysNumber()<day){
			throw new AssertionError("Day " + day
					+ "is not correct for " + month.name());
		}
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public int   getDay  (){ return day; }
	public Month getMonth(){ return month; }
	public int   getYear (){ return year;	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Date date = (Date) o;
		return Objects.equals(day, date.day) &&
				month == date.month &&
				Objects.equals(year, date.year);
	}
	@Override
	public int hashCode(){ return Objects.hash(day, month, year); }
}