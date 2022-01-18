package com.example.starter;

import com.fasterxml.jackson.databind.JsonNode;

public class JacksonParser {
   JsonNode query;
   JacksonData data;


  public JacksonData getData() {
    return data;
  }

  public JsonNode getQuery() {
    return query;
  }
}
