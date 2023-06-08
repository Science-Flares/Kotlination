## Theory: Doubly linked list

##### Essentials

In a **doubly linked list**, every node stores data, a reference to the next node (*next*) and a reference to the previous node (*prev*).

<img src="https://ucarecdn.com/f7059e4b-907f-4c4d-b156-6f437ddd7588/" alt="img" style="zoom:50%;" />

The following picture demonstrates a list with four nodes that store numbers:

![img](https://ucarecdn.com/2d89276f-ab2d-4321-b444-811b337a60a6/)

As you can see, the first node doesn't have a previous node, and the last node isn't followed by another one.

It is possible to iterate over a doubly linked list in both directions starting from head or tail because every node has *prev* and *next* references. So, we don't even need to reverse it to print elements in the reverse order.

##### Operations

The basic operations of a doubly linked list are the same as for a singly linked list. In this topic, we'll consider only two operations that can be performed more efficiently in the case of a doubly linked list.

\1) Sometimes we need to add a new element before a *specified node* to which we have a reference. It can be done quite easily: we simply take the *previous node* of the *specified node* and set the *next* reference of the *previous* *node* to the new one. We also need to set both references of the new node and modify the *prev* reference of the *specified node* to refer to the new node. This operation has O(1)*O*(1) time complexity.

The following picture demonstrates adding element 48 as a new node before the read node (with 54).

![img](https://ucarecdn.com/2accf655-1f3f-4891-ae57-ecd0c127021a/)

If the list was singly linked, we would be forced to look for the previous node of the specified one, and that takes O(n)*O*(*n*) time.

\2) The operation of removing the last element is also more efficient when using doubly linked lists. All we need is to modify the next reference of the second-to-last node to nothing (null or nil, depending on the language) and reset the *tail* reference. We can access the second-to-last node through the *prev* reference of the *tail* node, to which we have a reference. This operation has O(1)*O*(1) time complexity.

In the following example, we remove the last node (with 42) from the list and reassign the tail to the new last node (54):

![img](https://ucarecdn.com/62d855f5-df20-4ab3-87b9-dc9d9f9900c4/)

In the case of a singly linked list, we don't have the *prev* reference and must look for the second-to-last node traversing the list, which takes O(n)*O*(*n*) time.

##### Conclusion

A doubly linked list is an unbounded linear data structure like a singly linked list, but it provides more flexibility and optimizes some basic operations because it uses an additional reference between the nodes. This is the main reason why this kind of list is common in standard libraries of many programming languages. But there is one drawback: storing additional references requires more space.