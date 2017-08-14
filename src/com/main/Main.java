package com.main;
/**
 * @author Nishant Singh
 *
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.pojo.*;
import com.bu.*;
public class Main {

	public static void main(String[] args) {
		try {
			// to get the csv file as input and store it in list
			List<String> lines= Files.readAllLines(Paths
					.get("Meteorite_Landings.csv")); 
			//if want to test with small data put "data.csv"
			GetData data = new GetData(lines);//reading and parsing the data
			// performing data operations
			@SuppressWarnings("unused")
			BuisnessLogic logic = new BuisnessLogic(data);

		} 		
		catch (IOException e) {

			e.printStackTrace();
		}
	}

}
