import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class extent_adding_screenshots {
	
	static WebDriver driver;


	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://github.com/");
		
		
		ExtentReports extentrepo=new ExtentReports();//Creating object of extendreport
		
		//Creating object for the which type of report we want and location where to maintain it
		ExtentSparkReporter sparkrepo=new ExtentSparkReporter("C:\\Users\\shrad\\.eclipse\\Extent_Reports\\Reports\\Extent_Report\\Report.html");
		
		extentrepo.attachReporter(sparkrepo);// attaching type of report(sparkrepoter) to extent report
		
		
		
		// Attaching screenshots at Test Level
		extentrepo
		.createTest("Base 64 Screenshot","Attaching Screenshots At Test Level")
		.addScreenCaptureFromBase64String(capturescreenshotsusingbase64());
		
		extentrepo
		.createTest("Screenshot using File", "Attaching Screenshots At Test Level")
		.addScreenCaptureFromPath(capturescreenshotsusingfile());
		
		
		
		extentrepo.flush(); // closing the report
		
		driver.quit();
	
		// To Opening the extent report automatically after the execution
		Desktop.getDesktop().browse(new File("C:\\Users\\shrad\\.eclipse\\Extent_Reports\\Reports\\Extent_Report\\Report.html").toURI());
	
				
	}
	
	
	//CODE FOR SCREENSHOT IN BASE64
	public static String capturescreenshotsusingbase64() throws IOException
	{
		TakesScreenshot screen=(TakesScreenshot)driver; // Casting Takescreesnshot to driver
		String Base64screenshot=screen.getScreenshotAs(OutputType.BASE64); // it will store the screenshot in String
		
		byte[] bytearray=Base64.getDecoder().decode(Base64screenshot);// converting base64 to byte array
		FileOutputStream fos=new FileOutputStream("./Screenshots/base64.jpeg"); 
		fos.write(bytearray); // Writing bytearray into fileoutputstream
		fos.close();
		return Base64screenshot; 	
		
	}
	
	//CODE FOR SCREENSHOT IN FILE
	public static String capturescreenshotsusingfile() throws IOException
	{
		
		TakesScreenshot screen=(TakesScreenshot)driver; // Casting Takescreesnshot to driver
		File captured_file=screen.getScreenshotAs(OutputType.FILE);// Using OutputType.FILE, it will store the 
		FileUtils.copyFile(captured_file, new File("./Screenshots/file.png")); // SOURCE Will be caupted file and designation will be Folder where to store the screenshots
		return captured_file.getAbsolutePath();
		
		
	}


}
