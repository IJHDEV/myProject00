package com.spring5.mypro00.common.apis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiTest {

	public static void main(String[] args) {

		
		String key = "j6GcyyvmxJet0G9NxmoZG7s0oEylfTXfIuOFTnYPHZITlgMREirbgHlO8qQvbV1CyO664Md6NGoVaWrP8WFOXA%3D%3D";
		String result = "";
		
		try{
			URL url = new URL("https://api.odcloud.kr/api/15043890/v1/uddi:9840de90-5907-49bd-94ed-acd173ea9ae1?page=1&perPage=10&serviceKey=" + key);
			BufferedReader bf;
			
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			result = bf.readLine();
			System.out.println(result);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			
			JSONArray airRoutes = (JSONArray) jsonObject.get("data");
			
			String val = "";
			
			for (int i = 0; i < airRoutes.size(); i++) {
				JSONObject valNm = (JSONObject) airRoutes.get(i);
				val += valNm.get("기종") + " ";
			}
			
			System.out.println("기종: " + val );
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}

}
