package com.seguimiento.pagos.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pruebas")
@CrossOrigin(origins = "*")
public class PruebasController {


	final String url = "https://jsonmock.hackerrank.com/api/inventory";
	private RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.getForObject(url, String.class);
	
	JSONObject root = new JSONObject(result);
	JSONArray data = root.getJSONArray("data");
	
	private static Logger log = LoggerFactory.getLogger(PruebasController.class);
	
	@GetMapping(value = "/filter/price/{initial}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean servicioPrueba(@PathVariable Integer initial, @PathVariable Integer end){
		
		try {
				log.info(" ================ Cantidad de datos: " + root.getInt("per_page"));
			for (int i = 0; i<data.length(); i++) {
				log.info("valor "+i+": " + data.getJSONObject(i).getInt("price") + " e item: " + data.getJSONObject(i).getString("item"));
			}
			
		} catch (Exception e) {
			log.error("error econtrado: "+e.getMessage());
		}
		
		return true;
	}
	


}
