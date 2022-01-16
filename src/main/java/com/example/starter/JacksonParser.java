package com.example.starter;

import com.fasterxml.jackson.databind.JsonNode;

public class JacksonParser {
   JsonNode query;
   JsonNode data;

  public void setData(JsonNode data) {
    this.data = data;
  }

  public void setQuery(JsonNode query) {
    this.query = query;
  }

  public JsonNode getData() {
    return data;
  }

  public JsonNode getQuery() {
    return query;
  }
}
