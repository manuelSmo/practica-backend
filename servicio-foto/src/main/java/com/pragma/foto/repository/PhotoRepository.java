package com.pragma.foto.repository;

import com.pragma.foto.entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends MongoRepository<Photo,Long> {
    Photo findByClientId(Long clientId);
    void deletePhotoByClientId(Long clientId);
}
