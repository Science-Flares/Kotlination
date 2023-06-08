## Theory: Queue

##### Essentials

A **queue** is a fundamental data structure with two basic operations: **enqueue** which inserts an element and **dequeue** which removes an element. Elements are inserted to the end of a queue and removed from the top of it. This rule is known as **First In First Out**, or **FIFO** (or, alternatively, **Last In Last Out**, **LILO**).

The following image demonstrates how a queue works:

<img src="https://ucarecdn.com/971a7192-5424-4cfe-9fd2-047618967fb9/" alt="img" style="zoom:33%;" />

Here, element **1** was added before any other, and hence it will be dequeued first. At the same time, element **4** was added last and it is last to be dequeued.

##### Real queues, virtual queues

Even if you do opt for an easier word "line", you still probably know what a queue is: according to the Cambridge Dictionary, it is "a row of people waiting for something, one behind the other". Let's imagine a queue as a line of people in a movie theatre. The first one in line is the first one to enter the theater: first come first served, as we sometimes say in life. This is exactly how the FIFO principle works when we're speaking about queues in programming.

<img src="https://ucarecdn.com/0a761e30-d73c-4d17-bc89-d3d7909c1125/" alt="img" style="zoom: 33%;" />

As you see, by analogy, queues are often used in programming. The operating system on your computer, for instance, uses a queue to store keystroke data as you type on the keyboard. Recall typing something in a text editor while the computer is busy doing another task: the keystrokes still aren't lost. This happens because the system stores them in queue order until they can be processed.

##### Complexity

In the case of using a linked list or a classic array (non-resizable) as an internal structure, both **enqueue** and **dequeue** operations always take constant **O(1)** time. It does not depend on how many elements are in the queue, therefore the operations are very quick.