package com.pojo;
/**
 * @author Nishant Singh
 *
 */


import java.util.List;

public class GetData {

	public Model model = new Model();
	/**
	 * Constructor will read the data from the list 
	 * Store required
	 */
	public GetData(List<String> list)
	{
		for(String line:list)
		{
			line = line.replace("\"", "");
			String[]result = line.split(",");
			model.id.add(result[1]);
			model.recclass.add(result[3]);
			model.mass.add(result[4]);
			try
			{
				if(result[6].equalsIgnoreCase("Fell"))//data are not formated properly
					model.date.add(result[7]);
				else
					model.date.add(result[6]);
			}
			catch(Exception e){
				continue;//continue in case of data is missing
			}

		}

	}
}


