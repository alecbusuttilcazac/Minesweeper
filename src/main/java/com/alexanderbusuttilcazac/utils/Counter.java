package com.alexanderbusuttilcazac.utils;

public class Counter{
    String countingText;
    String finishedText;
    double step;
    double count = 0.0;
    
    public class CounterException extends Exception{
        public CounterException (String message){
            super(message);
        }
    }
    
    // Console counter
    public Counter(String countingText, String finishedText, int step){
        this.countingText = countingText;
        this.finishedText = finishedText;
        this.step = step;
    }
    
    public void increment(){
        // if(count-1 == step) throw new CounterException("CounterException: Overstepped limit of " + step + " steps.");
        if(count+1 == step){
            System.out.print("\033[1F\33[K"); // Clear previous line in console
            System.out.println(finishedText);
        }else{
            System.out.print("\033[1F\33[K"); // Clear previous line in console
            System.out.println(
                countingText +
                "[" +
                String.format("%2.0f", ((100 / (step-1)) * count)) +
                "%] "
            );
            count++;
        }
    }
}
