package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {

//		We create a base class for all those methods which will be repeated and used by other classes. This is for re-usability of codes.
//		We have to use (public) access modifier, so that the following methods will be accessible by other classes, even the child classes.
//		All the methods in BaseClass, should be public.
	public static WebDriver driver;
	
/*		Logger is a class, and in here we just declare a variable. We have to initiate the Logger variable in setup() method,
		so all other child classes have access to it.
		LogManager is another predefined class in Log4j package, has a static method getLogger(). This method is used to find or 
		create a logger with the name passed as parameter. 
		getLogger(this.getClass()), will refer to the class name executing and the same class name will be displayed in the log file. 
		so we can see which class/ test has failed.
*/
	public Logger logger;
	
	
/*		ResourceBundle is a class in Java and is used to get data from the config.properties file using static method
 		getBundle() which takes name of the config.properties file as a parameter.
 		We create the public global variable of ResourceBundle class first and in the setup method we call the ResourceBundle class
 		and its method getBundle() and pass the config.properties file name as a parameter and assign it to a variable (rb).
 */
	public ResourceBundle rb;
	
	
	@BeforeClass (groups = {"Sanity", "Master", "Regression"})
	@Parameters("browser")
	public void setup(String br)
	{
		logger = LogManager.getLogger(this.getClass());
		
		rb = ResourceBundle.getBundle("config");
		
		
/*		The following statements will avoid the browser from displaying the message that says 
 *		(chrome is being controlled by an automated software).
 		We have to create object of ChromeOptions class and pass it as a parameter, while we create the ChromeDriver() class objects reference.
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		driver = new ChromeDriver(options);		// options passed as a parameter.
*/ 		
		if(br.equals("chrome")) {
		driver = new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(rb.getString("appUrl"));
//		rb will return the config.properties file, from which we are capturing the String using getString() method and passing the 
//		key (appUrl) as a parameter in the brackets.
		driver.manage().window().maximize();
		
	}
	
	@AfterClass (groups = {"Sanity", "Master", "Regression"})
	public void tearDown()
	{
		driver.quit();
	}

//		We create the following reusable methods so every class access them.
	
//		RandomStringUtils is a class in Java which has a static method called randomAlphabetic(5) is used to generate 
//		random String consist of alphabets. (5) is number of alphabets.
//		randomNumeric(10) is another method in RandomStringUtils class, that is used to generate random string consist of numbers.
	
	public String randomString()
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return(generatedString1);
	}
	
	public String randomNumber()
	{
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return(generatedString2);
	}
	
	public String randomAlpha_Numeric()
	{
		String str = RandomStringUtils.randomAlphabetic(5);
		String numbers = RandomStringUtils.randomNumeric(4);
		return(str+"@"+numbers);
	}
	
//		The following statement will capture the screenshot 
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
}
