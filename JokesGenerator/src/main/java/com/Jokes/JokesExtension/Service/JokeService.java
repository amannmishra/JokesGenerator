package com.Jokes.JokesExtension.Service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jokes.JokesExtension.Entity.JokeEntity;
import com.Jokes.JokesExtension.Repository.JokeRepository;
@Service
public class JokeService {
    @Autowired
    private JokeRepository jokeRepository;

    public String getRandomeJokesByCategory(String category){
        List<JokeEntity> jokes=jokeRepository.findByCategoryIgnoreCase(category);
        if(jokes.isEmpty()){
            return "No jokes available for this category :"+category;

        }
        int randomIndex=new Random().nextInt(jokes.size());
        return jokes.get(randomIndex).getJoke();
    }

    public void saveJokes(JokeEntity joke){
        jokeRepository.save(joke);
    }
    


}
