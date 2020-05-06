package io.dowlath.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

// completOnTimeout added this feature in java9
public class CompletableFuture_CompleteOnTimeOut_OrTimeOut {
    public static void main(String[] args) {
        System.out.println("Main start ....");
        CompletableFuture.supplyAsync(() -> getValue())
                  .completeOnTimeout(subValue(),1, TimeUnit.SECONDS)
                  .thenAccept(System.out::println);
                 // .orTimeout(1,TimeUnit.SECONDS)  // sleep on more than time out
                 //.thenAccept(System.out::println).join(); // see runtime exception
        sleepALittle();
        System.out.println("Main done sleeping....");
    }

    public static int getValue(){
        sleepALittle();
        return 5;
    }

    public static void sleepALittle(){
        try{
            Thread.sleep(5000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
    public static int subValue(){
        return 89;
    }
}
