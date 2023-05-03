# Music Visualiser Project
----
# Youtube Video of Our Project
[![YouTube](https://github.com/MK-CIAN/MusicVisuals/blob/master/images/OOP_Pic.jpg?raw=true)](https://youtu.be/flGGAfn6u8M)
----
# Group Members
| Name | Student Number |
|-----------|-----------|
|Cian McKenna | C21755919 |
|Ian Hipolito | C21436494 |
|Josua Flores | C21352183 |

----

# Description of the assignment
In this assignment we have a total of 5 different scenes that use Java Processing.
The song we chose for this assignment is "starryeyed" by Ellie Goulding. We felt this song gave us good oppurtunties to create a narrative within our video and of course gave us many visualization oppurtunites. All of our visualisations respond to the amplitude and frequency of the music, this is doen through the minim library.

----
# Instructions
- Compile and run main.java from ie.tudublin package
- In order of appearance = {Scene1(Cian), Scene2(Ian), Scene3(Cian & Ian), Scene4(Joshua), Scene5(Joshua), , Scene1(Cian)}
----
# How it works
- We created a folder for each team members scene's, these folders are named with our student numbers. 
- We then have a main file which is used to run all of our visualtions this file is called MainVisual, this is an extend of the Visual file.
- The MainVisual file starts the music and calls the needed methods from the visual file, such as getAmplitude.
----
## Cian McKenna
- I created the opening scene of the project, this scene is meant to resemble an stary night sky aong with shooting stars and northern lights to visualise the music, also with moving terrain in the foreground.
- To create this scene I created a randomly generated stars to fill up the sky, this is done through creating an arraylist of different parameters such as StarNum, x, y, StarReact, these parameters would all have random values to look more realistic. The northern lights visulizer is made by using by getting the size of amplitude in the audio player and creating lines that are controlled by the lerped buffer. The terrain is created by creating an array which changes values based on the noise function.
![Cian Scene 1](images/CMCKOOP.jpg)
- The next scene I was involved in was scene 3, I worked on these scene with Ian and we created a visual that mathced the lyrics of the song(Carousels of Color)
-How this was acheieved was by creating 5 different lines who's lenght and thickness is effected by the amplituded of the music, this is meant to represent the rides you would see on a carousel, we then added an outside ring of points which would rotate to give use the overall carousel shape, this is done through a TWO_PI loop and various points with different variables.
![Cian & Ian Scene 3](images/C&I_OOP.jpg)
----
# What I am most proud of in the assignment
## Cian McKenna
- Overall I'm very pleased with the way our project turned out, since in the middle of the project I felt the video lacked direction but we managed to create something good.
- Also learning java, definetly please with how much experience this project has given me in java and working through all the many errors that we got.


