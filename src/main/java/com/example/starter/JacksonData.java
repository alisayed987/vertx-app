package com.example.starter;

import com.fasterxml.jackson.annotation.*;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@JsonIncludeProperties({"JPY","EGP"})
////@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonData {
  @JsonProperty("EGP")
  String EGP;
  @JsonProperty("JPY")
  String JPY;

}
