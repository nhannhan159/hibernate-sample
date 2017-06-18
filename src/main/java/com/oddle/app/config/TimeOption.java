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

		System.out.println("\nReal opening hours:");
		OpeningHours openingHours = new OpeningHours();
		openingHours.feedShift(firstShift);
		openingHours.feedShift(secondShift);
		openingHours.feedShift(thirdShift);
		openingHours.feedShift(forthShift);
		openingHours.print();
	}

	/**
	 * This class used to collect shift hours
	 * and print opening hours in pretty format
	 *
	 * @author  Tien Tan
	 * @since   2017-06-18
	 */
	public class OpeningHours {

		/** List of weekdays used as data structure */
		List<HourPerDay> weekdays;

		/** Mapping for finding index of day quickly */
		Map<String, Integer> weekdaysMapping;

		/** Init all weekdays configuration */
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

		/**
		 * Feeding new shift hours to current opening hours
		 * @param shift a set of shift hours
		 */
		public void feedShift(Shift shift) {
			for (int i = this.weekdaysMapping.get(shift.startDay);
				 i <= this.weekdaysMapping.get(shift.endDay);
				 i++) {
				this.weekdays.get(i).feedShift(shift);
			}
		}

		/** Print the opening hours in pretty format */
		public void print() {
			//Mark all same time structure of days and print them
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

	/**
	 * This class used to collect shift hours
	 * and print opening hours in one day
	 *
	 * @author  Tien Tan
	 * @since   2017-06-18
	 */
	public class HourPerDay {

		/** Name of this weekday */
		private String weekday;

		/** Store opening hours for this day */
		private List<Boolean> hours;

		/**
		 * Initial data structure for storing opening hours
		 * @param weekday Name of this weekday
		 */
		public HourPerDay(String weekday) {
			this.weekday = weekday;
			this.hours = new ArrayList<>();
			for (int i = 0; i < 24; i++) {
				this.hours.add(Boolean.FALSE);
			}
		}

		/**
		 * Compare opening hours structure with another day
		 * @param  anotherDay Another day for comparing
		 * @return Return true if have same hours structure
		 */
		public boolean compare(HourPerDay anotherDay) {
			boolean result = true;
			for (int i = 0; i < 24 && result; i++) {
				if (!this.hours.get(i).equals(anotherDay.getHours().get(i))) {
					result = false;
				}
			}
			return result;
		}

		/** Print the opening hours of this day in pretty format */
		public void print(String startDay) {
			if (startDay.equals(this.weekday)) {
				System.out.println(this.weekday);
			} else {
				System.out.println(startDay + " - " + this.weekday);
			}
			this.printReadableHours();
		}

		/** Print the opening hours by convert stored type to readable string */
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

		/**
		 * Feeding new shift hours to current day
		 * @param shift a set of shift hours
		 */
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

	/**
	 * Utility class for convert 12 hours time to 24 hours time
	 * and vice versa
	 *
	 * @author  Tien Tan
	 * @since   2017-06-18
	 */
	public static class HourUtils {

		/**
		 * Convert 12 hours type to 24 hours type
		 * EX: 2PM -> 14
		 * @param middayTypeHour 12 hours type string
		 */
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

		/**
		 * Convert 24 hours type to 12 hours type
		 * EX: 14 -> 2PM
		 * @param hourTime 24 hours type
		 */
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
