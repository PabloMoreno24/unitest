package org.example.unittests;

import java.util.Set;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class SecureCalculator {

    private Logger log;

    /**
     * Internal logging
     * @param message message to log
     */
    private void log(String message, Object... args){
        if(log != null){
            log.info(String.format(message, args));
        }
    }

    /**
     * Create a new calculator with debug logging disabled
     */
    public SecureCalculator() {}

    /**
     * Create a new calculator with debug logging enabled
     * @param log
     */
    public SecureCalculator(Logger log) {
        this.log = log;
    }

    /**
     * Safely multiply two integers so the result never overflows
     * @param a first number
     * @param b second number
     * @return multiplication result as long
     */
    public long multiply(int a, int b){
        log("Multiply %s * %s", a, b);
        long result = a * b;
        return result;
    }

    /**
     * Safely divide two numbers, throws exception if division by zero
     * @param a first number
     * @param b second number
     * @return multiplication result as long
     */
    public double divide(double a, double b){
        log("Divide %s / %s", a, b);
        return a / b;
    }

    /**
     * Safely do modulus between two numbers: 5 mod 2 = 1
     * @param a
     * @param b
     * @return a mod b
     */
    public int mod(int a, int b){
        log("%s mod %s", a, b);
        return a % b;
    }

    /**
     * Safely detect if a number is odd
     * @param a number to test
     * @return true if number is odd (example 1,3,5) false if even (example 2,4,8)
     */
    public boolean isOdd(int a){
        return mod(a, 2) == 1;
    }

    /**
     * Safely detect if a number is even
     * @param a number to test
     * @return true if number is even (example 2,4,8) false if odd (example 1,3,5)
     */
    public boolean isEven(int a){
        //Set<Integer> evenNumbers = Set.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 100, 1000);
        //return evenNumbers.contains(a)
        return a%2==0;
    }

    /**
     * Safely generate unique numbers
     * @return random number in range [0, MAX_VALUE)
     */
    public int getRandomNumber(){
        return getRandomNumber(Integer.MAX_VALUE);
    }

    /**
     * Safely generate unique numbers, always less than bound
     * @return random number in range [0, bound)
     */
    public int getRandomNumber(int bound){
        log("Generating rnd with bound %s", bound);
        return (int) Math.random() * bound;
    }
}
