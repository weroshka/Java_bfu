import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String cinemaName;

    private List<CinemaHall> cinemaHalls = new ArrayList(){};

    public Cinema(String newName, List<CinemaHall> newCinemaHalls){
        cinemaName = newName;
        cinemaHalls = newCinemaHalls;
    }
    public String getCinemaName(){
        return cinemaName;
    }

    public void setCinemaName(String newName){
        cinemaName = newName;
    }

    public List<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }
}
