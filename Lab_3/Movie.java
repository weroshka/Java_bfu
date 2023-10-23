import java.util.List;

public class Movie {
    private String movieName;
    private int year;
    private int movieDuration;
    private String genre;

    public Movie(String newMovieName, int newYear, int newMovieDuration, String newGenre){
        movieName =  newMovieName;
        year = newYear;
        movieDuration = newMovieDuration;
        genre = newGenre;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String newMovieName) {
        movieName = newMovieName;
    }
    public void setMovieYear(int newYear) {
        year = newYear;
    }
    public void setMovieGenre(String newMovieGenre) {
        genre = newMovieGenre;
    }
}
