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

1.1) The total should be zero(if correct) and it is not always the same.
	
1.2) Average runtime = (0.016089 + 0.018657 + 0.015745)/3 = 0.0168303 second
	
1.3) It may not be the same because both of the threads(addTask and substractTask) have executed at the same time, So the result of the Counter sometimes is not zero because it is changed according to which thread is done after another.

## 2. Implications for Multi-threaded Applications

How might this affect real applications?

- The total money in the Bank Account can be changed when another customer deposit or withdraw from it at the same time. For example, if the account has 100 Baht at the beginning. The customer deposit 100 Baht when the Banker withdraw the money 50 Baht, the total money should be 150 but because of using unsynchronized counter, it makes the total money be 50 Baht (assuming withdraw is done after deposit). 

## 3. Counter with ReentrantLock

3.1) The total is always zero and the average runtime = (0.823072 + 0.773907 + 0.733182)/3 = 0.7767203 second
			
3.2) Because the running thread is not interrupted by other thread. 
	
3.3) Reentrant lock doesn't let the thread update the value until the running thread is done (unlocked). I will use it when I need to lock some part in the method. 
	
3.4) Because it prevents the application to create an infinite loop. The Thread cannot update the value when it is locked and it will ask forever until it is unlocked. 

## 4. Counter with synchronized method

4.1) The total is always zero and average runtime = (0.866201 + 0.900958 + 1.083479)/3 = 0.95021267 second
			
4.2) Because it uses synchronized counter which doesn't let other thread update value.
	
4.3) The word "synchronized" means to obtain a lock when entering the method, so only one thread can execute the method at the same time. I will use it when I have to lock the whole method.

## 5. Counter with AtomicLong
	
5.1) Because Atomic counter doesn't let other thread interrupt the running thread, so the value will not be replaced or causing the error to the program.
	
5.2) They are called Atomic Operation, which is very fast and accurate when the code is not complicated. I use it when I want to compute easy Mathematical function, because I can get the solution very fast.

## 6. Analysis of Results

6.1) Comparing the average run-times, Atomic counter is the fastest and the Reentrant lock is the slowest.
	
6.2) In my opinion, I think that the Reentrant lock cause less problem than others because when the resource is getting more complex, we can easily spot the error and fix it part-by-part.

## 7. Using Many Threads (optional)

