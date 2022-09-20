import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extent {
	
	public static void main(String[] args) throws IOException {
		
		ExtentReports extentrepo=new ExtentReports();//Creating object of extendreport
		
		//Creating object for the which type of report we want and location where to maintain it
		ExtentSparkReporter sparkrepo=new ExtentSparkReporter("C:\\Users\\shrad\\.eclipse\\Extent_Reports\\Reports\\Extent_Report\\Report.html");
		
		extentrepo.attachReporter(sparkrepo);// attaching type of report(sparkrepoter) to extent report
		
		
		
		
		extentrepo.createTest("test1").pass("This is passed testcase");
		extentrepo.createTest("test2").fail("This is failed testcase");
		extentrepo.createTest("test3").skip("This is skiped testcase");
		extentrepo.createTest("test4").info("This is Info testcase");
		extentrepo.createTest("test5").warning("This is Waring testcase");
		
		//Logging
		extentrepo.createTest("Log1")
		.log(Status.INFO, "This is log info for Log1")
		.log(Status.PASS, "Status Is Pass Pass");
		
		
		
		
		extentrepo.flush(); // closing the report
	
		// To Opening the extent report automatically after the execution
		Desktop.getDesktop().browse(new File("C:\\Users\\shrad\\.eclipse\\Extent_Reports\\Reports\\Extent_Report\\Report.html").toURI());
	
				
	}

}
