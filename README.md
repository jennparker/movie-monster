# Movie Monster: Popular Movie Tracker
This app uses [Volley](https://github.com/google/volley) for networking, [GSON](https://github.com/google/gson) for converting JSON and [Glide](https://github.com/bumptech/glide) for image management. It uses [The Movie DB (TMDB)](https://www.themoviedb.org/) to retrieve movie data.

## Getting Started
You'll need to [register for an API key](https://developers.themoviedb.org/3/getting-started/introduction) from TMBD. 

Once you have your key, open Constants.kt and replace <em>INSERT KEY HERE<em> with your key.
  
## Movie Details
Thought it is a work in progress, this app currently takes the first 20 results of the list of movies from most popular to least popular. Title, movie poster, overview and genre (first listing only) are displayed.
