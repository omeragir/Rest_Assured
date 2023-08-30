package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RegionPojo {

    @JsonProperty("region_id")  //hey Jackson, find region_id from JSON response and set value to variable below
    private int regionId;

    @JsonProperty("region_name")
    private String regionName;


}