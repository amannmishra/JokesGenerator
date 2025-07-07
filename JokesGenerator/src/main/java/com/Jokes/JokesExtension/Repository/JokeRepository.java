package com.Jokes.JokesExtension.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jokes.JokesExtension.Entity.JokeEntity;
import java.util.List;

@Repository
public interface JokeRepository  extends JpaRepository <JokeEntity,Long>{
    List<JokeEntity> findByCategoryIgnoreCase(String category);

}
