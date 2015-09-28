package sodexoPoc;

import java.io.Serializable;

public class SodexoDetails implements Serializable{
public static final String month_array[] = {
			"None",
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sept",
            "Oct",
            "Nov",
            "Dec"
};

	private int amount=0;
	private String date;
	private String issueDate;
	boolean status=false;
	public void setAmount(int amount)
	{
		this.amount=amount;
	}
	
	
	public static final String getMonth(String date)
	{
		return month_array[Integer.parseInt(date.substring(5, 7))];
	}
	
	public String getYear()
	{
		return date.substring(0, 4);
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setDate(String date)
	{
		this.date=date;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setIssueDate(String iDate)
	{
		this.issueDate=iDate;
	}
	
	public String getIssueDate()
	{
		return issueDate;
	}
	public void setStatus(boolean st)
	{
		status=st;
	}
	
	public boolean getStatus()
	{
		return status;
	}
}