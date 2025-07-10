package java_concepts;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolConcept {
    public static void main(String args[]) {

        //default fork Join pool thread pool size is calculated by
        //Runtime.getRuntime().availableProcessors() – 1
        int parallelism = ForkJoinPool.commonPool().getParallelism();
        System.out.println("Default common pool parallelism: " + parallelism);
    }
}
