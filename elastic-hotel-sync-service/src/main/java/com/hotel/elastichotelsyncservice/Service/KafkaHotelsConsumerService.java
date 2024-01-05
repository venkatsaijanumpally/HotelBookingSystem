package com.hotel.elastichotelsyncservice.Service;

import com.hotel.elastichotelsyncservice.Model.ElasticHotel;
import com.hotel.elastichotelsyncservice.Repository.ElasticHotelRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaHotelsConsumerService {
    private final ElasticsearchService elasticsearchService;
    private final ElasticHotelRepository elasticHotelRepository;
    private final ObjectMapper objectMapper;

    public KafkaHotelsConsumerService(
            ElasticsearchService elasticsearchService,
            ElasticHotelRepository elasticHotelRepository, ObjectMapper objectMapper) {
        this.elasticsearchService = elasticsearchService;
        this.elasticHotelRepository = elasticHotelRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "hotels_topic", groupId = "hotels_group")
    public void consumeHotelMessage(String hotelJson) {
        try {
            System.out.println(hotelJson);
            ElasticHotel elasticHotel = objectMapper.readValue(hotelJson, ElasticHotel.class);
            elasticHotelRepository.save(elasticHotel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
