## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |     10,000,000     |    0.0168303    |
| Using ReentrantLock     |     10,000,000     |    0.7767203    |
| Syncronized method      |     10,000,000     |    0.9502126    |
| AtomicLong for total    |     10,000,000     |    0.2592778    |

## 1. Using unsynchronized counter object

1.1) the total should be zero(if correct). the total is not always the same.
	
1.2) Average runtime = (0.016089 + 0.018657 + 0.015745)/3 = 0.0168303 second
	
1.3) It may not be the same because both of the threads(addTask and substractTask) have executed at the same time, So the result of the Counter sometimes is not zero because it is changed according to which thread is done after another.

## 2. Implications for Multi-threaded Applications

How might this affect real applications?

The total money in the Bank Account can be changed when another customer deposit or withdraw from it at the same time. For example, if the account has 100 Baht at the beginning. The customer deposit 100 Baht when the Banker withdraw the money 50 Baht, the total money should be 150 but because of unsynchronized, it makes the total money be 50 Baht (assuming withdraw is done after deposit). 

## 3. Counter with ReentrantLock

3.1) the total is always zero. Set limit to 10,000,000, average runtime = (0.823072 + 0.773907 + 0.733182)/3 = 0.7767203 second
			
3.2) Because it uses ReentratLock instead of Unsynchronized counter. 
	
3.3) it doesn't let the thread update the value until the other thread is done (unlocked). I will use it when I need to lock some part in the method. 
	
3.4) Because it prevents the application to create an infinite loop. The Thread cannot update the value when it is locked and it will ask forever until it is unlocked. 

## 4. Counter with synchronized method

4.1) the total is always zero. Set limit to 10,000,000, average runtime = (0.866201 + 0.900958 + 1.083479)/3 = 0.95021267 second
			
4.2) Because it uses synchronized counter which doesn't let other Thread update value.
	
4.3) The word "synchronized" means to obtain a lock when entering the method, so only one thread can execute the method at the same time. I will use it when I have to lock the whole method.

## 5. Counter with AtomicLong
	
5.1) Because Atomic counter doesn't let other thread interrupt the running thread, so the value will not be replaced and cause error to the program.
	
5.2) They are Atomic Operation. When you want the thread to execute the method one at a time.

## 6. Analysis of Results

6.1) Comparing the average run-times, Atomic counter is the fastest and the Reentrant lock is the slowest.
	
6.2) In my opinion, I think the Reentrant lock can cause least problem of the 3 threads because when the resource is getting more complex, we can fix the code part-by-part.

## 7. Using Many Threads (optional)

