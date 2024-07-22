package com.documanque.documanqueserver.Commentaire;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AddComReq {

    @JsonProperty("postedBy")
    long postedBy;
    @JsonProperty("postedOn")
    long postedOn;
    @JsonProperty("announcer")
    long announcer;
    @JsonProperty("text")
    String text;
    @JsonProperty("date")
    Date date;

}
