# Finding a convex hull with the quickhull algorithm

There are several algorithms that can accomplish the task, however, 
for this assignment you will be implementing a nice algorithm that 
utilizes the divide and conquer technique invented by 
C. Bradford Barber, David P. Dobkin, and Hannu Huhdanpaa. 
You can find the original paper here: 
http://dpd.cs.princeton.edu/Papers/BarberDobkinHuhdanpaa.pdf

The algorithm is known as the quickhull algorithm and shares 
complexity of a sort by they same name standard, quicksort. 
The average time for the quickhull algorithm is O(n log n) but O(n2) for the worst case.
The worst-case scenario is when all the points are part of the convex hull.

Here is a how the algorithm works.
1. Find the leftmost point A and rightmost point B. Add these two points to the convex hull.
2. The line formed by these two points separate the rest of the points above and below the line. These two sets will be processed recursively.
3. Find the point farthest from the line C and add it to the convex hull.
4. This new point will form a triangle with the previous two points.
5. You may ignore all points inside the triangle as they will not be part of the convex hull.
6. You will now have two new lines formed by AC and BC as well as a new set of points to the left and right of the triangle.
7. Repeat steps 3 – 6 on these new lines and continue until no more points are left.
8. Return your convex hull.

## Input

The input will be a file containing the coordinate of each point in a shape. 
Each line of the file contains a separate X and Y coordinate separated by a space. 

## Output

Output a new line separated list of coordinates in which each line contains an X, and a Y 
separated by a space that represents a point on the convex hull.

## Math Needed

1. Distance of a point from a line.
2. Determining what side a point is on.
3. Determining if a point is within a triangle.




