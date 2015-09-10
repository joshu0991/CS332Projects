package com.joshua.lilly.dnsdynamic;

import java.util.List;

/**
 * \brief Program to compare two lists and return the index, i such that b[i] is a
 * prime multiple of a[i]
 * @author Joshua Lilly, G00561467
 * 8/6/2015
 */

public class FindPrimeFactor {

	/**
	 * Method to determine if a value is prime
	 * @param value the Integer object in question
	 * @return True if the value is prime false otherwise.
	 */
    private static boolean isPrime(Integer value)
    {
        // We will start at two because obviously we can't divide by zero
        // And of course it is divisible by one.
        int counter = 2;
        while (counter <= (value.intValue())/2)
        {
            // if the mod of our value is evenly divisible by our counter
            // our number is not prime
            if (value.intValue() % counter == 0)
            {
                return false;
            }
            ++ counter;
        }
        return true;
    }

    /**
     * Method for finding if a value is divisible by another
     * @param value a The Integer to check to see if it divisible by the other
     * @param value b Integer object that is known to be prime for checking to see if an index is divisible by it
     * @return True if the value is divisible by another false otherwise.
     */
    private static boolean isDivisible(Integer valuea, Integer valueb)
    {
        if ((valuea.intValue() % valueb.intValue()) == 0)
        {
            return true;
        }
        // We didn't find a value that it is divisible by return false.
        return false;
    }
    
    /**
     * Finds the first prime factor in a list of Integers
     * @param a The list to search to see if it contains a prime factor 
     * @param b The list of integers to consider if they are prime factors
     * @return An int representing an array index or -1 if the list doesn't contain a prime factor
     *  * <dt><b>Preconditions: a not null; b not null; there exists an index in b
     *   that is a prime factor of the same index in a</b><dd>
     */
    public static int findPrimeFactor (List<Integer> a, List<Integer> b)
    {
        // we will first loop over our list b looking for prime values 
        int counter = 0;
        int value = 0;
        while (counter < b.size())
        {
            // if the value in b is prime we will try to find a value divisible by it in list a.
            // this will not evaluate is divisible if the number is not prime due to short circuit 
            // evaluation of &&
            if (isPrime(b.get(counter)) && isDivisible(a.get(counter), b.get(counter)))
            {
                // We have a prime number and it is divisible we will assign to a new variable
            	// and break from the loop. Instead of just returning the value.
                value = counter;
                break;
            }
            ++counter;
        }
        return value;
    }
}
