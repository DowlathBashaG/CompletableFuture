package io.dowlath.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFuture_ThenCombine_ThenCompose {
    public static void main(String[] args) {
        int userId = 5;

        //getValue1Async(userId).thenCombine(getValue2Async(userId),(a,b) -> a+b)
         //       .thenAccept(integer -> System.out.println(integer));
       // sleep();  // output: 70

        getValue1Async(userId).thenCompose(CompletableFuture_ThenCombine_ThenCompose::getValue2Async)
                .thenAccept(System.out::println);
        longSleep();  // output : 200 , why long sleep...
                      // bcoz mainthread completes before this process, so extended the time

    }

    public static CompletableFuture<Integer> getValue1Async(int value){
        return CompletableFuture.supplyAsync(() -> process_1(value));
    }
    public static CompletableFuture<Integer> getValue2Async(int value){
        return CompletableFuture.supplyAsync(() -> process_2(value));
    }

    public static int process_1(int x){
        sleep();
        return x * 4;
    }
    public static int process_2(int x){
        sleep();
        return x * 10;
    }

    public static void sleep(){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void longSleep(){
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
