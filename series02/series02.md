Aaron Karper, Joel Krebs (09-112-996)

# Serie 2 - Java, Concurrency, FSP, LTS

## Exercise 1

### a. What are traces, and what do they model?

Traces are possible executions paths of Finite State Processes (FSP). So they to model one specific possible execution of a process.

### b. What states can a Java thread be in?

In a starting state (no specific name), Runnable, NotRunnable and in a ended state (no specific name).

### c. How can it change state?

By either calling one of it's methods e.g. `start()`, `sleep()`, `wait()`, `notify()`,
`notifyAll()`, `yield()` or `run()`; or due to specific events (i/o block or time
elapsed after a `sleep()` call, end of the `run()` method).

### d. What is the Runnable interface good for?

Java doesn't allow inheritence from multiple classes. Should that become
necessary the interfaces overs a way out, as implementing multiple interfaces
is possible.

### e. How would you specify an FSP that repeatedly performs hello, but may stop at any time?

erm...isn't that the solution already? hello can either go to hello or stop.
Yep, I can't see either how that wouldn't work.

### f. How can concurrency invalidate a class invariant?
The invariant of a doubly-linked list states that the forward and backward
links have to match in conclusive items. During the update however, first
either the back link or the forward link is updated, leaving the class in an
invalid state. Normally, this is not a problem, but a second thread could now
use that list and finding the object in an unexpected state, causing it to
fail.

### g. What happens if you call wait or notify outside a synchronized method or block?
Usually, there is some condition checked before the `wait` call, e.g. 
```
push(Item i){
	while (full())
		wait();
  ...
}

pop() {
	...
  notify();
  ...
}
```

now we might end up with these traces:

```
Thread A        | Thread B
enter push      |
full() is true  |
                | runs pop
                | notifies waiting threads
hits wait()     |
```

now Thread A is locked until someone else pops something again.

### h. When is it better to use synchronized blocks rather than methods?
If we can only lock on a member of ours instead of the whole object. Or of
course if we only need to lock part of a method.

## Exercise 2

    APPOINTMENT = (hello -> converse -> goodbye -> STOP).

    HOLIDAY = (arrive -> relax -> HOLIDAY).
    
    SPEED = (on -> On), 
    On = (off -> SPEED | speed -> On).
    
    LEFTONCE = (ahead -> (right -> LEFTONCE | left -> STOP)).
    
    TREBLE = 
        ( in[1] -> out[3] -> TREBLE
        | in[2] -> out[6] -> TREBLE
        | in[3] -> out[9] -> TREBLE
        ).
    
    TICK (N=5) = TICK[0],
    TICK[i:0..N] = ( when(i<N) tick -> TICK[i+1] ).
    
    PERSON = (workday -> sleep -> work -> PERSON | holiday -> sleep -> {play,shop} -> PERSON ).
    
## Exercise 3

### a. How many states and how many possible traces does it have?

`6*6 = 36` states. For the traces:

The race will take `5+5=10` transitions. Out of these 10 transitions there are
5 done by the first opponent at some time. These times can be chosen in 
`10 choose 5 = 10!/(5!²) = 252` ways, which is therefore the number of possible
traces.

### b. Can you indicate the number of states and traces in the general case, i.e. for n steps?

`(n+1)²` states, `2n choose n` traces.

## Exercise 4

    RADIO = (on -> On),
    On = 
        ( off -> RADIO 
        | reset -> On
        | scan -> Lock
        ),
    Lock = (scan -> End | reset -> On | off -> RADIO),
    End = ({scan, reset} -> On | off -> RADIO).
