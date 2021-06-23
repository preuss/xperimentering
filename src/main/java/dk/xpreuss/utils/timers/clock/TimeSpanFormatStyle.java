package dk.xpreuss.utils.timers.clock;

import static dk.xpreuss.utils.timers.clock.TimeSpanFormatStyle.TimeSpanFormatStyleNotation.*;

public enum TimeSpanFormatStyle {
	BASIC_NOTATION(ISO8601_NOTATION, true, true),
	EXTENDED_NOTATION(ISO8601_NOTATION, true, true);


	private TimeSpanFormatStyleNotation formatStyleNotation;
	private boolean useYear;
	private boolean useWeek;

	TimeSpanFormatStyle(TimeSpanFormatStyleNotation formatStyleNotation, boolean useYear, boolean useWeek) {
		this.formatStyleNotation = formatStyleNotation;
		this.useYear = useYear;
		this.useWeek = useWeek;
	}

	/**
	 * For IS8601:
	 * - BASIC style:       [-]PyyyymmddThhmmssfff
	 * - EXTENDED style:    [-]Pyyyy-mm-ddThh:mm:ss.fff
	 * - Basic and ext:     [-]PnYnMnDTnHnMnS
	 * ...
	 * ...
	 * Der er så meget mere kig her
	 * https://support.sas.com/documentation/cdl/en/lrdict/64316/HTML/default/viewer.htm#a003169814.htm
	 * Find ud af hvordan det skal gøres.
	 * ...
	 * ...
	 * For Simple:
	 * - Simple: [-]
	 *
	 *
	 * In basic and extended format the complete representationfor duration is:
	 * - PnYnMnDTnHnMnS
	 * - PnW:
	 */
	public enum TimeSpanFormatStyleNotation {
		ISO8601_NOTATION, SIMPLE_NOTATION;
	}

	public enum Separator {
		YEAR_MONTH("-"), YEAR_WEEK("-"), YEAR_DAY("-"), MONTH_DAY("-"), WEEK_DAY("-"),
		HOUR_MINUTE(":"), MINUTE_SECOND(":"), SECOND_FRACTION(",");
		private String separator;

		Separator(String separator) {
			this.separator = separator;
		}

		public String getSeparator() {
			return separator;
		}
	}

	/**
	 * Representations
	 * Characters used in place of digits or signs
	 */
	public enum Representations {
		/**
		 * Time-interval of a duration or called period designator, preceding a data element which represents a given duration of time.
		 * This designator precedes, without spaces, the duration.
		 */
		PERIOD("P"),
		/**
		 * Is used as recurring time-interval designator, preceding a data element which represents a given duration of one time-interval of a
		 * recurring time-interval or the total expression if there is no duration data lement.
		 */
		TIME_INTERVAL("R"),
		/**
		 * Is used a time designator to indicate the start of the representation of the time-units for hour, minute or seconds in expressions of
		 * duration.
		 */
		TIME("T"),
		YEAR("Y"), MONTH("M"), DAY("D"), WEEK("W"), HOUR("h"), MINUTE("m"), SECOND("s"), DIGIT("n"),
		/**
		 * Represents the plus sign [+] if in combination with the following element a positive value or zero needs to be represented, or a minus
		 * sign [-] if in combination with the following element a negative value needs to be represented.
		 */
		SIGN("±");
		private String representation;

		Representations(String representation) {
			this.representation = representation;
		}

		public String getRepresentation() {
			return representation;
		}
	}
}
