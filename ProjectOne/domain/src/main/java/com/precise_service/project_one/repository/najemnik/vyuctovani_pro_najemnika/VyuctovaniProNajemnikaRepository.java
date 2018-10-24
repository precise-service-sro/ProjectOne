package com.precise_service.project_one.repository.najemnik.vyuctovani_pro_najemnika;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnika;

public interface VyuctovaniProNajemnikaRepository extends MongoRepository<VyuctovaniProNajemnika, String> {

}