package abc.home.zarni.welcomevoting.model;

public class User {

    String name,isVoted,king,queen,user_name,roll;

    public User() {
    }

    public User(String name,String user_name, String isVoted, String king, String queen,String roll) {
        this.name = name;
        this.user_name = user_name;
        this.isVoted = isVoted;
        this.king = king;
        this.queen = queen;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(String isVoted) {
        this.isVoted = isVoted;
    }

    public String getKing() {
        return king;
    }

    public void setKing(String king) {
        this.king = king;
    }

    public String getQueen() {
        return queen;
    }

    public void setQueen(String queen) {
        this.queen = queen;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
