package edu.algo.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * https://medium.com/unibench/java-hack-sneaky-throws-explained-88f445ed983b
 * <p>
 * <p>
 * Checked exceptions are part of Java(i.e. compiler), not the JVM. In the
 * bytecode, we can throw any exception from anywhere, without restrictions.
 * <p>
 * <p>
 * Sneaky throws is a technique that lets you throw checked exceptions without
 * declaring them or catching them.
 * <p>
 * Traditionally, this was done via a helper method.
 * <p>
 * Java 8 brought a new type inference rule that states that a throws T is
 * inferred as RuntimeException whenever allowed. This gives the ability to
 * implement sneaky throws without the helper method.
 */
public class SneakyException {

    public static final class WrappingCheckedException1 {

        public static void main(String[] args) {

            try {
                Files.readAllLines(Path.of("test.txt")).forEach(System.out::println);
            } catch (IOException e) {
                // wrapping the checked exception in unchecked exception.
                throw new RuntimeException(e);
            }

        }

    }

    public static final class SneakyThrowsTraditional2 {

        public static void main(String[] args) {

            try {
                Files.readAllLines(Path.of("test.txt")).forEach(System.out::println);
            } catch (IOException e) {
                // wrapping the checked exception in unchecked exception.
                sneakyThrowsHelper(e);
            }

        }

        // sneakyThrows with helper
        public static void sneakyThrowsHelper(Throwable t) {
            SneakyThrowsTraditional2.<RuntimeException>sneakyThrows(t);
        }

        @SuppressWarnings("unchecked")
        private static <T extends Throwable> void sneakyThrows(Throwable t) throws T {
            throw (T) t;
        }

    }

    public static final class SneakyThrowsJava8OnWards3 {

        public static void main(String[] args) {
            try {
                Files.readAllLines(Path.of("test.txt")).forEach(System.out::println);
            } catch (IOException e) {
                sneakyThrows(e);
            }
        }

        // SneakyThrows without helper
        @SuppressWarnings("unchecked")
        public static <E extends Throwable> void sneakyThrows(Throwable e) throws E {
            throw (E) e;
        }
    }

}