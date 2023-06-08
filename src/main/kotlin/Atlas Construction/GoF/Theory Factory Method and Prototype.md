## Theory: Factory Method and Prototype

The main goals of creational design patterns are making a more reusable code and defining separate object creation methods. The most obvious examples of how these goals are realized are the factory method and prototype.

##### What is a factory method?

**Factory method** is a creational design pattern that describes how you can, through interface or abstract class, make a method to create objects which could be overridden in subclasses. It's one of the basic patterns that describe how to solve recurring design problems to write more flexible and reusable code.

It's relatively easy to describe what the factory method is supposed to do. It solves the following two problems:

- Defining a separate object creation method in the form of a factory
- Allowing subclasses to call factory for object creation

As an example, let's try to apply the factory method to a simple task. You have to make a factory method that creates different types of birds. All of them will be the birds created from a common template, but different variations will have different functions. Let's try to visualize how it will look in form of a class diagram:

<img src="https://ucarecdn.com/fd92e624-59ec-4da5-ae11-4feb5e1c139d/" alt="img" style="zoom: 50%;" />

We have an interface called *bird* which can be used to describe the common functions of all birds. Through this interface we can 'make' different types of birds. In this picture, we described duck, pigeon, and chicken. They all share the same functions they inherited from a *bird* interface, but they also added new methods.

##### When we can apply the factory method?

There are few situations where you can use this pattern:

1. It's useful when you don't know the exact types of objects your code should work with. If your application has to expand the types of objects it works with, it will be easier with the factory method. You will only need to create a new factory subclass that will override factory method in it.
2. A lot of frameworks and libraries are made with the factory method in mind, which allows you to modify them in your code. So if you are writing a framework that in your plans will be used by other people, you could apply the factory method making possible the extensions of your code.
3. This pattern could also be used when you want to save system resources by reusing existing objects instead of rebuilding new ones each time.

But applying the factory method isn't the ultimate way to use patterns. Its main disadvantage is a more complicated code that consists of a lot of subclasses needed to implement it. There is another creational design pattern that excludes this problem and could seem somewhat similar to the factory method.

##### Prototype pattern

**Prototype** is a creational design pattern that is based on the concept of copying an existing object to create a new one. This pattern delegates the copying process to the object that is being copied. It commonly uses an interface that contains the `clone()` method. Let's return to our bird creation example. For this example class diagram will look like this:

<img src="https://ucarecdn.com/667136a9-7411-484e-98da-f704b9cbd072/" alt="img" style="zoom:50%;" />

Here we have a prototype interface that contains the `clone()` method. This method is implemented in our prototype classes. They create objects and then copy them through our `clone()` method.

To explain the difference between factory method and prototype, let's use pseudocode:

```no-highlight
// fabric method
pigeon = Pigeon()
bird = pigeon as Bird
bird.makEggs()

// prototype when we have an existing pigeon instance
prototype = pigeon as Prototype
newPigeon = prototype.clone()
newPigeon.fly()
```

As you can see, in the factory method we call our Bird interface using different classes to create objects. Then we call object methods. In the prototype, we create an object by copying one that already exists. You can use both of these patterns together.

##### When we can apply the prototype?

This pattern could be used in two situations:

1. When your code shouldn't depend on a class that creates prototype objects. This can occur when you are working with objects which were passed to you from a 3rd-party code via some interface. In this case, you simply can't rely on them, because these classes are unknown. Prototype pattern allows working around these classes by providing an interface that works with any object that supports the copying process.
2. When you want to reduce the number of subclasses that only differ by the way they initialize objects. The pattern lets you use a set of pre-built objects, configured in various ways, as prototypes.

It's a more flexible pattern than the factory method, but it can be more complicated to implement. Especially with more complex objects with circular references.

##### Conclusion

Factory method and prototype pattern are relatively simple types of creational design patterns. They allow you to write more reusable and clean code with ease. But you need to keep in mind special cases in which these patterns should be implemented, to avoid unnecessarily complicating your work.