package FreshRestAssured.TestingRA;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportMAnager {
	public static ExtentReports reports;
	public static ExtentTest test;
	
	public static void createReport()
	{
		reports = new ExtentReports(System.getProperty("user.dir")+"/test-output/extentreport.html");
	}
}
