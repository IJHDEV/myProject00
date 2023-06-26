package com.spring5.mypro00.common.apis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;


public class ApiTest {

	public static void main(String[] args) {

		
		String key = "j6GcyyvmxJet0G9NxmoZG7s0oEylfTXfIuOFTnYPHZITlgMREirbgHlO8qQvbV1CyO664Md6NGoVaWrP8WFOXA%3D%3D";
		String result = "";
		
		try{
			URL url = new URL("https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=" + key + "&pageNo=1&numOfRows=10&_type=json&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20230628&airlineId=AAR");
			BufferedReader bf;
			
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			result = bf.readLine();
			System.out.println("result: " + result);
			
			JSONObject jsonObject = new JSONObject(result);
			JSONObject response = jsonObject.getJSONObject("response");
			JSONObject body = response.getJSONObject("body");
			JSONObject items = body.getJSONObject("items");
			JSONArray item = items.getJSONArray("item");
			
			
			for (int i = 0; i < item.length(); i++) {
				JSONObject airRoute = item.getJSONObject(i);
				String airlineNm = airRoute.getString("airlineNm");
				String arrAirportNm = airRoute.getString("arrAirportNm");
				long arrPlandTime = airRoute.getLong("arrPlandTime");
				String depAirportNm = airRoute.getString("depAirportNm");
				long depPlandTime = airRoute.getLong("depPlandTime");
				int economyCharge = airRoute.getInt("economyCharge");
				int prestigeCharge = airRoute.getInt("prestigeCharge");
				String vihicleId = airRoute.getString("vihicleId");
				
				System.out.println();
				System.out.println("airlineNm: " + airlineNm);
				System.out.println("arrAirportNm: " + arrAirportNm);
				System.out.println("arrPlandTime: " + arrPlandTime);
				System.out.println("depAirportNm: " + depAirportNm);
				System.out.println("depPlandTime: " + depPlandTime);
				System.out.println("economyCharge: " + economyCharge);
				System.out.println("prestigeCharge: " + prestigeCharge);
				System.out.println("vihicleId: " + vihicleId);
				System.out.println("====================================");
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

}
