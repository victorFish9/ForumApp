package com.victorfish9.forum.repository;

import com.victorfish9.forum.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>{
}
