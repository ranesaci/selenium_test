package seleniun_test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
	public static final long DELAY = 1860000;
	private static final String URL = "http://mycutebaby.in/contest/participant/?n=5e8aa945525c4&utm_source=wsapp_share&utm_campaign=April_2020&utm_medium=shared&utm_term=wsapp_shared_5e8aa945525c4&utm_content=participant";
	private static final String FIREFOX_DRIVER = "C:\\Users\\sachin\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	private static final String FIREFOX_DRIVER_UNIX = "/usr/bin/geckodriver";
	private static final String CHROME_DRIVER_UNIX = "/usr/bin/chromedriver";
	private static int count = 0;
	// private static Timer timerForMinDispaly = null;

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println(new Date());
				try {
					runClickJob();
					count++;
					System.out.println("Count is >>>>>>>>>" + count);
					printDateAndTime();
					/*
					 * if(null != timerForMinDispaly ) { timerForMinDispaly.cancel(); } synchronized
					 * (SeleniumTest.class) { counMins=0; }
					 */
					// invokeMinDisplay();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, new Date(), DELAY);

		// runClickJob();

	}

	private static void runClickJob() throws InterruptedException {
		// System.setProperty("webdriver.ie.driver",
		// "C:\\Users\\sachin\\Downloads\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");
		WebDriver driver = null;
		try {
		System.setProperty("webdriver.gecko.driver", CHROME_DRIVER_UNIX);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("start-maximized");
		
		driver = new ChromeDriver(chromeOptions);
		driver.manage().deleteAllCookies();
		driver.get(URL);
		// driver.manage().window().maximize();
		// WebElement elementName = driver.findElement(By.className("vote-btn"));
		// driver.findElement(By.id("v")).sendKeys("dgfdff");;

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.id("vote_btn")));
		/// element1.sendKeys("rtertret");
		element1.click();
		/*
		 * //System.out.println(elementName.getText()+"*********"); Thread.sleep(5000);
		 * elementName.sendKeys("Sachin Rane"); //click elementName.click();
		 * //driver.findElement(By.id("vote_btn")).click();
		 */
		Thread.sleep(5000);
		//driver.close();
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(null != driver) {
			driver.quit();
			}
		}
	}

	private static void printDateAndTime() {
		Date today = new Date();

		// displaying this date on IST timezone
		DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:SS z");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		String IST = df.format(today);
		System.out.println("Date in Indian Timezone (IST) : " + IST);

	}

	private static void invokeMinDisplay() {
		Timer timerForMinDispaly = new Timer("my_timer");
		// System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
		timerForMinDispaly.schedule(new TimerTask() {
			int counting = 0;

			@Override
			public void run() {
				System.out.println(new Date() + " and minutes elapsed:" + counting);
				counting++;
				if (counting >= 30) {// for 30 min
					// System.out.println("Killling
					// Thread.currentThread().getName():"+Thread.currentThread().getName());
					Thread.currentThread().stop();
				}

			}
		}, new Date(), 60000);// repeat after every 1 min

	}

}
