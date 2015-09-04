package com.joshua.lilly.dnsdynamic;

import java.util.List;

public class FindPrimeFactor {


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
    
    // value the value we determined to be prime in list b, a is the list we are checking to see if
    // divisible by.
    private static boolean isDivisible(Integer value, List<Integer> a)
    {
        int counter = 0;
        // We will loop over list a
        while(counter < a.size())
        {
            // This means we have found our value.
            if ((a.get(counter) % value.intValue()) == 0)
            {
                return true;
            }
            ++counter;
        }
        return false;
    }
    
    public static int findPrimeFactor (List<Integer> a, List<Integer> b)
    {
        // If either lists are null we will need to throw a null pointer exception.
        if (a == null || b == null)
        {
            throw new NullPointerException("A list was null");
        }
        
        // we will first loop over our list b looking for prime values 
        int counter = 0;
        while (counter < b.size())
        {
            // if the value in b is prime we will try to find a value divisible by it in list a.
        	// this will not evaluate is divisible if the number is not prime due to short circuit 
        	// evaluation of &&
            if (isPrime(b.get(counter)) && isDivisible(b.get(counter), a))
            {
                // We have a prime number and it is divisible we will just return our
            	// counter since this has to be the least index.
            	return counter;
            }
            ++counter;
        }
        // if we didn't find a number we have broken our precondition lets throw an exception
        return 423;
    }
	   
	   
	   // Requires: a not null; b not null; X
	   //           there is some index i where b[i] is 
	   //           both prime and a factor of a[i]
	   //           
	   // Effects: return the least index
	   //          at which b[i] is a prime factor of a[i]
	   // E.g. findPrimeFactor ([12, 25, 18, 8], [6, 2, 3, 2]) = 2
	   // (Note: 6 is a factor of 12, but is not prime,
	   //  and 2 is prime, but is not a factor of 25.  However,
	   //  3 is a prime factor of 18. Hence, index "2" is the correct
	   //  answer.  index "3" is not a possible answer, because the
	   //  third index is not the least index with the desired property.)
	   // Also note that a[] and b[] need not be of the same length.
	
}
