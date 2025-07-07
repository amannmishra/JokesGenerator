document.getElementById('getJokeBtn').addEventListener('click', function () {
  const category = document.getElementById('categorySelect').value;

  fetch(`http://localhost:8080/Jokes/${category}`)
    .then(response => response.json())
    .then(data => {
      if (data.content) {
        document.getElementById('jokeDisplay').textContent = data.content;
      } else if (data.message) {
        document.getElementById('jokeDisplay').textContent = data.message;
      }
    })
    .catch(error => {
      console.error('Error fetching joke:', error);
      document.getElementById('jokeDisplay').textContent = "Error fetching joke.";
    });
});
