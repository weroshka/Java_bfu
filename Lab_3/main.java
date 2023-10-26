import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Scanner;


public class main {

    static private List<Cinema> cinemaData = new ArrayList<>();
    static private List<Movie> movieData = new ArrayList<>();
    static private List<Schedule> scheduleData;
    static private String admin_login = "admin";
    static private String admin_password = "admin";

    static private Scanner in = new Scanner(System.in);
    static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    static private Comparator<Schedule> dateComparator = new Comparator<Schedule>() {
        @Override
        public int compare(Schedule arr1, Schedule arr2) {
            try {
                Date date1 = dateFormat.parse(arr1.getDate());
                Date date2 = dateFormat.parse(arr2.getDate());
                return date1.compareTo(date2);
            }
            catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }
    };


    static private void printCinema() {
        for (Cinema cinema : cinemaData) {
            System.out.println(cinema.getCinemaName() + "\nHalls:");
            for (CinemaHall cinemaHall : cinema.getCinemaHalls()) {
                System.out.println(cinemaHall.getName() + " " + cinemaHall.getWidth() + "x" + cinemaHall.getLength());
            }
        }
    }

    static private boolean editCinema() {

        System.out.println("Input the name for cinema you want to edit");
        String editCinemaName = in.next();

        if (editCinemaName.isEmpty())
            return false;
        for (int i = 0; i < cinemaData.size(); i++)
        {
            if (Objects.equals(cinemaData.get(i).getCinemaName(), editCinemaName)){
                System.out.println("Do you want to change the name of cinema? Input YES or NO");
                String command_1 = in.next();
                if (command_1.equals("yes")) {
                    editCinemaName = in.next();
                    cinemaData.get(i).setCinemaName(editCinemaName);
                }
                else{
                    System.out.println("Program suggests you to change cinema halls");
                }

                System.out.println("Do you want to change the halls of cinema? Input YES or NO");
                String command_2 = in.next();
                if (command_2.equals("yes")){
                    System.out.println("Now cinema halls look like");
                    List<CinemaHall> editCinemaHalls = cinemaData.get(i).getCinemaHalls();
                    for (int t =0; t < editCinemaHalls.size(); t++)
                    {
                        System.out.println(editCinemaHalls.get(t).getName());
                        editCinemaHalls.get(t).printSeats();
                        System.out.println("Do you want to change this hall?");
                        String command_3 = in.next();
                        if (command_3.equals("yes")) {
                            System.out.println("Input the NEW WIDTH of hall you want TO EDIT");
                            int edWidth = in.nextInt();
                            editCinemaHalls.get(t).setWidth(edWidth);
                            System.out.println("Input the NEW LENGTH OF hall you want TO EDIT");
                            int edLength = in.nextInt();
                            editCinemaHalls.get(t).setLength(edLength);
                            char[][] edSeats = new char[edWidth][edLength];
                            for (int k = 0; k < edWidth; k++) {
                                for (int j = 0; j < edLength; j++) {
                                    edSeats[k][j] = 'n';
                                }
                            }
                            System.out.println("Input amount of VIP seats ");
                            int vipAmount = in.nextInt();
                            while (vipAmount > 0) {
                                System.out.println("Input number of row");
                                int row = in.nextInt();
                                System.out.println("Input seat in the row ");
                                int seatInRow = in.nextInt();
                                edSeats[seatInRow][row] = 'v';
                                vipAmount--;
                            }

                            editCinemaHalls.get(t).setSeats(edSeats);
                            editCinemaHalls.get(t).printSeats();
                        }

                    }

                }
                else {
                    System.out.println("You refused to change something");
                    return false;
                }

                return true;
            }
        }
        System.out.println("This cinema doesn`t exist");
        return false;
    }

    static private boolean addCinema(){
        System.out.println("Input name for new cinema");
        String newCinemaName = in.next();
        if(newCinemaName == "")
            return false;
        for (Cinema cinema:cinemaData) {
            if (Objects.equals(cinema.getCinemaName(), newCinemaName)) {
                System.out.println("This name exists");
                return false;
            }
        }
        List<CinemaHall> halls = new ArrayList(){};
        System.out.println("Input amount of halls");
        int amount = in.nextInt();
        while(amount>0){
            System.out.println("Input amount of seats in width ");
            int amountsWidth = in.nextInt();
            System.out.println("Input amount of seats in length ");
            int amountsLength = in.nextInt();
            char[][] seats = new char[amountsWidth][amountsLength];
            for (int i = 0; i < amountsWidth; i++) {
                for (int j = 0; j < amountsLength; j++) {
                    seats[i][j] = 'n';
                }
            }

            System.out.println("Input amount of VIP seats ");
            int vipAmount = in.nextInt();
            while (vipAmount>0){
                System.out.println("Input number of row");
                int row = in.nextInt();
                System.out.println("Input seat in the row ");
                int seatInRow = in.nextInt();
                seats[seatInRow][row] = 'v';
                vipAmount--;
            }
            amount--;
        }
        cinemaData.add(new Cinema(newCinemaName, new ArrayList<>()));
        System.out.println("Success!!!");

        return false;
    }

    static private boolean deleteCinema(){
        System.out.println("Input name for cinema to delete");
        String newCinemaName = in.next();
        if(newCinemaName == "")
            return false;
        Cinema deleteCinema;
        for (Cinema cinema:cinemaData) {
            if (Objects.equals(cinema.getCinemaName(), newCinemaName)) {
                cinemaData.removeIf(item -> item.equals(cinema));
                System.out.println("Success!!!");
                return true;
            }
        }

        System.out.println("Failed!!!");
        return false;
    }
    static private void getMovieList() {
        for (Movie movie : movieData) {
            System.out.println(movie.getMovieName());
        }
    }

    static private boolean addMovie(){
        System.out.println("Input name for new movie you want to ADD with operation end");
        String newMovieName = "", line;
        do{
            line = in.next();
            if (!Objects.equals(line, "end")){
                newMovieName += line + " " ;
            }
        }
        while (!Objects.equals(line, "end"));
        newMovieName = newMovieName.trim();

        if(newMovieName == "")
            return false;
        for (Movie movie:movieData) {
            if (Objects.equals(movie.getMovieName(), newMovieName)) {
                System.out.println("This movie exists");
                return false;
            }
        }
        System.out.println("Input YEAR of the new movie");
        int newYear = Integer.parseInt(in.next());
        System.out.println("Input DURATION of the new movie");
        int newDuration = Integer.parseInt(in.next());
        System.out.println("Input GENRE of the new movie");
        String newGenre = in.next();
        movieData.add(new Movie(newMovieName, newYear, newDuration,newGenre));
        System.out.println("Success!!!");

        return false;
    }

    static private boolean deleteMovie(){
        System.out.println("Input name for movie to DELETE with operation end");
        String movieName = "", line;
        do{
            line = in.next();
            if (!Objects.equals(line, "end")){
                movieName += line + " " ;
            }
        }
        while (!Objects.equals(line, "end"));
        movieName = movieName.trim();

        if(movieName == "")
            return false;
        Movie deleteMovie;
        for (Movie movie:movieData) {
            if (Objects.equals(movie.getMovieName(), movieName)) {
                movieData.removeIf(item -> item.equals(movie));
                System.out.println("Success!!!");
                return true;
            }
        }
        System.out.println("Failed!!!");
        return false;
    }
    static private boolean editMovie(){
        System.out.println("Input the name for movie you want to EDIT with operation end");
        String editMovieName = "", line;
        do{
            line = in.next();
            if (!Objects.equals(line, "end")){
                editMovieName += line + " " ;
            }
        }
        while (!Objects.equals(line, "end"));
        editMovieName = editMovieName.trim();
       

        if (editMovieName.isEmpty())
            return false;
        for (int i = 0; i < movieData.size(); i++)
        {
            if (Objects.equals(movieData.get(i).getMovieName(), editMovieName)){
                System.out.println("Do you want to change the NAME of movie? Input YES or NO");
                String command_1 = in.next();
                if (command_1.equals("yes")) {
                    System.out.println("Input the NEW name of film");
                    editMovieName = in.next();
                    movieData.get(i).setMovieName(editMovieName);
                }
                else{
                    System.out.println("Program suggests you to change other parametrs");
                }

                System.out.println("Do you want to change the YEAR of movie? Input YES or NO");
                String command_2 = in.next();
                if (command_2.equals("yes")) {
                    System.out.println("Input the new YEAR of film");
                    int editYear = Integer.parseInt(in.next());
                    movieData.get(i).setMovieYear(editYear);
                }
                else{
                    System.out.println("Program suggests you to change other parametrs");
                }

                System.out.println("Do you want to change the GENRE of movie? Input YES or NO");
                String command_3 = in.next();
                if (command_3.equals("yes")) {
                    System.out.println("Input the new GENRE of film");
                    String editGenre = in.next();
                    movieData.get(i).setMovieGenre(editGenre);
                }

                else{
                    System.out.println("It`s all that you can edit");
                    return false;
                }

                return true;
            }
        }
        System.out.println("This movie doesn`t exist");
        return false;
    }

    static private void getScheduleList() {
        Collections.sort(scheduleData, dateComparator);
        for (Schedule schedule : scheduleData) {
            System.out.println(schedule.getDate()+ "\n" + schedule.getTime() + "\n" + schedule.getMovie().getMovieName() + "\n" + schedule.getMovie().getMovieDuration());
            System.out.println(schedule.getCinema());
            System.out.println(schedule.getCinemaHall().getName());
            if (schedule.isFull()){
                System.out.println("All seats are booked!");
            }
            else{
                schedule.getCinemaHall().printSeats();
            }
        }
    }

    static private boolean addInSchedule(){
        System.out.println("Input the DATE of film in format yyyy.mm.dd");
        String inputDate = in.next();
        System.out.println("Input the TIME of film. For example, 9.30");
        String inputTime = in.next();
        System.out.println("Input the CINEMA for session. For example, Moonlight");
        in.nextLine();
        String inputCinema = in.nextLine().trim();

        boolean isExist = false;
        Cinema cinemaNeed = null;
        for (Cinema cinema: cinemaData){
            if (inputCinema.equals(cinema.getCinemaName())){
                isExist = true;
                cinemaNeed = cinema;
                break;
            }
        }
        if (!isExist){
            System.out.println("This cinema doesn`t exist");
            return false;
        }
        CinemaHall hallNeed = null;
        System.out.println("Input the HALL for session. For example, Moonlight");
        String inputHall = in.nextLine();
        isExist = false;
        for (CinemaHall hall : cinemaNeed.getCinemaHalls()){
            if(inputHall.equals(hall.getName())){
                isExist = true;
                hallNeed = hall;
                break;
            }
        }
        if (!isExist){
            System.out.println("This hall doesn`t exist");
            return false;
        }
        System.out.println("Input the MOVIE for session. For example, Moonlight");
        String inputMovie = in.nextLine();
        Movie movieNeed = null;
        isExist = false;
        for(Movie movie : movieData){
            if(inputMovie.equals(movie.getMovieName())){
                isExist = true;
                movieNeed = movie;
                break;
            }
        }
        if (!isExist){
            System.out.println("This movie doesn`t exist");
            return false;
        }

        boolean isAvailable = true;
        for (Schedule schedule : scheduleData) {
            if (Objects.equals(schedule.getDate(), inputDate) && Objects.equals(schedule.getTime(), inputTime)
                    && Objects.equals(schedule.getCinema(), inputCinema) && Objects.equals(schedule.getCinemaHall().getName(), inputHall)
            )
            {
                isAvailable = false;
                break;
            }
        }
        if (isAvailable){
            System.out.println("Time is available");
        }
        else{
            System.out.println("Time isn`t available already");
            return false;
        }
        Schedule scheduleNew = new Schedule(inputDate, inputTime, inputCinema, hallNeed, movieNeed, false);
        for(Schedule schedule : scheduleData){
            if(schedule == scheduleNew){
                return false;
            }
        }
        scheduleData.add(scheduleNew);
        System.out.println("Operation is successful!");
        return true;
    }

    static private boolean deleteInSchedule(){
        System.out.println("Input the DATE of film TO DELETE in format yyyy.mm.dd");
        String delDate = in.next();
        System.out.println("Input the TIME of film TO DELETE. For example, 9.30");
        String delTime = in.next();

        System.out.println("Input the CINEMA for session. For example, Moonlight");
        in.nextLine();
        String delCinema = in.nextLine().trim();

        boolean isExist = false;
        Cinema cinemaNeed = null;
        for (Cinema cinema: cinemaData){
            if (delCinema.equals(cinema.getCinemaName())){
                isExist = true;
                cinemaNeed = cinema;
                break;
            }
        }
        if (!isExist){
            System.out.println("This cinema doesn`t exist");
            return false;
        }
        System.out.println("Input the HALL for session. For example, Moonlight");
        String delHall = in.nextLine();
        isExist = false;
        for (CinemaHall hall : cinemaNeed.getCinemaHalls()){
            if(delHall.equals(hall.getName())){
                isExist = true;
                break;
            }
        }
        if (!isExist){
            System.out.println("This hall doesn`t exist");
            return false;
        }
        System.out.println("Input the MOVIE TO DELETE. For example, Moonlight");
        String delMovie = in.nextLine();
        isExist = false;
        for(Movie movie : movieData){
            if(delMovie.equals(movie.getMovieName())){
                isExist = true;
                break;
            }
        }
        if (!isExist){
            System.out.println("This movie doesn`t exist");
            return false;
        }
        for(Schedule schedule : scheduleData){
            if(Objects.equals(schedule.getDate(), delDate) && Objects.equals(schedule.getTime(), delTime)
                    && Objects.equals(schedule.getCinema(), delCinema) && Objects.equals(schedule.getCinemaHall().getName(), delHall)
                    && Objects.equals(schedule.getMovie().getMovieName(), delMovie)){
                scheduleData.remove(schedule);
                System.out.println("Operation is successful!");
                return true;
            }
        }
        return false;
    }

    static private boolean editInSchedule(){
        System.out.println("Input the DATE of film TO EDIT in format yyyy.mm.dd");
        String delDate = in.next();
        System.out.println("Input the TIME of film TO EDIT. For example, 9.30");
        String delTime = in.next();

        System.out.println("Input the CINEMA for session. For example, Moonlight");
        in.nextLine();
        String delCinema = in.nextLine().trim();

        boolean isExist = false;
        Cinema cinemaNeed = null;
        for (Cinema cinema: cinemaData){
            if (delCinema.equals(cinema.getCinemaName())){
                isExist = true;
                cinemaNeed = cinema;
                break;
            }
        }
        if (!isExist){
            System.out.println("This cinema doesn`t exist");
            return false;
        }
        System.out.println("Input the HALL for session. For example, Moonlight");
        String delHall = in.nextLine();
        isExist = false;
        for (CinemaHall hall : cinemaNeed.getCinemaHalls()){
            if(delHall.equals(hall.getName())){
                isExist = true;
                break;
            }
        }
        if (!isExist){
            System.out.println("This hall doesn`t exist");
            return false;
        }
        System.out.println("Input the MOVIE TO EDIT. For example, Moonlight");
        String delMovie = in.nextLine();
        isExist = false;
        for(Movie movie : movieData){
            if(delMovie.equals(movie.getMovieName())){
                isExist = true;
                break;
            }
        }
        if (!isExist){
            System.out.println("This movie doesn`t exist");
            return false;
        }
        for(Schedule schedule : scheduleData){
            if(Objects.equals(schedule.getDate(), delDate) && Objects.equals(schedule.getTime(), delTime)
                    && Objects.equals(schedule.getCinema(), delCinema) && Objects.equals(schedule.getCinemaHall().getName(), delHall)
                    && Objects.equals(schedule.getMovie().getMovieName(), delMovie)){
                System.out.println("Do you want to change the DATE of session? Input yes OR no");
                String command_1 = in.next();
                if (command_1.equals("yes")){
                    System.out.println("Input DATE to edit in format yyyy.mm.dd");
                    String edDate = in.next();
                    schedule.setDate(edDate);
                }
                else{
                    System.out.println("You refused to change DATE");
                    System.out.println("Program suggests you to change other parameters");
                }
                System.out.println("Do you want to change the TIME of session? Input yes OR no");
                String command_2 = in.next();
                if (command_2.equals("yes")){
                    System.out.println("Input TIME to edit in format h.mm");
                    String edTime = in.next();
                    schedule.setTime(edTime);
                }
                else{
                    System.out.println("You refused to change TIME");
                    System.out.println("Program suggests you to change other parameters");
                }
                System.out.println("Do you want to change the MOVIE for session? Input yes OR no");
                String command_3 = in.next();
                if (command_3.equals("yes")){
                    System.out.println("Input the name of MOVIE to edit");
                    String edMovie = in.next() + in.nextLine();
                    Movie movieEd = null;
                    isExist = false;
                    for(Movie movie : movieData){
                        if(edMovie.equals(movie.getMovieName())){
                            isExist = true;
                            movieEd = movie;
                            break;
                        }
                    }
                    if (!isExist){
                        System.out.println("This movie doesn`t exist");
                        return false;
                    }
                    schedule.setMovie(movieEd);
                }
                else{
                    System.out.println("You refused to change MOVIE");
                    System.out.println("Program suggests you to change other parameters");
                }
                System.out.println("Do you want to change the AVAILABILITY of seats? Input yes OR no");
                String command_4 = in.next();
                if (command_4.equals("yes")){
                    System.out.println("Avalability now: ");
                    schedule.getCinemaHall().printSeats();
                    char[][] seats = schedule.getCinemaHall().getSeats();
                    System.out.println("Input seats you want to EDIT in format \n" +
                            "'r:s:v/n/x~r:s:v/n/x~...' where \n" + "r - number of row \n" +
                            "number of seat in row \n" + "type of seats: \n v - VIP;\n n - normal;\n x - is booked\n ");
                    String[] seatsEdit = in.next().split("~");
                    for (String seat: seatsEdit){
                        // seat = "r:s:v/n/x" => paramSeat = {r,s,v/n/x}
                        String[] paramSeats = seat.split(":");
                        seats[Integer.parseInt(paramSeats[1])-1][Integer.parseInt(paramSeats[0])-1] = paramSeats[2].charAt(0);
                    }
                    schedule.getCinemaHall().setSeats(seats);
                }
                else{
                    System.out.println("You refused to change session");
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    static private boolean buyTickets(){
        System.out.println("Input the day in format yyyy.mm.dd");
        String date = in.next();
        System.out.println("Input time in format hh.mm");
        String time = in.next();
        System.out.println("Input name of the cinema");
        String cinema = in.next() + in.nextLine();
        System.out.println("Input name of the hall");
        String hall = in.nextLine();
        System.out.println("Input the name of movie");
        String movie = in.nextLine();
        for (Schedule schedule : scheduleData) {
            if (Objects.equals(schedule.getDate(), date) && Objects.equals(schedule.getTime(), time) &&
                    Objects.equals(schedule.getCinema(), cinema) &&
                    Objects.equals(schedule.getCinemaHall().getName(), hall) && Objects.equals(schedule.getMovie().getMovieName(), movie)){
                if(schedule.isFull()){
                    System.out.println("All seats are booked!!");
                }
                else{
                    schedule.getCinemaHall().printSeats();
                    System.out.println("Input the number of row");
                    int row = in.nextInt();
                    if (row-1 >= schedule.getCinemaHall().getLength()){
                        System.out.println("Row doesn`t exist!! ");
                        return false;
                    }
                    System.out.println("Input the number of seat in row");
                    int seat = in.nextInt();
                    if (seat-1 >= schedule.getCinemaHall().getWidth()){
                        System.out.println("Seat doesn`t exist!! ");
                        return false;
                    }
                    char[][] seats = schedule.getCinemaHall().getSeats();
                    if(seats[seat-1][row-1] == 'x'){
                        System.out.println("This place is booked");
                        return false;
                    }
                    else{
                        seats[seat-1][row-1] = 'x';
                        schedule.getCinemaHall().setSeats(seats);
                        boolean isFull = true;
                        for (int i = 0; i < schedule.getCinemaHall().getWidth(); i++){
                            for (int j = 0; j < schedule.getCinemaHall().getLength(); j++){
                                if (seats[i][j] != 'x'){
                                    isFull = false;
                                    break;
                                }

                            }
                            if(!isFull){
                                break;
                            }

                        }
                        schedule.setIsFull(isFull);
                        System.out.println("Congratulations! You have bought a ticket! ");
                        return true;
                    }
                }
            }

        }
        return false;
    }

    static private void admin_work(){
        while (true){
            System.out.println("Choose action from list of \"Actions\"");
            System.out.println("1) Exit");
            System.out.println("2) Get list of cinema");
            System.out.println("3) Add cinema ");
            System.out.println("4) Edit cinema");
            System.out.println("5) Delete cinema");

            System.out.println("6) Get schedule list");
            System.out.println("7) Add in schedule list");
            System.out.println("8) Delete in schedule list");
            System.out.println("9) Edit in schedule list");

            System.out.println("10) Delete movie");
            System.out.println("11) Add movie");
            System.out.println("12) Edit movie");
            System.out.println("13) Get movie list");
            int operation = in.nextInt();
            switch (operation){
                case 1: return;
                case 2:
                    printCinema();
                    break;
                case 3:
                    while (addCinema())
                    {}
                    break;
                case 4:
                    while (editCinema())
                    {}
                    break;
                case 5:
                    deleteCinema();
                    break;
                case 6:
                    getScheduleList();
                    break;
                case 7:
                    addInSchedule();
                    break;
                case 8:
                    deleteInSchedule();
                    break;
                case 9:
                    editInSchedule();
                    break;
                case 10:
                    deleteMovie();
                    break;
                case 11:
                    addMovie();
                    break;
                case 12:
                    editMovie();
                    break;
                case 13:
                    getMovieList();
                    break;
            }

        }
    }
    static private void user_work(){
        while (true){
            System.out.println("Choose action from list of \"Actions\"");
            System.out.println("1) Exit");
            System.out.println("2) Get schedule list");
            System.out.println("3) Buy tickets");

            int operation = in.nextInt();
            switch (operation){
                case 1: return;
                case 2:
                    getScheduleList();
                    break;
                case 3:
                    buyTickets();
                    break;
            }

        }
    }

    private static void getData(){
        cinemaData = ReaderData.getCinema();
        movieData = ReaderData.getMovie();
        scheduleData = ReaderData.getSchedule(movieData);

    }
    public static void main(String args[]){
        getData();

        System.out.println("Welcome to cinema \"NIKA\"\nDo you want to authorize as Admin? (Input yes\\no)");
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if(command.equals("yes")){
            System.out.println("Input admin`s login");
            String log_admin = in.nextLine();
            System.out.println("Input admin`s password");
            String pass_admin = in.nextLine();
            if (log_admin.equals(admin_login) && pass_admin.equals(admin_password)){
                admin_work();
            }
            else{
                System.out.println("ERROR! You`re not an admin!");
                return;
            }
        }
        else{
            user_work();
        }

        ReaderData.saveCinema(cinemaData);
        ReaderData.saveMovie(movieData);
        ReaderData.saveSchedule(scheduleData);
    }
}
