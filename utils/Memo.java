public class Memo {
    int from, to;
    String message;

    Memo(int userID) { this.from = userID; }

    public void writeMemo(String message) {this.message = message;}
    public String readMemo() {return message; }

    public void setFrom(int ID) {this.from = ID;}
    public void setTo(int ID) {this.to = ID;}

    public int getFrom() {return this.from;}
    public int getTo() {return this.to;}

}
