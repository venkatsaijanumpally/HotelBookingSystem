package com.hotel.elastichotelsyncservice.Model;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
@Document(indexName = "hotels")
public class ElasticHotel {
    private String id;
    private String name;
    private List<ElasticRoomType> roomTypeList;
    private String description;
    private String location;

    public ElasticHotel() {
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

    public List<ElasticRoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(List<ElasticRoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
