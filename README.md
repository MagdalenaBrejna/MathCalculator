<h1 align="center"> MathCalculator </h1> <br>

<table>
<tr>
<td>
  
  The MathCalculator is the model of a basic and a scientific calculator created using Java and Swing. Thanks to containing the most important operation signs, 
  constants and functions, it lets count complex mathematical operations. 
  
  The project was created to practise Java and Swing.
 <td>
 <tr>
</table>

<p align="center">
    <img alt="MathCalculator" title="MathCalculator" src="https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/icon.jpg" width="450">
</p>

## Table of contents
* [About](#about)
* [Code Examples](#code_examples)
* [Technology](#technology)
* [To-Do](#features)
* [Status](#status)
* [Contact](#contact)

## About
The MathCalculator contains two different versions of a calculator - the basic and the scientific one. They can be swop using tabs. 

The basic calculator lets handle the four basic maths operations on positive and negative floating-point numbers. The result is counted on the fly. 
Clicking equal sign button save the final reslut. Clicking operation sign button after counting the final result will result in treating the final 
result as a number in the operation. Clicking figure button will restart the operation.  


<p align="center">
    <img alt="calculator_screen_3" title="calculator_screen_3" src="https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/calculator__screen_3.png" width="400">
</p>

The scientific calculator lets count complex mathematical operations which apart from basic operation signs include also functions (ln(), log(),
sin(), cos(), tg(), exact the root of a number, exponentiation) and constants (e, PI). The result is counted on the fly, if there is a possibility that the operation 
is correct. If the equal sign button was clicked and the operation is incorrect, the program will show error massege with the information about the kind of mistake.
The DEL button enables coming back to the operation, if the result was counted or lets remove the last step. The Calculator tries to fix user bugs 
(e.g. lack of an ending bracket)



|  correct           |  incorrect |
|---------------------|----------------------|
|![calculator_screen_1](https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/calculator__screen_1.png) | ![](https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/calculator__screen_2.png) |

## Code Examples

<p1 align="center"> DEL button action implementation.</p1> <br>

![](https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/code__screen_1.png)

<p1 align="center"> Counting result.</p1> <br>

![](https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/code__screen_2.png)

<p1 align="center"> Setting priority to the different kinds of mathematical operators signs (the part of the ONP algorithm).</p1> <br>

![](https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/code__screen_3.png)

<p1 align="center"> The abstract class button.</p1> <br>

![](https://github.com/MagdalenaBrejna/MathCalculator/blob/master/src/MB_calculator_images/code__screen_4.png)

## Technology
* Java
* Swing

## To-Do
* A small database with a list of the previous results.
* A new tab with a calculator for different number systems.

## Status

The main project finished. Some extra features in progress.

## Contact
magdalena.brejna@wp.pl

https://www.linkedin.com/in/magdalena-brejna


