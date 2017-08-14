package com.bu;
/**
 * @author Nishant Singh
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import com.pojo.*;

public class BuisnessLogic {
	HashMap<String,Integer> hm=new HashMap<String,Integer>();
	TreeMap<Integer,String> tm=new TreeMap<Integer,String>(); 
	Model modelyr = new Model();

	public BuisnessLogic(GetData data)
	{
		groupRecclass(data.model.recclass);
		findHeaviestLightest(data.model.mass);
		numberofRecoding(data.model.date);
		calculateAverageMass(data.model.mass);

	}
	/**
	 * method to check data are numerics
	 * @param str
	 * @return Boolean
	 */
	public static boolean isNumeric(String str)
	{
		try
		{
			@SuppressWarnings("unused")
			int data = Integer.parseInt(str);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
	/**
	 * method to print recording group by
	 * @param recclass
	 */
	void groupRecclass(ArrayList<String> recclass)	
	{
		System.out.println("Number of recording grouped by recclass ");
		for(int i=1; i<recclass.size();i++)
		{
			String  rclass= recclass.get(i);


			int count=1;
			for(int j=i+1;j<recclass.size();j++)
			{
				if(rclass.equalsIgnoreCase(recclass.get(j)))
				{
					count++;
					recclass.remove(j);
				}
			}

			System.out.println(rclass+","+count);
		}

	}

/**
 * method to find heavy and light weight Meteorites 
 * @param mass
 */
	void findHeaviestLightest(ArrayList<String> mass)	
	{
		int light=10000;
		int heavy=0;
		for(String weight:mass)
		{
			if(isNumeric(weight))
			{
				int local=Integer.parseInt(weight);
				if(local>heavy)
					heavy=local;
				if(local<light)
					light=local;
			}	
		}
		System.out.println("Heaviest and Lightest Meteorite landing recorded ");
		System.out.println("Heaviest: "+heavy+"g");
		System.out.println("Lightest: "+light+"g");
	}
	/**
	 * method to get the year when Meteors fell
	 * @param dates
	 */
	void numberofRecoding(ArrayList<String> dates)	
	{
		int count=0;
		for(String date:dates)
		{

			count++;
			try{
				if(count>1&&(!date.isEmpty())&&(!date.equals("")))
				{
					int year = Integer.parseInt((String) date.subSequence(6,10));
					modelyr.year.add(year);
				}
			}
			catch(Exception e){
				continue;
			}
		}
		modelyr.year.sort(null);
		Assendingyear(modelyr.year);
		DecendingSightings(hm);


	}
	/**
	 * method to print Year Range Ascending
	 * @param year
	 */
	void Assendingyear(ArrayList<Integer> year)
	{
		int itr=0;
		int startyear = modelyr.year.get(0);
		int sizeyr = modelyr.year.size();
		int endyear = modelyr.year.get(sizeyr-1);
		System.out.println(endyear);
		if(startyear%10!=0)
			startyear=startyear-(startyear%10);
		System.out.println("Sorted by Year Range Ascending ");
		for(int i=startyear; i<=endyear; i=i+10)
		{
			int limit= i+9;
			if((endyear-i)<9)
				limit=endyear;
			int counter=0;
			int j=modelyr.year.get(itr);
			while(j<=limit)
			{
				counter++;

				if(itr<(sizeyr-1))
				{
					itr++;
					j=modelyr.year.get(itr);
				}
				else break;

			}
			String key=Integer.toString(i).concat("-")
					.concat(Integer.toString(limit));
			hm.put(key,counter);	
			System.out.println(key+"  "+counter);
		}
	}
	/**
	 * method to print  year range based on Sightings Descending
	 * @param hmp
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void DecendingSightings(HashMap<String,Integer> hmp)
	{
		List mapKeys = new ArrayList(hmp.keySet());
		List mapValues = new ArrayList(hmp.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap sortedMap = new LinkedHashMap();

		Iterator valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Object val = valueIt.next();
			Iterator keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Object key = keyIt.next();
				String comp1 = hmp.get(key).toString();
				String comp2 = val.toString();

				if (comp1.equals(comp2)){
					hmp.remove(key);
					mapKeys.remove(key);
					sortedMap.put((String)key,val);
					break;
				}

			}

		}
		System.out.println("Sorted by Number of Sightings Descending");
		int size= sortedMap.size();
		ArrayList<Integer> keys = new ArrayList<Integer>(sortedMap.keySet());
		for(int i=size-1; i>=0;i--){
			System.out.println(keys.get(i)+" "+sortedMap.get(keys.get(i)));
		}

	}
	/**
	 * method to print average mass of meteorites 
	 * @param mass
	 */
	void calculateAverageMass(ArrayList<String> mass)	
	{
		double sum=0;
		long count=0;
		for(String weight:mass)
		{
			if(isNumeric(weight))
			{
				count++;
				sum=sum+Integer.parseInt(weight);

			}	
		}
		sum=sum/count;
		System.out.println("Average mass of meteorites: "+sum);
	}
}
