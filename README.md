# Compiler-Project
Build a dynamic code analyzer for Java Language to generate statement and branches
code coverage reports. Kindly refer to this [stackoverflow link](https://stackoverflow.com/questions/14519416/a-difference-between-statement-and-decision-coverage#:~:text=Statement)
 to know the difference
between statement and branch coverage.
To warm you up, check the expected output of the project that you are expected to
deliver at the end of the project:



![image](https://user-images.githubusercontent.com/66525657/233114995-810a6033-032e-49ef-a9c9-12e67397138a.png)


# How the project work:
1. The main.java file takes the inputs from the directory 'inputs' then makes a java file for every single input file.
2. The HTMLGen.java file runs the output files that made by the main.java then makes a text file for every single output file.
3. Every output.txt has an array with the number of blocks that's visited and the not-visited ones it will have zero in the array.
4. The HTMLGen.java file uses these arrays to know which block isn't visited to make its color is red and then makes an output.html file to show the output in it.

# How to run the project:
"Running this project is easier than making a cup of coffee! Simply run 'main.java', sit back, relax, and watch as the project works its magic like a caffeinated pipeline."
