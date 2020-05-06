package io.dowlath.completablefuture;

import java.util.concurrent.CompletableFuture;

// Example for CompletableFuture -> supplyAsync , runAsync
public class SupplyAsync_RunAsync {
    public static void main(String[] args) {
       // ifRunTakingSuchALongTime();
       // completableFutureSupplyAsyncBehavior();
        completableFutureRunAsyncBehavior();
    }

    public static void completableFutureSupplyAsyncBehavior(){
        System.out.println( "Starting process before supplyAsync....");
        /*
          after Completable Future nothing will print.because main thread is
           not waiting, its running. if you want to print and see make some
           waiting period for main thread.
        */
        CompletableFuture.supplyAsync(() -> longRunningProcess(5))
                .thenAccept(value -> System.out.println(value));
        // waiting period...ie call sleep
        sleepALittle();
        System.out.println( "Done process after supplyAsync....");
    }

    public static void completableFutureRunAsyncBehavior(){
        System.out.println( "Starting process before runAsync....");
        CompletableFuture.runAsync(() -> veryLongProcess(5));
        sleepALittle();
    }

    public static void ifRunTakingSuchALongTime(){
        System.out.println( "Starting process....");
        int value = longRunningProcess(5);
        System.out.println(value);
        System.out.println("Done with process....");
    }
    public static int longRunningProcess(int value){
       sleepALittle();
       return value * 10;
    }
    public static void veryLongProcess(int value){
        sleepALittle();
        System.out.println("Done with very long process - runAsync");
    }

    public static void sleepALittle(){
        try{
            Thread.sleep(3000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
