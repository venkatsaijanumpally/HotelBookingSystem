package com.hotel.elastichotelsyncservice.Repository;

import com.hotel.elastichotelsyncservice.Model.ElasticHotel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticHotelRepository extends ElasticsearchRepository<ElasticHotel, String> {
}
