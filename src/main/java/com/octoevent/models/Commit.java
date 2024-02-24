package com.octoevent.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class Commit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_current", nullable = false)
    private Long id_current;

    @JsonProperty("id")
    private String id;

    @JsonProperty("tree_id")
    private String tree_id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("url")
    private String url;

    @JsonProperty("distinct")
    private boolean distinctB;

//    @JsonProperty("author")
//    @ElementCollection(targetClass = Author.class)
//    private List<Author> author;
//
//    @JsonProperty("committer")
//    @ElementCollection(targetClass = Commiter.class)
//    private List<Commiter> committer;
//
//    @JsonProperty("added")
//    @ElementCollection(targetClass = String.class)
//    private List<String> added;
//
//    @JsonProperty("removed")
//    @ElementCollection(targetClass = String.class)
//    private List<String> removed;
//
//    @JsonProperty("modified")
//    @ElementCollection(targetClass = String.class)
//    private List<String> modified;

}
