Group 11  - Aaron Karper (08-915-894), Joel Krebs (09-112-996)

# Serie3 - Safety and Synchronization

## Exercise 1

**1. Provide a deﬁnition of *multiprogramming*, *multiprocessing* and *distributed processing*.**

tbd

**2. In a multiprogramming system implemented on top of a multiprocessor architecture, do we need to implement a synchronization system if we know that all the threads use their own variables and they don’t have to communicate with other threads**

I don't think so, but I'm really not sure.

**3. What are safety properties? How are they modeled in FSP?**

These are certain conditions or state transitions which are not allowed, basicially the defintion of "something bad happend". They're modelded in FSP with simple processes which either define what is allowed - and everything else is an error - or what is not allowed.

**4. Is the busy-wait mutex protocol fair? Deadlock-free? Justify your answer.**

tbd

**5. If we haveto implement a simple batchoperating system on a singleprocessor architecture would have sense to use path expressions o handle the concurrency? (Motivate your answer!!!)**

tbd

**6. What happens if you call wait or notify outside a synchronized method or block?**

This will raise an IllegalMonitorStateException. (assuming we're talking about Java here)

**7. How would you manually check a safety property?**

tbd

**8. Can you ensure safety in concurrent programs without using locks?**

tbd

**9. What is the *ﬁnite progress assumption*?**

tbd

## Exercise 2

tbd

## Exercise 3

tbd





