import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReaderData {
    public static List<Cinema> getCinema(){
        List<Cinema> cinemaData = new ArrayList<>();
        try {
            File file = new File("data/cinema.txt");
            FileReader dataReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(dataReader);
            String line = buffer.readLine();
            String cinemaName = "";
            List<CinemaHall> cinemaHalls = new ArrayList<>();

            while (line != null) {
                if(Objects.equals(line.split(" ")[0], "cinema"))
                {
                    cinemaName = line.split(" ")[1];
                    cinemaHalls = new ArrayList<>();
                }
                else if(Objects.equals(line.split(" ")[0], "hall"))
                {
                    String hallName = line.split(" ")[1];
                    line = buffer.readLine();
                    int width = Integer.parseInt(line.split(" ")[0]);
                    int length = Integer.parseInt(line.split(" ")[1]);
                    char[][] seats = new char[width][length];

                    for (int i = 0; i < length; i++) {
                        String[] seatLine = buffer.readLine().split(" ");
                        for (int j = 0; j < width; j++) {
                            seats[j][i] = seatLine[j].charAt(0);
                        }
                    }
                    cinemaHalls.add(new CinemaHall(hallName,width,length,seats));
                }
                else if(Objects.equals(line.split(" ")[0], "end")){
                    cinemaData.add(new Cinema(cinemaName,cinemaHalls));
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cinemaData;
    }
    public static List<Movie> getMovie(){
        List<Movie> movieData = new ArrayList<>();
        try {
            File file = new File("data/movie.txt");
            FileReader dataReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(dataReader);
            String line = buffer.readLine();
            while (line != null && !line.isEmpty()) {
                String movieName = line;
                int movieYear = Integer.parseInt(buffer.readLine());
                int movieDuration = Integer.parseInt(buffer.readLine());
                String movieGenre = buffer.readLine();
                movieData.add(new Movie(movieName, movieYear, movieDuration, movieGenre));

                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieData;
    }

    public static List<Schedule> getSchedule(List<Movie> movieData){
        List<Schedule> scheduleData = new ArrayList<>();
        try {
            File file = new File("data/schedule.txt");
            FileReader dataReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(dataReader);
            String line = "tmp";
            while (!Objects.equals(line, "endFile") && !line.isEmpty()) {
                String date = buffer.readLine();
                String time = buffer.readLine();
                String cinemaName = buffer.readLine();
                String hallName = buffer.readLine();
                line = buffer.readLine();
                int width = Integer.parseInt(line.split(" ")[0]);
                int length = Integer.parseInt(line.split(" ")[1]);
                char[][] seats = new char[width][length];
                for (int i = 0; i < length; i++) {
                    String[] seatsInRow = buffer.readLine().split(" ");
                    for (int j = 0; j < width; j++) {
                        seats[j][i] = seatsInRow[j].charAt(0);
                    }
                }
                String filmName = buffer.readLine();
                int isFull = Integer.parseInt(buffer.readLine());
                line = buffer.readLine();
                Movie movie = null;
                for (Movie value: movieData) {
                    if(value.getMovieName().equals(filmName)){
                        movie = value;
                        break;
                    }
                }
                scheduleData.add(new Schedule(date, time, cinemaName, new CinemaHall(hallName, width, length, seats), movie, isFull>0));

            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return scheduleData;
    }

    public static void saveCinema(List<Cinema> cinemaData){
        try {
            FileWriter fileWriter = new FileWriter("data/cinema.txt", false);

            String newContent = "";
            for (Cinema cinema : cinemaData) {
                if(!newContent.isEmpty())
                    newContent += "end\n";
                newContent += "cinema " + cinema.getCinemaName() + "\n";
                for (CinemaHall cinemaHall: cinema.getCinemaHalls()) {
                    newContent += "hall " + cinemaHall.getName() + "\n";
                    newContent += Integer.toString(cinemaHall.getWidth()) + " " + Integer.toString(cinemaHall.getLength())  + "\n";
                    char[][] data = cinemaHall.getSeats();
                    for (int i = 0; i < cinemaHall.getLength(); i++) {
                        for (int j = 0; j < cinemaHall.getWidth(); j++) {
                            newContent += data[j][i] + " ";
                        }
                        newContent += "\n";
                    }
                }
            }
            fileWriter.write(newContent+"\nend");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMovie(List<Movie> movieData){
        try {
            FileWriter fileWriter = new FileWriter("data/movie.txt", false);
            String newContent = "";
            for (Movie movie : movieData) {
                newContent += movie.getMovieName() + "\n";
                newContent += movie.getYear() + "\n";
                newContent += movie.getMovieDuration()+ "\n";
                newContent += movie.getGenre()+ "\n";

            }
            fileWriter.write(newContent.trim());
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSchedule(List<Schedule> scheduleData){
        try {
            FileWriter fileWriter = new FileWriter("data/schedule.txt", false);
            String newContent = "";
            for (Schedule schedule : scheduleData) {
                newContent += schedule.getDate() + "\n";
                newContent += schedule.getTime() + "\n";
                newContent += schedule.getCinema()+ "\n";
                newContent += schedule.getCinemaHall().getName()+ "\n";
                char[][] data = schedule.getCinemaHall().getSeats();
                for (int i = 0; i < schedule.getCinemaHall().getLength(); i++) {
                    for (int j = 0; j < schedule.getCinemaHall().getWidth(); j++) {
                        newContent += data[j][i] + " ";
                    }
                    newContent += "\n";
                }
                newContent += schedule.getMovie().getMovieName() +"\n";
                newContent += (schedule.isFull())?"1\n":"0\n";

            }
            fileWriter.write(newContent.trim());
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
