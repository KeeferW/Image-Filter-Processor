package model;

/**
 * Represents a function that accepts inputs and applies itself to arguments.
 *
 * @param <T> - first input
 * @param <U> - second input
 * @param <V> - third input
 * @param <R> - return type
 */
public interface Function<T, U, V, R> {
  /**
   * Applies this function.
   * @param input1 - first input
   * @param input2 - second input
   * @param input3 - third input
   */
  R apply(T input1, U input2, V input3);
}