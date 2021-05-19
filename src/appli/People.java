package appli;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Interface qui lis les données extraites de TEAMS pour en extraire l'activitée des participants
 * aux réunions, ainsi que leurs identités, en implémentant le DP Factory.
 *
 * @author Jade
 *
 */
public interface People extends Comparable<People> {


    public String get_id();

    public void addPeriod(String action, String instant);

    public void forceEndTimeAt(String instant);

    public void forceStartTimeAt(String instant);

    public long getTotalAttendanceDuration();

    public boolean isClosed();

    public String getName();

    public String getDate();

    public boolean isOutOfPeriod();

    public String getCode();

    @Override
    public String toString();

    @Override
    public int compareTo(People o);

	public String get_name();

	public String get_start();

	public String get_stop();

	public LinkedList<TEAMSPeriod> get_periodList();

}
