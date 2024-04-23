package com.example.pi_projet.repositories;

import com.example.pi_projet.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post findPostsByIdPost(Long idPost);
}
