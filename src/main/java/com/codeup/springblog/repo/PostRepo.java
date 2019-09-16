package com.codeup.springblog.repo;

import com.codeup.springblog.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

}
