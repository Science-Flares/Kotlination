## ANSI colors

Command line terminals can do much more than just sequentially print text in specific text and background colors. Most terminals support **ANSI escape sequences** – commands that can change the text and background color, move the cursor to a specified position, or define certain text attributes. You can use them to create really impressive terminal applications.

In this topic, we will look into the ANSI codes that concern coloring.

##### Standardization history

In older days, terminals supported various methods for text, color, and cursor manipulation, and in many cases, those were incompatible with each other. This problem was fixed by **ECMA** (European Computer Manufacturers Association) and **ANSI** (American National Standards Institute), two of numerous organizations for standardization, which produced the **ECMA-48** and the **ANSI X3.64** standards respectively.

One of the first terminals to use the ECMA-48 standard was DEC's VT100, which was a commercial success. As a result, all successive DEC terminals (e.g., VT103 and VT220), as well as terminals from other vendors, began using these standards.

All diverse standards were finally combined by **ISO** (the International Organization for Standardization) into the **ISO/IEC 6429** standard in 1992 (last reviewed in 2020). On a side note, the **Open Document Architecture** standard (**ODA** or **ISO 8613**), which is intended to replace all proprietary document file formats with an open source format, supports ANSI codes since 1995.

##### ANSI escape sequences

The ANSI set of the ASCII character encoding standard (American Standard Code for Information Interchange) defines certain non-printable characters. In the following table, there are some examples:

| Name            | Abbreviation | Value | Action                                    |
| --------------- | ------------ | ----- | ----------------------------------------- |
| Bell            | BEL          | 7     | Produces a sound                          |
| Backspace       | BS           | 8     | Moves the cursor one position to the left |
| Carriage Return | CR           | 13    | Moves the cursor to the first column      |
| Escape          | ESC          | 27    | Initiates an escape sequence              |

ANSI escape sequences used for color setting start with the non-printable `ESC` character followed by the opening bracket `[` and some other characters or digits, depending on the command, and end with the `m` character: `ESC[<various characters>m`. The sequence `ESC` + `[` is also called CSI, which stands for "Control Sequence Introducer".

To print `ESC` in Kotlin, we have to use the UTF-8 escape character `\u` together with the UTF-8 `ESC` character code (`001b`):

```kotlin
println("\u001b")

// OR

println("\u001B")
```

Note that if you copy `\u001b` and then paste it into IntelliJ IDEA within double quotation marks (" "), then a second backslash is added automatically (`"\\u001b"`). This, of course, will just print `\u001b` and will not work as an escape sequence.

##### Terminal support

Command line terminals for Unix-based or Unix-like operating systems (e.g., Linux, OS X, BSD, Solaris, etc.) generally support ANSI escape sequences.

The Windows 10 Command Prompt (cmd) and Windows Powershell also support ANSI colors; however, this option has first to be enabled by changing a registry value: the `HKEY_CURRENT_USER\Console\VirtualTerminalLevel` key should be set to `1`. In case the key doesn't exist, you first need to create it as a DWORD with the value `1`.

The IntelliJ IDEA terminal supports ANSI colors by default. However, it doesn't support other ANSI sequences, like cursor movement codes.

##### 16 colors

You can get the 16 colours of the regular ANSI color scheme with the following ANSI sequence:

```kotlin
\u001b[<Integer Number>m
```

where `<Integer Number>` is one of 32 specific numbers that define either the foreground or the background color – you can find them in the following table. Originally, there where only 8 set colors (30 to 37 and 40 to 47), the other 8 where added afterward.

| Color          | Foreground | Background |
| -------------- | ---------- | ---------- |
| Black          | 30         | 40         |
| Red            | 31         | 41         |
| Green          | 32         | 42         |
| Yellow         | 33         | 43         |
| Blue           | 34         | 44         |
| Magenta        | 35         | 45         |
| Cyan           | 36         | 46         |
| White          | 37         | 47         |
| Bright Black   | 90         | 100        |
| Bright Red     | 91         | 101        |
| Bright Green   | 92         | 102        |
| Bright Yellow  | 93         | 103        |
| Bright Blue    | 94         | 104        |
| Bright Magenta | 95         | 105        |
| Bright Cyan    | 96         | 106        |
| Bright White   | 97         | 107        |

Thus, in order to set the text color to magenta, use the `\u001b[35m` ANSI sequence, and to set the background color to white – the `\u001b[47m` ANSI sequence. You can define the foreground and background colors with one single sequence if you separate them by a semicolon `;`. For example, the sequence `\u001b[37;44m` sets the text color to white and the background to blue.

All settings can be returned to their default values with the help of the `\u001b[0m` sequence. This also works for the sequences we will examine in the next sections.

The colors are not implemented the same way in all terminals. You can check the difference by running the following code in various terminals:

```kotlin
// Set foreground colors
for (i in 30..37) print("\u001b[${i}m ${"%3d".format(i)} ")
for (i in 90..97) print("\u001b[${i}m ${"%3d".format(i)} ")

// Reset colors
println("\u001B[0m")

// Set background colors
for (i in 40..47) print("\u001b[${i}m ${"%3d".format(i)} ")
for (i in 100..107) print("\u001b[${i}m ${"%3d".format(i)} ")

// Reset colors
println("\u001B[0m")
```

The `String.format()` function is used for formatting text the way we want. In the above code, it is used for printing each integer within a 3 character range aligned to the right. You can find more information in Kotlin [format](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/format.html) documentation.

The IntelliJ IDEA terminal output:

![img](https://ucarecdn.com/685de3f0-83e6-40f1-8a2b-500a481ae38e/)

The Windows 10 cmd terminal output:

![img](https://ucarecdn.com/ae0e4312-163c-4f05-a4f8-1bba8c17c4e8/)

The Windows 10 Windows Powershell output:

![img](https://ucarecdn.com/3d8c7886-6978-44d5-a761-9c1933f8eb56/)

##### 256 colors

With extended ANSI sequences, you can get 256 colors. For foreground colors, use:

```kotlin
\u001b[38;5;<Color Number>m
```

For background colors, use:

```kotlin
\u001b[48;5;<Color Number>m
```

Unlike in the case of the 16-color scheme, now the color numbers are the same for both the foreground and the background. The color numbers are defined as follows:

- **0–15**: These are the same as the 16 standard colors discussed in the previous section. For example, number 1 is the red foreground or background color, which in the previous section we defined as 31 and 41 respectively.
- **16–231**: These are 216 colors constructed as `16 + 36 * r + 6 * g + b`, where `r`, `g`, and `b` stand for Red, Green, and Blue respectively. Each one of these can take values from 0 to 5, where 0 stands for the darkest shade of the given color and 5 for the brightest.
- **232–255**: These are grayscale colors from black to white in 24 steps; 232 stands for black and 255 – for white.

We can display all 256 colors with the following code:

```kotlin
// Standard foreground colors
for (i in 0..15) print("\u001b[38;5;${i}m ${"%3d".format(i)}")

// 216 foreground colors
for (i in 16..231) {
    if ((i - 16) % 36 == 0) println()
    print("\u001b[38;5;${i}m ${"%3d".format(i)}")
}
println("\u001B[0m")

// Foreground grayscale shades
for (i in 232..255) print("\u001b[38;5;${i}m ${"%3d".format(i)}")
println()

// Standard background colors
for (i in 0..15) print("\u001b[48;5;${i}m ${"%3d".format(i)}")

// 216 background colors
for (i in 16..231) {
    if ((i - 16) % 36 == 0) println("\u001B[0m")
    print("\u001b[48;5;${i}m ${"%3d".format(i)}")
}
println("\u001B[0m")

// Background grayscale shades
for (i in 232..255) print("\u001b[48;5;${i}m ${"%3d".format(i)}")
println("\u001B[0m")
```

The output of the above code in the IntelliJ IDEA terminal is the following:

![img](https://ucarecdn.com/b0649e7e-4632-4884-8ff6-af249efeb749/)

##### RGB colors

For 24-bit RGB text colors, use the sequence:

```kotlin
\u001b[38;2;<red>;<green>;<blue>m
```

For background colours, use the following sequence:

```kotlin
\u001b[48;2;<red>;<green>;<blue>m
```

Here, `red`, `green`, and `blue` are the corresponding color values, each within the range 0 – 255.

As an example, we use the following code to print a text with certain colors. Both foreground and background colors are set within the same ANSI sequence, divided by a semicolon `;`.

```kotlin
print("\u001b[38;2;255;255;0;48;2;255;0;127mHello World!\u001B[0m")
```

Between the `\u001b[` and `m`, we set the text color as `38;2;255;255;0` and the background as `48;2;255;0;127`, divided by `;`.

The above code results in:

![img](https://ucarecdn.com/83c65f9d-d35d-4fb5-923c-c0e0c9546470/)

##### Conclusion

We have discussed how to use colors in various command line terminals. This may be used for numerous purposes – from creating fabulous terminal applications to using color in logging messages for easier log reviewing.