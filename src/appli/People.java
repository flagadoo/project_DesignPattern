package appli;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;

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

}
