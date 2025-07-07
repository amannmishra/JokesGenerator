package com.Jokes.JokesExtension.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jokes.JokesExtension.Entity.JokeEntity;
import com.Jokes.JokesExtension.Service.JokeService;

@RestController
@RequestMapping("/Jokes")
@CrossOrigin(origins = "*")
public class JokeController {

    @Autowired
    private JokeService jokeService;

    // Fetch joke by category
    @GetMapping("/{category}")
    public ResponseEntity<Object> getJokes(@PathVariable String category) {
        String joke = jokeService.getRandomeJokesByCategory(category);
        
        // Check if no joke found, return an error message
        if (joke.equals("No jokes available for this category :" + category)) {
            return ResponseEntity.ok(new ErrorMessage(joke));
        }

        // Return the joke in the expected format
        return ResponseEntity.ok(new JokeResponse(joke));
    }

    // Save multiple jokes to the database
    @PostMapping("/UploadJokes")
    public ResponseEntity<String> uploadJokes(@RequestBody List<JokeEntity> jokes) {
        for (JokeEntity joke : jokes) {
            jokeService.saveJokes(joke);
        }
        return ResponseEntity.ok("Jokes Saved Successfully");
    }

    // Helper classes for response format
    public static class JokeResponse {
        private String content;

        public JokeResponse(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ErrorMessage {
        private String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
