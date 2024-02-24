package com.octoevent.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commiter implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;
}
