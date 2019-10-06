package com.exaz.hack2019.konsulta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VWESender {
    /*

    "sender": {
        "id": "jAroztUssBNJWDo5Et6FTg==",
        "name": "Jensen",
        "avatar": "https://media-direct.cdn.viber.com/download_photo?dlid=YmMjW5PXpwiRaWVKcpo7CBRsRGh2h-yZ2qAbrbOWr1yMDjloyZlbP9vOGuOUk9ev5XynfCgA_jRnwYTXu9UlLkJ1-w3HzR0mUsVdKsNpi_UWvCykf4s7_6g_j327ozCc31-k0Q&fltp=jpg&imsz=0000",
        "language": "en",
        "country": "SG",
        "api_version": 8
    },
     */

    private String id;
    private String name;
    private String avatar;
    private String language;
    private String country;
    private Integer api_version;

    public VWESender() {
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }

    public VWESender(String id, String name, String avatar, String language, String country, Integer api_version) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.language = language;
        this.country = country;
        this.api_version = api_version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getApi_version() {
        return api_version;
    }

    public void setApi_version(Integer api_version) {
        this.api_version = api_version;
    }
}
