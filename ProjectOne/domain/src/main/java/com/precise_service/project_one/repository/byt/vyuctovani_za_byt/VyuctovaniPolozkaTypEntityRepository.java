package com.precise_service.project_one.repository.byt.vyuctovani_za_byt;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;

public interface VyuctovaniPolozkaTypEntityRepository extends MongoRepository<VyuctovaniPolozkaTypEntity, String> {

}