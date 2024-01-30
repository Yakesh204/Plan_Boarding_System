package LA5Q;

public class Stack {
    private  PassengerClass[] testArray;

    public Stack() {
        testArray = new PassengerClass[0];
    }//constructor method

    public int getSize() {//getter method
        return testArray.length;
    }

    public PassengerClass pop() {
        PassengerClass lastElement = null;//to store element getting removed
        if (testArray.length > 0) {//creating new array and moving elements then reassigned to original reference variable
            lastElement = testArray[testArray.length - 1];
            PassengerClass[] holder = new PassengerClass[testArray.length - 1];
            System.arraycopy(testArray, 0, holder, 0, testArray.length - 1);
            testArray = holder;
            holder = null;
        }
        return lastElement;
    }

    public void push(PassengerClass x) {//creating larger array and moving elements to it then adding received element to end of array
        PassengerClass[] holder = new PassengerClass[testArray.length + 1];
        System.arraycopy(testArray, 0, holder, 0, testArray.length);
        holder[holder.length - 1] = x;
        testArray = holder;//reassigning to original reference variable
        holder = null;

    }

    public PassengerClass[] getTestArray(){
        return testArray;//return current array
    }





}
