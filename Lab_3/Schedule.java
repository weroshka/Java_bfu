import java.util.List;

public class Schedule {
    private String date;
    private String time;
    private String cinema;
    private CinemaHall cinemaHall;
    private Movie movie;
    private boolean isFull;

    public Schedule(String newDate, String newTime, String newCinema, CinemaHall newCinemaHall, Movie newMovie, boolean newIsFull){

        date = newDate;
        time = newTime;
        cinema = newCinema;
        cinemaHall = newCinemaHall;
        movie = newMovie;
        isFull = newIsFull;

    }

    public String getCinema() {
        return cinema;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isFull() {
        return isFull;
    }
}
