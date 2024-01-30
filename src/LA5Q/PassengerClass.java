package LA5Q;

public class PassengerClass implements Comparable<PassengerClass>{//implementing comparable class to make custom compareTo method
    //private variables
    private boolean disabled;
    private int seatNum;
    private String name;

    public PassengerClass( int seatNum, String name, boolean disabled){//constructor class
        this.disabled = disabled;
        this.seatNum = seatNum;
        this.name = name;
    }

    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }//setter method

    public void setName(String name){
        this.name = name;
    }//setter method

    public void setSeatNum(int seatNum){
        this.seatNum = seatNum;
    }//setter method

    public boolean getDisabled(){
        return disabled;
    }//getter method

    public int getSeatNum(){
        return seatNum;
    }//getter method

    public String getName(){
        return  name;
    }//getter method

    public String toString(){//toString method
        return String.format("Name: %s | Seat Number: %d | Disability: %B", name, seatNum, disabled);
    }

    public int compareTo(PassengerClass x){//custom compareTo method will compare seat numbers
        int value;
        if (this.seatNum > x.getSeatNum()){
            value = 1;
        }
        else if(this.seatNum < x.getSeatNum()){
            value = -1;
        }
        else{
            value = 0;
        }
        return value;
    }
}
