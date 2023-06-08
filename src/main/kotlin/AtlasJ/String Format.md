String formatting
Theory
29 minutes reading
Kotlin String templates are a powerful tool for formatting strings. However, they have limitations, especially when one wants to print numerical values. Here, the String format() member function comes into play, which provides much more flexibility.

Basic syntax and the general string specifier
In Kotlin, the String.format() method returns a string formatted using a format string and a number of arguments. The format string defines how the arguments form the resulting string. As an example, let's create a string and print it:

val string = String.format("%s %s", "Hello", "World")
println(string)
//Hello World
The "%s %s" string is the format string: it defines how the arguments "Hello" and "World" are going to be formatted. The %s (or %S) denotes a format specifier that stands for every string argument. Each one of these specifiers is replaced by the following string arguments respectively (see the image below). In the above example, each argument occupies the space according to its length, and the arguments are separated by a space.



An alternative syntax with the same result is:

val string = "%s %s".format("Hello", "World")
println(string)
//Hello World
In order to print all string characters in the upper case, we can use the format specifier %S, like in the following example:

val string = String.format("%S %S", "Hello", "World")
println(string)
//HELLO WORLD
In case the syntax of a format string is incorrect, an IllegalFormatException is thrown.

Special format specifiers
Besides format specifiers, a format string can contain any text. Also, apart from the specifiers for various argument types, there are some special specifiers. Thus, %% inserts the % sign, while %n inserts a newline. For example, look at the following code:

println(String.format("The percentage of%nthis amount is 30%%.\nIt is easy to remember."))
It produces:

The percentage of
this amount is 30%.
It is easy to remember.
Note that %n may be interpreted as either \r\n or \n depending on the operating system, so it may be a better idea to use \n for more predictable behavior.

Width and justification
The %s specifier can be modified in order to define the space an argument can occupy and its alignment. If N is a positive integer, then %Ns denotes that the argument should occupy the space of N characters (width indicator). In case N is smaller than the string length, the string will occupy the space equal to its length (it is not truncated). By default, a string is right-aligned within its available space. For example, see the following code:

val str = "string"
for (n in 1..15) println("%${n}s".format(str))
It produces:

string
string
string
string
string
string
For left justification, %-Ns should be used. For example, consider the following code:

val s1 = String.format("%8s %8s", "Hello", "World")
println(s1)

val s2 = String.format("%-8s %-8s", "Hello", "World")
println(s2)
It produces:

Hello    World
Hello    World   
Although different argument types may have varying formatting requirements, all argument types define the width they take up and their justification as described above.

Formatting integers
The main format specifier for integers (Int), including Long, Short, Byte, and BigInteger, is %d, which has the following additional formatting properties:

%0Nd	Leading zeros fill in the indicated width.
%,d	Thousands divisor.
%+d	Number always signed, even if positive.
% d	For a positive number, insert one leading space.
%(d	Put a negative number in parentheses, without the minus sign.
Note that - is incompatible with 0.

For example:

val int1 = 1234
val int2 = -4567

println(String.format("%d", int1))       //1234
println(String.format("%8d", int1))      //    1234
println(String.format("%-8d", int1))     //1234  
println(String.format("%+d", int1))      //+1234

println(String.format("%+d", int2))      //-4567
println(String.format("%09d", int1))     //000001234
println(String.format("%,10d", int1))    //     1,234
println(String.format("%+,010d", int1))  //+00001,234

println(String.format("%-+,10d", int1))  //+1,234  
println(String.format("% d", int1))      // 1234
println(String.format("% d", int2))      //-4567
println(String.format("%(d", int2))      //(4567)
Formatting octal and hexadecimal numbers
There are also the %o and %x (for lower case) or %X (for upper case) format specifiers for integers (including Long, Short, Byte, and BigInteger) – they are used for formatting numbers as octal and hexadecimal respectively. Note that the normal integer + , ,, (space), and ( formatting properties are incompatible with these format specifiers.

The # formatting indicator can be used in order to lead an octal number with 0 or a hexadecimal number with 0x.

For example:

val int1 = 3465
val int2 = -7896

println(String.format("%o", int1))     //6611
println(String.format("%o", int2))     //37777760450
println(String.format("%#o", int1))    //06611

println(String.format("%8o", int1))    //    6611
println(String.format("%-8o", int1))   //6611
println(String.format("%09o", int1))   //000006611

println(String.format("%x", int1))     //d89
println(String.format("%X", int2))     //FFFFE128
println(String.format("%#X", int1))    //0XD89

println(String.format("%8x", int1))    //     d89
println(String.format("%-8X", int1))   //D89
println(String.format("%09X", int1))   //000000D89
Formatting floating point numbers
There are various format specifiers for floating-point numbers, such as Double and Float. For normal decimal representation, we use %f. It has all the formatting properties of %d, with the addition of an indicator to control the number of the decimal places.

If N and P are positive integers, then %N.Pf or %.Pf denote that the number should have P decimal digits. Note that the number is also rounded up. If P is larger than the number of actual decimal digits, then trailing zeros are added so their number is exactly P.

For example:

val double1 = 1234.5678
val double2 = -1234.5678

println(String.format("%f", double1))      //1234.567800
println(String.format("%f", double2))      //-1234.567800
println(String.format("% f", double1))     // 1234.567800
println(String.format("% f", double2))     //-1234.567800

println(String.format("%(f", double1))     //1234.567800
println(String.format("%(f", double2))     //(1234.567800)
println(String.format("%+f", double1))     //+1234.567800
println(String.format("%,f", double1))     //1,234.567800

println(String.format("%-15f", double1))   //1234.567800    
println(String.format("%015f", double1))   //00001234.567800
println(String.format("%15.2f", double1))  //        1234.57
println(String.format("%.3f", double1))    //1234.568
println(String.format("%.6f", double1))    //1234.567800
In order to get a string represented in scientific notation, the %e (lower case "e") or %E (upper case "E") format specifiers should be used. These specifiers are incompatible with the , property.

For example:

val double1 = 1234.5678
val double2 = -1234.5678

println(String.format("%e", double1))      //1.234568e+03
println(String.format("%E", double2))      //-1.234568E+03
println(String.format("%15.2e", double1))  //       1.23e+03
println(String.format("%.9E", double1))    //1.234567800E+03
And finally, we can use the %g or %G format specifier, which may choose the decimal or the scientific notation, whichever is shorter. For example:

val double1 = 1000.0
val double2 = 10000000.0

println(String.format("%g", double1))  //1000.00
println(String.format("%g", double2))  //1.00000e+07
println(String.format("%G", double2))  //1.00000E+07
Booleans and characters
Format specifiers for the boolean type are %b (for lower case) and %B (for upper case). For example:

val boolean = true

println(String.format("%b",boolean))    //true
println(String.format("%B",boolean))    //TRUE
Format specifiers for the character type are %c (for lower case) and %C (for upper case). For example:

val char = 'a'

println(String.format("%c", char))     //a
println(String.format("%C", char))     //A
Specifiers list
All the above-discussed specifiers are summarized in the following table:

Format specifier	Argument type	Output string
%s	Any type that implements the toString() method	String
%d	Int, Byte, Short, Long, BigInteger	Decimal integer
%o, %O	Int, Byte, Short, Long, BigInteger	Octal number
%x, %X	Int, Byte, Short, Long, BigInteger	Hexadecimal number
%f	Double, Float	Decimal floating point number
%e, %E	Double, Float	Floating point number in scientific notation
%g, %G	Double, Float	Floating point number in decimal or scientific notation
%b, %B	Boolean	Boolean value
%c, %C	Char	Character
%n	Char	Newline
%%	Char	The % character
Conclusion
In this topic, we have discussed string formatting in Kotlin and covered the most useful format specifiers for numeric and string formatting. Let's practice now!

