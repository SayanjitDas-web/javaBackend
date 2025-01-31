package com.sayanjit.jobposting.repository;

import com.sayanjit.jobposting.model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);

}
