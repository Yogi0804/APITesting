package FreshRestAssured.TestingRA;

import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class Testcases {
	
	
	ResponseSpecification spec = null;
	@BeforeClass
	public void setSpecification() {
		spec = RestAssured.expect();
		spec.contentType(ContentType.JSON);
		spec.statusCode(200);
		spec.statusLine("HTTP/1.1 200 OK");
		
		ExtentReportMAnager.createReport();
	}
	@Test   
    void loginIntoDashboard() //Login into the Dashboard
    {
  
    	WebDriverManager.firefoxdriver().setup();
 		driver = new FirefoxDriver();
        driver.get("https://tms.pisystindia.com/admin/login");
		driver.findElement(By.id("admin_email")).sendKeys("sales.infinitycorp@gmail.com");
		driver.findElement(By.id("admin_password")).sendKeys("123456");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div[2]/button")).click();
    }
    
    
    // Email Promotion
    
    //Downloading Pdf from Email Promotion List
    @Test
    void downloadPdfFromPromotionEmailList() {
    	WebDriverManager.firefoxdriver().setup();
 		driver = new FirefoxDriver();
        driver.get("https://tms.pisystindia.com/admin/login");
		driver.findElement(By.id("admin_email")).sendKeys("sales.infinitycorp@gmail.com");
		driver.findElement(By.id("admin_password")).sendKeys("123456");
		driver.findElement(By.xpath("/html/body/div[1]/div/div/ul/li[9]")).click();
		driver.findElement(By.xpath("//*[@id=\"example_wrapper\"]/div[1]/div[1]/div[2]/button[3]")).click();
		driver.quit();
   }
    
  //Downloading Excel from Email Promotion List
    @Test
     void downloadexcelFromPromotionEmailList() {
    	WebDriverManager.firefoxdriver().setup();
 		driver = new FirefoxDriver();
        driver.get("https://tms.pisystindia.com/admin/login");
		driver.findElement(By.id("admin_email")).sendKeys("sales.infinitycorp@gmail.com");
		driver.findElement(By.id("admin_password")).sendKeys("123456");
		driver.findElement(By.xpath("/html/body/div[1]/div/div/ul/li[9]")).click();
		driver.findElement(By.xpath("//*[@id=\"example_wrapper\"]/div[1]/div[1]/div[2]/button[2]")).click();
		driver.quit();
   }
	@Test
	public void getListOfUsers() {
		
		ExtentReportMAnager.test = ExtentReportMAnager.reports.startTest("getListOfUsers", "Gets the list of Users");
		try {
			RestAssured.baseURI = "https://dummy.restapiexample.com/";
			ExtentReportMAnager.test.log(LogStatus.INFO, "Configured the base URL", RestAssured.baseURI);
			//String message = given().when().get("api/v1/employees").then().spec(spec).extract().path("message");
			ExtentReportMAnager.test.log(LogStatus.INFO, "Make a GET API call", "api/v1/employees");
			//ExtentReportMAnager.test.log(LogStatus.INFO, "Extract the response message", message);
			//System.out.println(message);
			ExtentReportMAnager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! All records has been fetched.");
			//ExtentReportMAnager.test.log(LogStatus.INFO, "Actual Message -> ", message);
			given().when().get("api/v1/employees").then().assertThat().body("message", IsEqual.equalTo("Successfully! All records has been fetched."));
		}
		catch(Exception ex)
		{
			ExtentReportMAnager.test.log(LogStatus.ERROR, "Exception Occured -> ", ex.getMessage());
			ExtentReportMAnager.test.log(LogStatus.FAIL, "TC FAiled",ex.getMessage());
		}
		/*
		if (message.equals("Successfully! All records has been fetched.")) {
			System.out.println("Test case passed");
		}
		else
		{
			System.out.println("Test case failed");
		}
		*/
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void CreateUser() {
		ExtentReportMAnager.test = ExtentReportMAnager.reports.startTest("CreateUser", "Create a User");
		JSONObject params = new JSONObject();
		params.put("name", "Jacob");
		params.put("age", "20");
		params.put("salary", "200000");
		ExtentReportMAnager.test.log(LogStatus.INFO, "Configured the JSON request body", params.toJSONString());
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		ExtentReportMAnager.test.log(LogStatus.INFO, "Configured the base URL", RestAssured.baseURI);
		ExtentReportMAnager.test.log(LogStatus.INFO, "Make a POST API call", "api/v1/create");
		Response response = given().header("Content-Type", "application/json").body(params.toJSONString()).post("api/v1/create");
		System.out.println("The status received: " + response.asString());
		
		ExtentReportMAnager.test.log(LogStatus.INFO, "Expected Status code", "200");
		ExtentReportMAnager.test.log(LogStatus.INFO, "Actual Status Code", Integer.toString(response.statusCode()));
		
	}
	
	@Test
	public void getSpecificUser() {
		//ResponseSpecification spec2 = RestAssured.expect();
		//spec2.contentType(ContentType.JSON);
		//spec2.statusCode(200);
		ExtentReportMAnager.test = ExtentReportMAnager.reports.startTest("getSpecificUser", "Fetch record of one user");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		ExtentReportMAnager.test.log(LogStatus.INFO, "Configured the base URL", RestAssured.baseURI);
		ExtentReportMAnager.test.log(LogStatus.INFO, "Make a GET api call", "api/v1/employee/<id>");
		ExtentReportMAnager.test.log(LogStatus.INFO, "Employee ID", "22");
		ExtentReportMAnager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! Record has been fetched");
		try
		{
			given().when().get("api/v1/employee/22").then().assertThat().body("message", IsEqual.equalTo("Successfully! Record has been fetched"));
		}
		catch(Exception ex)
		{
			ExtentReportMAnager.test.log(LogStatus.ERROR, "Exception Occured", ex.getMessage());
			ExtentReportMAnager.test.log(LogStatus.FAIL, "TC Failed", ex.getMessage());
		}
		//String message = given().when().get("api/v1/employee/5777").then().spec(spec).extract().path("message");
		//System.out.println(message);
		
	}
	
	
	@Test
	public void DeleteUser() {
		
		ExtentReportMAnager.test = ExtentReportMAnager.reports.startTest("deleteSpecificUser", "Delete a user record");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		ExtentReportMAnager.test.log(LogStatus.INFO, "Configured the BASE url", RestAssured.baseURI);
		ExtentReportMAnager.test.log(LogStatus.INFO, "Make a DELETE api call", "/api/v1/delete/<id>");
		ExtentReportMAnager.test.log(LogStatus.INFO, "Employee ID", "2");
		ExtentReportMAnager.test.log(LogStatus.INFO, "Expected Message -> ", "Successfully! Record has been deleted");
		try
		{
			given().delete("/api/v1/delete/2").then().assertThat().body("message", IsEqual.equalTo("Successfully! Record has been deleted"));
		}
		catch(Exception ex){
			
			ExtentReportMAnager.test.log(LogStatus.ERROR, "Exception Occured", ex.getMessage());
			ExtentReportMAnager.test.log(LogStatus.FAIL, "TC Failed", ex.getMessage());
			
			
		}
		Response response = given().delete("/api/v1/delete/2");
		System.out.println("The status received: " + response.asString());

	}
	
	@Test
	public void UpdateUser() {
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		Response response = given().put("/api/v1/update/21");
		System.out.println("The status received: " + response.asString());
		
	}
	
	@AfterClass
	public void endReport()
	{
		ExtentReportMAnager.reports.flush();
	}

	

}
