/**
 * @file: Movie.java
 * @description: This class represents a movie object that has attributes such as
 *               rank, title, theaters, total gross, release date, and distributor.
 *               Fundamental methods are included.
 * @author: Keira Yu
 * @date: September 26, 2024
 */
public class Movie implements Comparable<Movie>{
    private int rank;
    private String title;
    private int theaters;
    private int totalGross;
    private String releaseDate;
    private String distributor;

    // Default constructor
    public Movie(){
        this.rank = 0;
        this.title = "";
        this.theaters = 0;
        this.totalGross = 0;
        this.releaseDate = "";
        this.distributor = "";
    }

    // Parametrized constructor
    public Movie(int rank, String title, int theaters, int totalGross, String releaseDate, String distributor) {
        this.rank = rank;
        this.title = title;
        this.theaters = theaters;
        this.totalGross = totalGross;
        this.releaseDate = releaseDate;
        this.distributor = distributor;
    }

    // Copy constructor
    public Movie(Movie other) {
        this.rank = other.rank;
        this.title = other.title;
        this.theaters = other.theaters;
        this.totalGross = other.totalGross;
        this.releaseDate = other.releaseDate;
        this.distributor = other.distributor;
    }

    @Override
    public String toString(){
        return rank + ". " + title + " | Theaters: " + theaters + " | Gross: $" + totalGross +
                " | Release: " + releaseDate + " | Distributor: " + distributor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return rank == movie.rank &&
                theaters == movie.theaters &&
                totalGross == movie.totalGross &&
                title.equals(movie.title) &&
                releaseDate.equals(movie.releaseDate) &&
                distributor.equals(movie.distributor);
    }

    @Override
    public int compareTo(Movie other) {
        // Compares by rank
        return Integer.compare(this.rank, other.rank);
    }

    public int getRank() {
        return rank;
    }
    public String getTitle() {
        return title;
    }
    public int getTheaters() {
        return theaters;
    }
    public long getTotalGross(){
        return totalGross;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getDistributor() {
        return distributor;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTheaters(int theaters) {
        this.theaters = theaters;
    }
    public void setTotalGross(int totalGross){
        this.totalGross = totalGross;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public void setDistributor(String distributor){
        this.distributor = distributor;
    }


}
