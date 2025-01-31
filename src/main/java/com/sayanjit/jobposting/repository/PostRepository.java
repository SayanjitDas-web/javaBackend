package com.sayanjit.jobposting.repository;

import com.sayanjit.jobposting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String>
{

}
