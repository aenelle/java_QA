package ru.stqa.rft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPrimes()  {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimes()  {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }
  @Test
  public void testSecondPrimes()  {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 7));
  }
  @Test(enabled = false)
  public void testPrimesLong()  {
  long n = Integer.MAX_VALUE;
  Assert.assertTrue(Primes.isPrime(n));
  }
}

