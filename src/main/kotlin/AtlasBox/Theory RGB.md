## Theory: RGB

If you have worked in graphics programs at least once, you may have noticed the strange digits displayed next to the color palette. Moreover, they even change depending on the choice of the shade.

![img](https://ucarecdn.com/5964e3b9-feb9-45fd-a65a-0a6b1b2e4a06/-/crop/518x297/419,221/-/preview/)

The thing is: there are different ways to communicate to the computer what shade you want to use. Sometimes ordinary words are not enough to accurately describe the shade you need. For such cases, there are **color models**: methods of describing colors using quantitative characteristics. One of these is the **RGB** model.

##### Colors

**RGB** is one possible way to represent a shade which is received as combination of three basic colors: **R**ed, **G**reen, **B**lue. Using various degrees of saturation of the basic colors, you can get any shade. It is described using numbers in the range from `0` to `255`. The black color is equivalent to the absence of this quality, therefore, its record will consist of their three zeros:

```
(0, 0, 0)
```

In order to get white, on the contrary, you need to make a combination of the maximum possible numbers:

```
(255, 255, 255)
```

In records, the first number is always responsible for red, the second for green, and the third for blue. At this point, it is easy to guess how to indicate bright red, bright green (it has its own name, lime) and bright blue into RGB:

| **name** | **color** | **RGB**     |
| -------- | --------- | ----------- |
| red      |           | (255, 0, 0) |
| lime     |           | (0, 255, 0) |
| blue     |           | (0, 0, 255) |

Going beyond the minimum and maximum values, you can get other beautiful shades:

| **name**      | **color** | **RGB**         |
| ------------- | --------- | --------------- |
| oldLace       |           | (253, 245, 230) |
| paleTurquoise |           | (175, 238, 238) |
| thistle       |           | (216, 191, 216) |

Theoretically, RGB can describe more than sixteen million colors. This is more than the human eye can distinguish! This huge diversity does not fit in our topic, but you can always find out the name and composition of the color you like with the help of [color pickers](https://htmlcolorcodes.com/color-picker/).

##### RGB and HEX

Each RGB color can be represented in the hexadecimal system (HEX). It looks like this: `#RRGGBB`, where the first couple of digits is responsible for red, the second for green and the third for blue. To inform the computer that the color is written in HEX, put the hashtag symbol `#` before its value. Consider some examples:

| **name** | **color** | **RGB**         | **HEX** |
| -------- | --------- | --------------- | ------- |
| black    |           | (0, 0, 0)       | #000000 |
| white    |           | (255, 255, 255) | #FFFFFF |
| red      |           | (255, 0, 0)     | #FF0000 |
| lime     |           | (0, 255, 0)     | #00FF00 |
| blue     |           | (0, 0, 255)     | #0000FF |
| thistle  |           | (216, 191, 216) | #D8BFD8 |

You can also find the code of the desired color in HEX or convert the RGB value to the hexadecimal system using [color pickers](https://htmlcolorcodes.com/color-picker/).

##### Digital devices and printing

It is not rare that the same colors in the design layouts of the site and in the printing products do not look the same due to minor differences in intensity. This is because the RGB color model is needed to create images that will be displayed on the screens of digital devices. RGB is also often used when selecting colors to be viewed in a browser. For example, when working on the appearance of web pages, you can use RGB to set the color of their specific parts.

Layouts for the press are always executed in **CMYK** as this is the only color model the printers can work with, so they automatically treat any color as CMYK. The abbreviation **CMYK** refers to the names of the basic ink colors used for the four-colors press: **c**yan, **m**agenta and **y**ellow. The letter **K** indicates blac**k** ink. The last letter of the word is used instead of the first to avoid confusion between black and blue. CMYK color coverage is smaller than that of RGB, so the printed images may look a little duller than on a computer screen.

##### Conclusion

The RGB color model is mainly used when it is necessary to output product design on digital graphic devices. It is useful to have a common language not only with people but also with electronics. Now you can even explain to a computer what the true visualization of your dreams should look like! It's hard to believe that everything we see on our monitors is a combination of three colors. By combining these three colors in different proportions, you can get any shade. Let's see if you can mix them!