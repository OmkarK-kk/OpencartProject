package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1

	  @DataProvider(name="Logindata") 
	  public String [][] getData() throws IOException 
	  { 
		  
	  String path = ".\\testData\\testdata_opencart.xlsx"; //taking xl file from this path
	  
	  ExcelUtility xlutil = new ExcelUtility(path); //creating an object for XL
	  
	  int totalRows = xlutil.getRowCount("sheet1"); 
	  int totalcols = xlutil.getCellCount("sheet1",1);
	  
	  String loginData[][] = new String[totalRows][totalcols]; //created for two dimension array which can store
	  
	  for(int i=1; i<totalRows; i++) //1 read the data from xl-- xcel start from 1 because 1st row contains header 
		 {
		  	for(int j=0; j<totalcols; j++) //0 i is row  and j is columns 
		  	{
		  		loginData[i-1][j] = xlutil.getCellData("sheet1", i, j);  //1,0 
		  	}
		  }
	  
	  return loginData; //returning two dimensional array }
	  }
	  
	//DataProvider 2
	@DataProvider(name="Regdata")
	public Object[][] getRegData() throws IOException
	{
		String path = ".\\testData\\testdata_opencartDT.xlsx"; //taking xl file from this path
		
		ExcelUtility xlutil1 = new ExcelUtility(path);  //creating an object for XL
		
		int totalRows1 = xlutil1.getRowCount("sheet1");
		int totalcols1 = xlutil1.getCellCount("sheet1",1);
		
		String RegData[][] = new String[totalRows1][totalcols1];  //created for two dimension array which can store
		
		for(int i=1; i<totalRows1; i++)  //1 read the data from xl-- xcel start from 1 because 1st row contains header
		{
			for(int j=0; j<totalcols1; j++)   //0   i is row and j is columns
			{
				RegData[i-1][j] = xlutil1.getCellData("sheet1", i, j);  //1,0
			}
		} 
		
		return RegData; //returning two dimensional array
	}
	
	//DataProvide 3
	
	//DataProvider 4

}
