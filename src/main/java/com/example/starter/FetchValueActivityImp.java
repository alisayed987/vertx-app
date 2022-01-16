package com.example.starter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class FetchValueActivityImp implements FetchValueActivity{

  ObjectMapper mapper = new ObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,true);
  @Override
  public float getUSDFromAPI() {

    try {
      // Http GET request (to get the USD value in EGP)
      URL url = new URL("https://freecurrencyapi.net/api/v2/latest?apikey=c604bf70-746f-11ec-a4ee-d7b783de69df");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Accept", "application/json");
      InputStream responseStream = con.getInputStream();

      // Mapping response to Json
      JacksonParser jsonMap = mapper.readValue(responseStream,JacksonParser.class);
      JsonNode currency_values = jsonMap.getData();

      Float EGP = Float.parseFloat( currency_values.get("EGP").toString() ) ;
      return (EGP);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }

  }
}
