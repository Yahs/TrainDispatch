import java.util.Date;
import java.lang.Object;
import java.text.SimpleDateFormat;
public class Time extends Date {

	private int hr;
	private int min;
	private int sec;
	Time time = new Time();
	
	public Time() {
		
	}
	public Time(int hours, int minutes, int seconds) {
		hr = hours;
		min = minutes;
		sec = seconds;
	}
	public Time (long time) {
		
	}
	public void setTime(long time) {
		time = new Time();
	}
	
	public long getTime() {
		hr = getHours();
	sec = getSec();
	min = getMin();
		return sec ;
		
	}
	public void setHours(int hours) {
		time.hr = hours;
	}
	public int getHours() {
		return this.hr;
	}
	public int getSec() {
		return this.sec;
	}
	public int getMin() {
		return this.min;
	}
	public void setMin(int minutes) {
		time.min = minutes;
	}
	public void setSecs(int seconds) {
		time.sec = seconds;
	}
	
	public String print() {
	final String timeString =
		    new SimpleDateFormat("HH:mm:ss").format(time.getTime());
	return timeString.toString();
	}
}