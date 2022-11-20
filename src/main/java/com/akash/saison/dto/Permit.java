package com.akash.saison.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permit implements Serializable {

    @JsonProperty("objectid")
    private Integer objectId;

    @JsonProperty
    private String applicant;

    @JsonProperty("facilitytype")
    private String facilityType;

    @JsonProperty
    private Integer cnn;

    @JsonProperty("locationdescription")
    private String locationDescription;
    @JsonProperty
    private String address;
    @JsonProperty
    private String blocklot;
    @JsonProperty
    private String lot;
    @JsonProperty
    private String permit;
    @JsonProperty
    private String status;
    @JsonProperty
    private String fooditems;
    @JsonProperty
    private Double x;
    @JsonProperty
    private Double y;
    @JsonProperty
    private Double latitude;
    @JsonProperty
    private Double longitude;
    @JsonProperty
    private String schedule;
    @JsonProperty
    private String dayshours;
    @JsonProperty
    private String received;
    @JsonProperty
    private String noisent;
    @JsonProperty
    private String approved;
    @JsonProperty
    private Integer priorpermit;
    @JsonProperty
    private Date expirationdate;
    @JsonProperty
    private Location location;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Location implements Serializable {
        @JsonProperty
        private Double latitude;
        @JsonProperty
        private Double longitude;
        @JsonProperty("human_address")
        private String humanAddress;
    }
}
