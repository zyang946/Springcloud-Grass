package com.grass.cloud.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.grass.cloud.entity.Apply;

import java.util.List;
import java.util.Optional;
public interface ApplyRepository extends CrudRepository<Apply, String>{
    
}

