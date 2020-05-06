package io.dowlath.completablefuture;

import java.util.concurrent.CompletableFuture;

public class SupplySync_Then_Apply_Accept_Run {

    public static void main(String[] args) {
         /*
             How it works (thenAccept,thenApply).
             Here first,
              1 . supplySync will process the value with 7, and
              2.  accepting with the value 7 ,
              3.  apply the value , in that case it will perform some operations.
                  based on operation if it is even will add with 70(this came after executing thenAccept) + 1,
              4.  else, if it is odd will add the value +3 , ie 71 + 3 = 74 is the o/p.
              5.  then it will run the very long process twice.
         */
        CompletableFuture.supplyAsync(() -> longRunningProcess(7))
                .thenApply(SupplySync_Then_Apply_Accept_Run::peformSomeOperations)
                .thenApply(SupplySync_Then_Apply_Accept_Run::peformSomeOperations)
                .thenAccept(System.out::println)
                .thenRun(SupplySync_Then_Apply_Accept_Run::longProcess)
                .thenRun(SupplySync_Then_Apply_Accept_Run::longProcess);
       /*
          with this sleepAWhile run wont execute bcoz main method is
          not waiting, its suddenly up. here waiting time is 3000 (3 secs)
          so increase with sleepLongWhile with 10 secs.so i m commenting now for while.

        */

       //sleepAWhile();

        sleepLongWhile();


    }

    public static int longRunningProcess(int value){
        System.out.println(Thread.currentThread().getName());
        sleepAWhile();
        return value * 10;
    }

    public static void longProcess(){
        sleepAWhile();
        System.out.println("Done with very long process....");
    }

    public static void sleepAWhile(){
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void sleepLongWhile(){
        try{
            Thread.sleep(10000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static int peformSomeOperations(int value){
        if( value % 2 == 0){
            value = value+1;
        }
        else{
            value = value+3;
        }
        return value;
    }
}
