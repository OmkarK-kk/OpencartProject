package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="Logindata")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\Logindata.xlsx"; //taking xl file from this path
		
		ExcelUtility xlutil = new ExcelUtility(path);  //creating an object for XL
		
		int totalRows = xlutil.getRowCount("sheet1");
		int totalcols = xlutil.getCellCount("sheet1",1);
		
		String loginData[][] = new String[totalRows][totalcols];  //created for
		
		for(int i=1; i<totalRows; i++)  //1read the data from xl
		{
			for(int j=0; j<totalcols; j++)   //0   i is row and j is columns
			{
				loginData[i-1][j] = xlutil.getCellData("sheet1", i, j);  //1,0
			}
		}
		
		return loginData; //returning two dimensional array
	}
	
	//DataProvider 2
	
	//DataProvide 3
	
	//DataProvider 4

}
