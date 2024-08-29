package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.playwrightfactory.PlaywrightFactory;

public class ExtentReportListener implements ITestListener {
	
	private static final String OUTPUT_DIR = "./reports/";
	private static final String FILE_NAME = "ExtentReport.html";
	
	private static ExtentReports extent = init();
	private static ThreadLocal<ExtentTest> tlTest = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	
	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_DIR);		
		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_DIR+FILE_NAME);
		reporter.config().setReportName("Opencart Scenarios Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Name", "Sandeep");
		extentReports.setSystemInfo("Author", "Sandeep");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Build", "1.1");
		
		return extentReports;
	}
	
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("The Test Started !!!!!!");
	}
	
	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("The Test Finished !!!!!!");
		extent.flush();
		tlTest.remove();
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		System.out.println("The qualified name is : "+qualifiedName);
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid+1, last);
		
		System.out.println(methodName+" STARTED !!!!");
		
		ExtentTest test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.assignCategory(className);
		
		tlTest.set(test);
		tlTest.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}
	
	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "PASS !!!!!!");
		tlTest.get().pass("Test PASSED !!!");
		tlTest.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(PlaywrightFactory.takeScreenShot(), 
				result.getMethod().getMethodName()).build());
		tlTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "FAILED !!!!");
		tlTest.get().fail("Test FAILED !!!!");
		tlTest.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(PlaywrightFactory.takeScreenShot(),
				result.getMethod().getMethodName()).build());
		tlTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+ "SKIPPED !!!!");
		tlTest.get().skip("Test SKIPPED !!!!");
		tlTest.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(PlaywrightFactory.takeScreenShot(), 
				result.getMethod().getMethodName()).build());
		tlTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed Within Success Percentage : "+result.getMethod().getMethodName());
	}
	
	
	public Date getTime(long timInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timInMillis);
		return calendar.getTime();
	}
}
