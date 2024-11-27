
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class FitPeoAutomation 
{
	public static void main(String[] args)
    {
        // Set up the WebDriver
        
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        try {
            // Step 1: Navigate to FitPeo Homepage
            driver.get("https://fitpeo.com/"); // Replace with actual FitPeo URL
            Thread.sleep(2000);

            // Step 2: Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = driver.findElement(By.linkText("Revenue Calculator"));
            revenueCalculatorLink.click();
            Thread.sleep(4000);
            
            // Step 3: Scroll to the slider section
            JavascriptExecutor js = (JavascriptExecutor) driver; 
            js.executeScript("window.scrollBy(0,450)");
            
            
            // Step 4: Adjust the slider to set value to 820 and validate text box
            WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));// Replace with actual slider ID
            
            Thread.sleep(2000);
            // Move the slider close to the desired value using dragAndDropBy
            //action.dragAndDropBy(slider, 92, 0).perform(); // Start from a known position
            //Thread.sleep(2000);
            // Fine-tune the slider value using arrow keys
            int targetValue = 820;
            int currentValue = Integer.parseInt(slider.getAttribute("value"));
            
            while (currentValue < targetValue) {
                slider.sendKeys(Keys.ARROW_RIGHT);
                currentValue = Integer.parseInt(slider.getAttribute("value"));
            }
            
            while (currentValue > targetValue) {
                slider.sendKeys(Keys.ARROW_LEFT);
                currentValue = Integer.parseInt(slider.getAttribute("value"));
            }
            Thread.sleep(4000);
           
            // Step 6: Select CPT Codes
            WebElement CPT99091Checkbox=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/label/span[1]/input"));
    		CPT99091Checkbox.click();
    		Thread.sleep(4000);
    		
    		WebElement CPT99453Checkbox=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/label/span[1]/input"));
    		CPT99453Checkbox.click();
    		Thread.sleep(4000);
    		
    		WebElement CPT99454Checkbox=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[3]/label/span[1]/input"));
    		WebElement CPT99474Checkbox=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[8]/label/span[1]/input"));
    		
    		CPT99454Checkbox.click();
    		
    		js.executeScript("window.scrollBy(0,1000)");
    		Thread.sleep(4000);
    		
    		CPT99474Checkbox.click();
    		Thread.sleep(4000);

            System.out.println("CPT Codes selected successfully.");

            // Step 7: Validate Total Recurring Reimbursement
            WebElement reimbursementHeader = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/p[4]")); // Replace with actual ID
            if (reimbursementHeader.getText().contains("$110700")) {
                System.out.println("Total Recurring Reimbursement value is $110700.");
            } else {
                System.err.println("Total Recurring Reimbursement value is incorrect.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
