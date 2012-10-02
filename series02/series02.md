Aaron Karper, Joel Krebs (09-112-996)

# Serie 2 - Java, Concurrency, FSP, LTS

## Exercise 1

### a. What are traces, and what do they model?

Traces are possible executions paths of Finite State Processes (FSP). So they to model one specific possible execution of a process.

### b. What states can a Java thread be in?

In a starting state (no specific name), Runnable, NotRunnable and in a ended state (no specific name).

### c. How can it change state?

By either calling one of it's methods e.g. start(), sleep(), wait(), notify(), notifyAll(), yield() or run(); or due to specific events (i/o block or time elapsed after a sleep() call).

### d. What is the Runnable interface good for?

Java doesn't allow inheritence from multiple classes. Should that become necessary the interfaces overs a way out, as implementing multiple interfaces is possible.

### e. How would you specify an FSP that repeatedly performs hello, but may stop at any time?

erm...isn't that the solution already? hello can either go to hello or stop.

### f. How can concurrency invalidate a class invariant?

### g. What happens if you call wait or notify outside a synchronized method or block?

### h.

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

36 states, ??? traces

### b. Can you indicate the number of states and traces in the general case, i.e. for n steps?

(n+1)^2 states, traces, dunno, I was never good in combinatorics

## Exercise 4

    RADIO = (on -> On),
    On = 
        ( off -> RADIO 
        | reset -> On
        | scan -> Lock
        ),
    Lock = (scan -> End | reset -> On | off -> RADIO),
    End = ({scan, reset} -> On | off -> RADIO).
