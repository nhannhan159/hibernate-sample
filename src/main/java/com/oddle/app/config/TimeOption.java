package com.oddle.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeOption {

	public void show() {
		Shift firstShift = new Shift("Monday", "Saturday", "9AM", "2PM");
		Shift secondShift = new Shift("Monday", "Friday", "4PM", "9PM");
		Shift thirdShift = new Shift("Friday", "Friday", "12PM", "5PM");
		Shift forthShift = new Shift("Saturday", "Saturday", "2PM", "7PM");

		firstShift.print();
		secondShift.print();
		thirdShift.print();
		forthShift.print();

		OpeningHours openingHours = new OpeningHours();
		openingHours.feedShift(firstShift);
		openingHours.feedShift(secondShift);
		openingHours.feedShift(thirdShift);
		openingHours.feedShift(forthShift);
		openingHours.print();
	}

	public class OpeningHours {
		List<HourPerDay> weekdays;
		Map<String, Integer> weekdaysMapping;


		public OpeningHours() {
			//Init opening hours
			this.weekdays = new ArrayList<>();
			this.weekdays.add(new HourPerDay("Monday"));
			this.weekdays.add(new HourPerDay("Tuesday"));
			this.weekdays.add(new HourPerDay("Wednesday"));
			this.weekdays.add(new HourPerDay("Thursday"));
			this.weekdays.add(new HourPerDay("Friday"));
			this.weekdays.add(new HourPerDay("Saturday"));
			this.weekdays.add(new HourPerDay("Sunday"));

			//Init weekdays mapping
			this.weekdaysMapping = new HashMap<>();
			this.weekdaysMapping.put("Monday", 0);
			this.weekdaysMapping.put("Tuesday", 1);
			this.weekdaysMapping.put("Wednesday", 2);
			this.weekdaysMapping.put("Thursday", 3);
			this.weekdaysMapping.put("Friday", 4);
			this.weekdaysMapping.put("Saturday", 5);
			this.weekdaysMapping.put("Sunday", 6);
		}

		public void feedShift(Shift shift) {
			for (int i = this.weekdaysMapping.get(shift.startDay); i <= this.weekdaysMapping.get(shift.endDay); i++) {
				this.weekdays.get(i).feedShift(shift);
			}
		}

		public void print() {
			String sameDay = this.weekdays.get(0).getWeekday();
			for (int i = 1; i <= 7; i++) {
				if (i == 7 || !this.weekdays.get(i).compare(this.weekdays.get(i - 1))) {
					this.weekdays.get(i - 1).print(sameDay);
					if (i < 7) {
						sameDay = this.weekdays.get(i).getWeekday();
					}
				}
			}
		}
	}

	public class HourPerDay {
		private String weekday;
		private List<Boolean> hours;

		public HourPerDay(String weekday) {
			this.weekday = weekday;
			this.hours = new ArrayList<>();
			for (int i = 0; i < 24; i++) {
				this.hours.add(Boolean.FALSE);
			}
		}

		public boolean compare(HourPerDay anotherDay) {
			boolean result = true;
			for (int i = 0; i < 24 && result; i++) {
				if (!this.hours.get(i).equals(anotherDay.getHours().get(i))) {
					result = false;
				}
			}
			return result;
		}

		public void print(String startDay) {
			if (startDay.equals(this.weekday)) {
				System.out.println(this.weekday);
			} else {
				System.out.println(startDay + " - " + this.weekday);
			}
			this.printReadableHours();
		}

		public void printReadableHours() {
			boolean isClosed = true;
			String startHourReadable, endHourReadable;
			int startHour = -1;
			for (int i = 0; i < 24; i++) {
				if (this.hours.get(i)) {
					isClosed = false;
					if (i == 0 || !this.hours.get(i - 1)) {
						startHour = i;
					}
					if (i == 23 || !this.hours.get(i + 1)) {
						startHourReadable = HourUtils.getReadableHourTime(startHour);
						endHourReadable = HourUtils.getReadableHourTime(i);
						System.out.print(startHourReadable + " to " + endHourReadable + " ");
					}
				}
			}
			if (isClosed) {
				System.out.print("Closed");
			}
			System.out.println();
		}

		public void feedShift(Shift shift) {
			int startHour = HourUtils.getHourTimeFromReadable(shift.startHour);
			int endHour = HourUtils.getHourTimeFromReadable(shift.endHour);
			for (int i = startHour; i <= endHour; i++) {
				this.hours.set(i, Boolean.TRUE);
			}
		}

		public String getWeekday() {
			return this.weekday;
		}

		public List<Boolean> getHours() {
			return this.hours;
		}
	}

	public static class HourUtils {

		public static int getHourTimeFromReadable(String middayTypeHour) {
			String suffix = middayTypeHour.substring(middayTypeHour.length() - 2);
			String hourString = middayTypeHour.substring(0, middayTypeHour.length() - 2);
			int hourTime = Integer.parseInt(hourString);
			if ("PM".equals(suffix)) {
				hourTime += 12;
			}
			if ("PM".equals(suffix) && "12".equals(hourString)) {
				hourTime = 12;
			} else {
				hourTime = hourTime % 24;
			}
			return hourTime;
		}

		public static String getReadableHourTime(int hourTime) {
			String suffix = hourTime < 12 ? "AM" : "PM";
			Integer middayTypeHour = hourTime % 12;
			if (hourTime == 0 || hourTime == 12) {
				middayTypeHour = 12;
			}
			return middayTypeHour.toString() + suffix;
		}
	}

	//NOTE: This is a convenient method to run non-static methods
	public static void main(String[] args) {
		TimeOption timeOption = new TimeOption();
		timeOption.show();
	}

	//NOTE: Do not modify this class
	public class Shift {
		String startDay;
		String endDay;
		String startHour;
		String endHour;

		public Shift(String startDay, String endDay, String startHour, String endHour) {
			this.startDay = startDay;
			this.endDay = endDay;
			this.startHour = startHour;
			this.endHour = endHour;
		}

		public void print() {
			if (this.startDay.equals(this.endDay)) {
				System.out.println(this.startDay);
			} else {
				System.out.println(this.startDay + " to " + this.endDay);
			}

			System.out.println(this.startHour + " to " + this.endHour);
		}
	}
}
