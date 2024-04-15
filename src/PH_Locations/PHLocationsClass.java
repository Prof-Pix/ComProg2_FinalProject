package PH_Locations;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class PHLocationsClass {
	
	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, List<String>>>> phLocations = new LinkedHashMap<>();
	
	public PHLocationsClass() {
		//FOR REGION 1 PROVINCES
		LinkedHashMap<String,  LinkedHashMap<String, List<String>>> r1Provinces = new LinkedHashMap<>();
				
					phLocations.put("Region 1", r1Provinces);
				
					//FOR PROVINCES AND THEIR CITIES
					LinkedHashMap<String, List<String>> ilocosNorteCities = new LinkedHashMap<>();
					
					r1Provinces.put("Ilocos Norte", ilocosNorteCities);
					
						//FOR CITIES AND THEIR BARANGAYS
					
							//BACARRA CITY
							List<String> bacarraBarangays = new ArrayList<>();
							bacarraBarangays.add("Bani");
							bacarraBarangays.add("Buyon");
							
							ilocosNorteCities.put("Bacarra", bacarraBarangays);
							
							//BADOC CITY
							List<String> badocBarangays = new ArrayList<>();
							badocBarangays.add("Alay-Nangbabaan");
							badocBarangays.add("Alogoog");
							
							ilocosNorteCities.put("Badoc", badocBarangays);
					
					LinkedHashMap<String, List<String>> ilocosSurCities = new LinkedHashMap<>();
					
					r1Provinces.put("Ilocos Sur", ilocosSurCities);
					
						//FOR CITIES AND THEIR BARANGAYS
					
							//ALILEM CITY
							List<String> alilemBarangays = new ArrayList<>();
							alilemBarangays.add("Alilem Daya");
							alilemBarangays.add("Amilongan");
							
							ilocosSurCities.put("Alilem", alilemBarangays);
							
							//BANAYOYO CITY
							List<String> banayoyoBarangays = new ArrayList<>();
							banayoyoBarangays.add("Bagbagotot");
							banayoyoBarangays.add("Banbanaal");
							
							ilocosSurCities.put("Banayoyo", banayoyoBarangays);
				
				//FOR REGION 2 PROVINCES
				LinkedHashMap<String, LinkedHashMap<String, List<String>>> r2Provinces = new LinkedHashMap<>();
				
				phLocations.put("Region 2", r2Provinces);
				
				//FOR PROVINCES AND THEIR CITIES
				LinkedHashMap<String, List<String>> batanesCities = new LinkedHashMap<>();
				
				r2Provinces.put("Batanes", batanesCities);
				
					//FOR CITIES AND THEIR BARANGAYS
				
						//BASCO CITY
						List<String> bascoBarangays = new ArrayList<>();
						bascoBarangays.add("Chanarian");
						bascoBarangays.add("Ihubok I");
						
						batanesCities.put("Basco", bascoBarangays);
						
						//ITBAYAT CITY
						List<String> itbayatBarangays = new ArrayList<>();
						itbayatBarangays.add("Raele");
						itbayatBarangays.add("San Rafael");
						
						batanesCities.put("Badoc", itbayatBarangays);
				
				LinkedHashMap<String, List<String>> cagayanCities = new LinkedHashMap<>();
				
				r2Provinces.put("Cagayan", cagayanCities);
				
					//FOR CITIES AND THEIR BARANGAYS
				
						//ABULUG CITY
						List<String> abulugBarangays = new ArrayList<>();
						abulugBarangays.add("Alinunu");
						abulugBarangays.add("Bagu");
						
						cagayanCities.put("Abulug", abulugBarangays);
						
						//ALCALA CITY
						List<String> alcalaBarangays = new ArrayList<>();
						alcalaBarangays.add("Abbeg");
						alcalaBarangays.add("Afusing Bato");
						
						cagayanCities.put("Alcala", alcalaBarangays);
		
	}

}
