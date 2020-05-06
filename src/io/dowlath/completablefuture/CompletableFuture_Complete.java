package io.dowlath.completablefuture;

import java.util.concurrent.CompletableFuture;

/*
    This feature will be useful for process the data from database or other networks.
*/
public class CompletableFuture_Complete {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        int value = 8;
        if( value % 2 == 0){
            getReady_1(completableFuture);
        }
        else{
            getReady_2(completableFuture);
        }
        // This will call after calling getReady method, with out getReady there is nothing.
        completableFuture.complete(value);
    }

    public static void getReady_1(CompletableFuture<Integer> future){
        System.out.println("Even.....");
        future.thenApply(integer -> integer * 5)
              .thenApply(integer -> integer + 20)
              .thenAccept(System.out::println);
    }
    public static void getReady_2(CompletableFuture<Integer> future){
        System.out.println("Odd......");
        future.thenApply(integer -> integer * 5)
                .thenApply(integer -> integer + 100)
                .thenAccept(System.out::println);
    }
}
