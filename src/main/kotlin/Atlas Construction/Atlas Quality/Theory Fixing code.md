## Theory: Fixing code

## Mistakes are an essential part of successful learning. They can also be very annoying and drain your motivation. The IDE helps you with code errors by alerting you to the most common ones and providing a debugger tool.

##### Suggested fixes

Take a look at the code example from the “Invalid declarations of the main method” above. The red squiggly line under the code means there’s something wrong. Put the cursor on the highlighted code to see what the issue is:

![img](https://i.imgur.com/7AdztEV.png)

You can use the red bulb or press `⌥ Enter` / `Alt + Enter` to see a list of suggested fixes.

Note the red icon at the top right. It shows you that there are some errors in the file. Hover over the red stripe to see the list of errors. Click on it to navigate to the line with the error and fix it:

![img](https://i.imgur.com/kdYGKif.png)

When everything is fixed, there is a green check icon in the top right corner:

![img](https://i.imgur.com/esj0wB4.png)

##### Debugger

Learning is a journey with a lot of experiments and investigations. Look under the hood of any code and see what's going on with the help of the [IntelliJ IDEA graphical debugger](https://www.jetbrains.com/help/idea/debugging-your-first-java-application.html).

![img](https://i.imgur.com/eOsuzVm.png)

Set a line breakpoint by clicking the left gutter area at a line where you want to stop, or by pressing `⌘ F8` / `Ctrl + F8`:

![img](https://i.imgur.com/IB3VWP0.png)

Now run the debugger with `⌃ D` / `Shift + F9`:

![img](https://i.imgur.com/DxgjXlI.png)

Your program will stop on the line with breakpoint:

![img](https://i.imgur.com/JJ091wa.png)

Now use `F8` to go step-by-step through your code and find all the information about what is happening in the Debug tool window:

![img](https://i.imgur.com/ONLPV1b.png)

You can also just hover over a code expression and see the evaluation result:

![img](https://i.imgur.com/XM5i8YB.png)

Or use `⌥ F8` / `Alt + F8` to open a special dialog where you can experiment with different code expressions while your program is paused:

![img](https://i.imgur.com/hwnBZHC.png)

Now you are fully equipped to fight the most severe bugs.