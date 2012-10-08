Group 11  - Aaron Karper (08-915-894), Joel Krebs (09-112-996)

# Serie3 - Safety and Synchronization

## Exercise 1

**1. Provide a deﬁnition of *multiprogramming*, *multiprocessing* and *distributed processing*.**

A multiprogramming system is a system which share it's resources for several applications.

Multiprocessing happens, when you use more than one CPU within the same system.

And in distributed system tasks are distributed not only to different processors, but to seperate systems.

**2. In a multiprogramming system implemented on top of a multiprocessor architecture, do we need to implement a synchronization system if we know that all the threads use their own variables and they don’t have to communicate with other threads**

I don't think so, but I'm really not sure.

**3. What are safety properties? How are they modeled in FSP?**

These are certain conditions or state transitions which are not allowed, basicially the defintion of "something bad happend". They're modelded in FSP with simple processes which either define what is allowed - and everything else is an error - or what is not allowed.

**4. Is the busy-wait mutex protocol fair? Deadlock-free? Justify your answer.**

It certainly is deadlock free. We can proof that with the FSP.

**5. If we have to implement a simple batchoperating system on a singleprocessor architecture would have sense to use path expressions o handle the concurrency? (Motivate your answer!!!)**

tbd

**6. What happens if you call wait or notify outside a synchronized method or block?**

This will raise an IllegalMonitorStateException. (assuming we're talking about Java here)

**7. How would you manually check a safety property?**

Try to come up with every possible state of the system and ensure, that the invariant holds. But this get's out of hands fast, so we could implement some tests which run the code under different conditions high number of times in sequence and alert as soon as the invariant is broken.

**8. Can you ensure safety in concurrent programs without using locks?**

If you can avoid shared mutable state. This is the principle of functional languages and is on the raise lately due to exactly these kinds of questions. Functional programing builds on the paradigm that you don't havy any mutable state and therefore you can not break any invariant.

**9. What is the *ﬁnite progress assumption*?**

tbd

## Exercise 2

We show that by describing possible traces of S1 and S2.

**S1**

S1 can either do *a* (due to the P process) or *c*. If it does *a*, the only thing it can do is *c* as *b* is a resource shared with Q. If it has done *c* first it can only do *a* due to the same reason. Afterwards *b* is the only possibility afterwards. And then we're at the start again.

So possible traces are:

    {(a,c,b|c,a,b)*}

**S2**

It is quite obvious that we come to the same traces here. Either we do `a->c->b->S2` or `c->a->b->S2` all over again. So we have the same trace:

    {(a,c,b|c,a,b)*}

## Exercise 3

    TABLE = (pickup->putdown->TABLE).
    GUN = (trigger->bang->STOP|bang->STOP).
    PLAYER = (pickup->trigger->putdown->PLAYER).
    ||Roulette = ({m,c}::PLAYER || TABLE || GUN).





