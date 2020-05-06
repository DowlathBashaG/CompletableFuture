package io.dowlath.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture_Exception_Handle {
    public static void main(String[] args) {

      //  nothingWillPrint();  // 1
      //  exceptionThrowsIfYouUseJoin(); // 2
      //  ifErrorThrowsHandleErrorWillPrintTheValue(); // output is 100. // 3
      //  ifNoErrorGoodTrack();  // output is 1000  // 4
      //  ifErrorComesGoodTrack(); // output is 20000 // 5
      //  ifAnotherGetValueItsBlowUp(); // No output...if you want test using join(); will get RuntimeExcepton(); // 6
          getMoreInfo(); // Final Conclusion. // output : 101
    }

    public static void getMoreInfo(){
        CompletableFuture.supplyAsync(()->getValue())
                .exceptionally(throwable -> handleError(throwable))
                .thenApply(integer -> getAnotherValue(integer))
                .thenApply(integer -> integer +8)
                .thenApply(integer -> integer+2)
                .exceptionally(throwable -> 91)
                .thenApply(integer -> integer+10)
                .thenAccept(System.out::println);
    }
    public static void ifErrorThrowsHandleErrorWillPrintTheValue(){
        /*
           CompletableFuture having two tracks.
           1. Exception if throws
           2. No Exception
           In this case it is throwing (blowup) error so it will come to
           exceptionally block and giving the value 100 (ie output).
         */

        CompletableFuture.supplyAsync(() -> getValue())
                .exceptionally(throwable -> handleError(throwable))
                .thenAccept(System.out::println);
    }

    // skip exceptionally...
    public static void ifNoErrorGoodTrack(){
        CompletableFuture.supplyAsync(() -> getValueWithoutBlowUp())
                .exceptionally(throwable -> handleError(throwable))
                .thenApply(integer -> integer * 200)
                .thenAccept(System.out::println);
    }
    // getAnotherValue...its blowup. ( it means will throw exception )
    public static void ifAnotherGetValueItsBlowUp() {
        CompletableFuture.supplyAsync(() -> getValue())
                .exceptionally(throwable -> handleError(throwable))
                .thenApply(integer -> getAnotherValue())
                .thenAccept(System.out::println);
                //.thenAccept(System.out::println).join();
    }

    public static void ifErrorComesGoodTrack(){
        CompletableFuture.supplyAsync(() -> getValue())
                .exceptionally(throwable -> handleError(throwable))
                .thenApply(integer -> integer * 200)
                .thenAccept(System.out::println);
    }

    public static void nothingWillPrint(){
        CompletableFuture.supplyAsync(() -> getValue() )
                .thenAccept(System.out::println);
    }
    public static void exceptionThrowsIfYouUseJoin(){
        CompletableFuture.supplyAsync(() -> getValue() )
                .thenAccept(System.out::println).join();
    }

    public static int getValue(){
        blowUp();
        return 5;
    }

    public static int getAnotherValue(){
        blowUp();
        return 100;
    }

    public static int getAnotherValue(int value){
        blowUp();
        return value;
    }

    public static int getValueWithoutBlowUp(){
        return 5;
    }

    public static void blowUp(){
        throw new RuntimeException();
    }

    public static int handleError(Throwable throwable){
        return 100;
    }


}
