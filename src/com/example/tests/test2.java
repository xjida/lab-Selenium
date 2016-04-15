package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.csvreader.CsvReader;

@RunWith(Parameterized.class)
public class test2 {
  private WebDriver driver;
  private String baseUrl;
  //private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String input1 = null;
  private String input2 = null;
  
  public test2(String input1,String input2){ 
  this.input1 = input1;
  this.input2 = input2;
  }

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.firefox.bin", "/Applications/Firefox");
    driver = new FirefoxDriver();
    baseUrl = "http://www.ncfxy.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Parameters
  public static Collection<Object[]> getData() throws IOException{
	Object[][] list = new Object[109][2];
	File file = new File("/Users/ida/Desktop/info.csv");
	FileReader fReader = new FileReader(file);
	CsvReader csvReader = new CsvReader(fReader);
	for(int i =0 ;csvReader.readRecord()&&i<109;i++){
		list[i][0] = new String(csvReader.get(0));
		list[i][1] = new String(csvReader.get(1));
		
	}
	//CsvReader r = new CsvReader("F://Eclipse//Test//src//info.csv", ',',Charset.forName("GBK"));
	System.out.println(list);
	return Arrays.asList(list);
	  
  }
  @Test
  public void testLab2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys(this.input1);
    driver.findElement(By.id("pwd")).clear();
    driver.findElement(By.id("pwd")).sendKeys(this.input1.substring(4,10));
    System.out.println(this.input1.substring(4,10));
    driver.findElement(By.id("submit")).click();
    assertEquals(this.input2, driver.findElement(By.xpath("//tbody[@id='table-main']/tr[1]/td[2]")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}