import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonPracticeDemo {
    public static void main(String args[]){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();

        //enter mobile in search-box
        WebElement searchBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("Mobile");

        //click on submit button
        WebElement searchBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-search-submit-button")));
        searchBtn.click();

        //debugging
        System.out.println("Search executed successfully!");

        //Scroll down the sidebar and select the 4 Stars & Up customer review filter.
        //By.xpath("//i[contains(@class,'a-star-medium-4')]/parent::a")
        //or can also use the above one
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='a-icon a-icon-star-medium a-star-medium-4']"))).click();

        //Enter 13000 in id="low-price", 18000 in id="high-price", and click Go (class="a-button-input").

        //wait.until(ExpectedConditions.urlContains("mobile"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-component-type='s-search-result']")));
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl + "&low-price=10000&high-price=20000");
        System.out.println("Price filter (₹10,000 - ₹20,000) applied successfully!");

        //click on dropdown for sorting
        Select select=new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("s-result-sort-select"))));
        select.selectByVisibleText("Price: Low to High");

        //6. BLOCK: SELECT FIRST SORTED PRODUCT & HANDLE WINDOW SWITCH
        //click on the first product once after sorting them

        String parentWindow=driver.getWindowHandle();

        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                //"(//div[@data-component-type='s-search-result']//h2)[1]")
                By.xpath("(//div[@data-component-type='s-search-result']//h2)[1]")
        ));
        firstProduct.click();
        System.out.println("First product clicked!");

        // URL while still on Tab 1 (Search page)
        System.out.println("URL before switch (Tab 1): " + driver.getCurrentUrl());

        // Wait for Tab 2 to register and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // URL after switching to Tab 2 (Product details page)
        System.out.println("URL after switch (Tab 2): " + driver.getCurrentUrl());

        WebElement addcartBtn=wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addcartBtn.click();
        System.out.println("Product added to cart successfully!");

        WebElement cartBtn=wait.until(ExpectedConditions.elementToBeClickable(By.id("a-button-text")));
        cartBtn.click();

        driver.quit();
        System.out.println("Test finished with exit code 0.");
    }
}
