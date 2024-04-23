package com.example.pi_projet.services;

import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Post;
import com.example.pi_projet.entities.User;
import com.example.pi_projet.repositories.GroupeRepository;
import com.example.pi_projet.repositories.PostRepository;
import com.example.pi_projet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostImplementation implements IPostService{
    GroupeRepository groupeRepository;
    UserRepository userRepository;
    PostRepository postRepository;
    @Override
    public Post addPost(String contenu, Long idGroupe, Long idUser) {
        Groupe groupe = groupeRepository.findGroupesByIdGroupe(idGroupe);
        User  user = userRepository.findUserById(idUser);
        Post post= new Post();
        post.setGroupePosts(groupe);
        post.setUser_post(user);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long idPost) {
    Post post = postRepository.findPostsByIdPost(idPost);
    postRepository.delete(post);
    }

    @Override
    public Post retrieveSpecPost(Long idPost) {
        return postRepository.findById(idPost).get();
    }

    @Override
    public List<Post> retrievePosts() {
        return postRepository.findAll();
    }
}