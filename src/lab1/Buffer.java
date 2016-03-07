package lab1;

/**
 * Created by Mateusz on 07/03/2016.
 */
public class Buffer {
    String message = null;
    private int i = 1;

    public synchronized void put(String message){
        this.message = message;
    }
    public synchronized String take(){
        return message;
    }

    public int getMessageNo(){
        return i;
    }

    public void incMessageNo(){
        i++;
    }

}

