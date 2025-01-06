package com.cxm;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Practice1 {

    public static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "异步返回";
        });
        future.thenAccept(result -> System.out.print("异步返回结果："+result));
        System.out.println("主线程运行结束");
        future.get();
    }
    public static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("打印内容：异步任务执行完成");
        }).thenAccept(result -> System.out.print("异步返回结果"+result));
        System.out.println("主线程运行结束");
        future.get();
    }
    public static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 5;
        }).thenApply(result -> {
            System.out.print("上一阶段结果："+result);
            return result + 3;
        });
        Integer finalResult = future.join();
        System.out.print("最终结果:"+finalResult);
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
          // 异步任务执行
          // 最终返回值
          System.out.println("第一阶段执行+++");
         return 5;
        }).thenRun(() -> {
          System.out.println("上一阶段结果执行完成!");
        });
    }
    public static void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 5;
        }).thenApply(result -> {
            System.out.print("上一阶段结果："+result);
            return result + 3;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 异步任务执行
            // 最终返回值
            System.out.println("第一阶段执行+++");
            return 5;
        }).thenApply(result -> {
            System.out.println("上一阶段结果执行完成!");
            return result * 2;
        });
        CompletableFuture<Integer> combinedFuture =future1.thenCombine(future2, Integer::sum);
        Integer combinedResult = combinedFuture.join();
        System.out.println("最终结果："+combinedResult);
    }
    public static void test5() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 5/0;
        }).handle((result, throwable) -> {
           if(throwable != null) {
                System.out.println(throwable.getMessage());
                // 拦截到了异常，处理异常，返回一个正常值
                return -1;
            }
           return result;
        }).thenApply(result -> {
            System.out.print("上一阶段结果："+result);
            return result + 3;
        });
        Integer finalResult = future1.join();
        System.out.println("最终结果：："+finalResult);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 异步任务执行
            // 最终返回值
            System.out.println("第一阶段执行+++");
            return 5;
        }).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            // 拦截到了异常，处理异常，返回一个正常值
            return -1;
        }).thenApply( result -> {
            // 输出前一阶段的值
            System.out.print(result);
            return result +2;
        });
        Integer finalResult2 = future2.join();
        System.out.println("最终结果2：："+finalResult2);

    }
public static void main(String[] str) throws ExecutionException, InterruptedException {
    //test1();
    //test2();
    //test3();
    //test4();
    test5();
}



}
