package Igal;

//package <set your test package>;
import com.experitest.client.*;
import org.testng.annotations.*;
/**
*
*/
public class AndroidTest {
  private String accessKey = "eyJ4cC51Ijo4NSwieHAucCI6MiwieHAubSI6Ik1BIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4Mjg5NDU4MjksImlzcyI6ImNvbS5leHBlcml0ZXN0In0.NJ0DFLOdtLvkhz6NPPNtRu2GZFLwhCprzeohEyd3FZY";
  private String projectBaseDirectory = "C:\\Users\\igal.reiblat\\workspace\\project5";
  protected Client client = null;
  protected GridClient grid = null;

  @BeforeMethod
  public void setUp(){
      // In case your user is assign to a single project you can provide an empty string, 
      // otherwise please specify the project name
      grid = new GridClient(accessKey, "sales.experitest.com",443, true);
      client = grid.lockDeviceForExecution("Untitled","@os='android'", 10, 50000);
      client.setProjectBaseDirectory(projectBaseDirectory);
      client.setReporter("xml", "reports", "Untitled");
  }

  @Test(groups = {"seetest"})
  public void testUntitled(){
      // This command "setDevice" is not applicable for GRID execution 
      if(client.install("cloud:com.experitest.ExperiBank/.LoginActivity", true, false)){
          // If statement
      }
      client.launch("cloud:com.experitest.ExperiBank/.LoginActivity", true, false);
      client.elementSendText("NATIVE", "xpath=//*[@accessibilityLabel='Username']", 0, "company");
      client.elementSendText("NATIVE", "xpath=//*[@accessibilityLabel='Password']", 0, "company");
      client.click("NATIVE", "xpath=//*[@text='Login']", 0, 1);
      client.click("NATIVE", "xpath=//*[@accessibilityLabel='Logout']", 0, 1);
  }

  @AfterMethod
  public void tearDown(){
      // Generates a report of the test case.
      // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
      client.generateReport(false);
      // Releases the client so that other clients can approach the agent in the near future. 
      client.releaseClient();
  }
}
