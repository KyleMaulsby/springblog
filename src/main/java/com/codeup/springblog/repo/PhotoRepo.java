package com.codeup.springblog.repo;

import com.codeup.springblog.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepo extends CrudRepository<Photo, Long> {
    Iterable<Photo> findByPost_Id(long post_id);
}
