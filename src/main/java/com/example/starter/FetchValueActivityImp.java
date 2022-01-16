package com.example.starter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchValueActivityImp implements FetchValueActivity{
  @Override
  public float getUSDFromAPI() {

    try {
      // Http GET request (to get the USD value in EGP)
      URL url = new URL("https://freecurrencyapi.net/api/v2/latest?apikey=c604bf70-746f-11ec-a4ee-d7b783de69df");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Accept", "application/json");
      InputStream responseStream = con.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputline;
      StringBuffer response = new StringBuffer();

      // Appending response strings together
      while((inputline = in.readLine()) != null){
        response.append(inputline);
      }
      int start = response.toString().indexOf("EGP");
      String string_val = response.toString().substring(start+5,start+10);

      // Extracting EGP from the String Response
      Float EGP = Float.parseFloat( string_val ) ;
      return (EGP);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }

  }
}
