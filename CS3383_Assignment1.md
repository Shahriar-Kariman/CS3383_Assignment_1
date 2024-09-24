# CS3383 Assignment 1

**Author:** Shahriar Kariman
*Due:* September 25th

## Question 1 - Run Time Analysis

### A

```py
def for_loop1(n):
  p = 1 # theta = 1
  for i in range(1, 5*(n**2)): # theta = n^2
    p = p * i
  return p # theta = 1
```

The run time analysis is $\Theta(n^{2})$.

### B

```py
def for_loop2(n):
  s = 0 # theta = 1
  for i in range(1,n): # theta = n
    for j in range(i,n): # theta = n^2
      s = s + i
  return s # theta = 1
```

$$
\begin{split}
  \sum_{i=1}^{n} i = \frac{n\times(n+1)}{2}
\end{split}
$$

The run time analysis is $\Theta(n^{2})$.

### C

```py
def WhileLoop1(n):
  x = 0 # theta = 1
  j = 2 # theta = 1
  while j<=n: # theta = log(n)
    x = x+1
    j = 2*j
```

How j is incremented in each loop:

$$
\begin{split}
  2, 4, 8, 16, ...\ , 2 ^ k
  \ when \
  2 ^ k = n
  \\
  k = \log_2 n
\end{split}
$$

So with $k$ being the equivalent of the number of iterations that would mean that the run time analysis is $\Theta(\log n)$.

### D

```py
def WhileLoop2(n):
  x = 0 # theta = 1
  j = n # theta = 1
  while j>=1: # theta = log(n)
    x = x+1
    j = 2*j/3
```

If $k$ is the number of iterations then:

$$
\begin{split}
  (\frac{2}{3})^k \times n \leq 1
  \\
  n \leq (\frac{3}{2})^k
  \\
  \log_{1.5}(n) \leq k
\end{split}
$$

So the run time analysis of the function is $\Theta(\log n)$.

### E

```py
def WhileLoop3(n):
  x = 0 # theta = 1
  j = 2 #theta = 1
  while j<=n: # theta = log(log(n))
    x = x+1
    j = j**3
```

If $k$ is the number of iterations then:

$$
\begin{split}
  2^{3^k} \geq n
  \\
  3^k \geq \log_2(n)
  \\
  k \geq \log_3{(log_2(n))}
\end{split}
$$

So the run time analysis of the function is $\theta(\log(\log n))$.

### F

```py
def WhileLoop4(n):
  x = 0
  j = n
  while j>=2:
    x = x + 1
    j = math.sqrt(n)
```

If $k$ is the number of iterations then:

$$
\begin{split}
  n^{(\frac{1}{2})^k} \leq 2
  \\
  (\frac{1}{2})^k \times \log(n) \leq \log(2)
  \\
  (\frac{1}{2})^k \leq \frac{\log(2)}{\log(n)}
  \\
  2^k \geq \frac{\log(n)}{\log(2)}
  \\
  k \geq \log_2 (\frac{\log(n)}{\log(2)})
  \\
  k \geq \log_2 (\log_2(n))
\end{split}
$$

So the run time analysis of the function is $\theta(\log(\log n))$.

### G

```py
def WhileLoop5(n):
  x = 0
  j = n
  while j>=1:
    for i in range(j, n):
      x = x + 1
    j = j - 1
  return x
```

$$
\begin{split}
  j = 1, 2, 3, ..., n
  \\
  total\ number\ of\ iterations = \sum_{j=0}^{n} (n-j) = \sum_{j=0}^{n} j = \frac{n\times(n-1)}{2}
\end{split}
$$

So the run time analysis of the function is $\theta(n^2)$.

### H

```py
def WhileLoop5(n):
  x = 0
  j = 1
  while j<=n:
    for i in range(1, j):
      x = x + 1
    j = 2*j
  return x
```

Based on the geometric series sum formula I could say:

$$
\begin{split}
  j \rightarrow 1, 2^1, 2^2, ...\ , 2^k
  \\
  k = log_2{n}
  \\
  total\ number\ of\ for\ loop\ iterations = \sum_{k=0}^{\log_2{n}} 2^k = \frac{2^{\log_2(n)+1} - 1}{2 - 1}
  \\
  total\ number\ of\ for\ loop\ iterations < 2^{\log_2(n)+1} = 2n
\end{split}
$$

That means the run time analysis of the function is $\theta(n)$.

## Question 2 - Factor Sum Algorithm

```py
def FactorSum(A, n):
  s = 0
  for i in range(0,n-1):
    for j in range(i, n-1):
      p = 1
      for k in range(i, j):
        p = p*A[k]
      s = s + p
  return s
```

### A

for the first loop the run time complexity is:
$$
\begin{split}
  i \rightarrow 0, 1, 2, 3, ...\ , n-1
  \\
  S_1 = \sum_{i=0}^{n-1} i = \frac{n \times (n-1)}{2}
\end{split}
$$

for the second loop the run time analysis is:

$$
\begin{split}
j \rightarrow i, i+1, ...\ , n-1
  \\
  S_2 = (n-1) + (n-2) + (n-3) + ...\ + 2 + 1 = \sum_{j=0}^{n-1} j = \frac{n \times (n-1)}{2}
\end{split}
$$

And for the inner most loop we basically run it j-i each time so if I add all possible j-i values together I would get:

$$
\begin{split}
  S_3 = \sum_{i=0}^{n-1} \sum_{j=i}^{n-1} j-i
  \\
  S_3 = \sum_{j=0}^{n-1} (j-0) + \sum_{j=1}^{n-1} (j-1) + \sum_{j=2}^{n-1} (j-2) + ...\ + \sum_{j=n-1}^{n-1} (j-n+2)
  \\
  S_3 = f_0 + f_1 + f_2 + ...\ + f_{n-1}
  \ each\ f\ statment\ is\ \Theta(n^2)
  \\
  \Theta(n) \times \Theta(n^2) = \Theta(n^3)
\end{split}
$$

So the over all run time analysis is $\Theta(n^3)$.

### B

So what we are essentially adding up factorials of numbers $i$ to $j$ for every possible $i$ and $j$ where $i \geq j$ so I would calcualte that like this:

```py
def FactorSum(A, n):
  s = 0
  for i in range(0,n-1):
    p = 1
    for j in range(i, n-1):
      p = p*A[j]
      s = s + p
  return s
```

And the run time analysis of this algorithm is $\Theta(n^2)$ since we are only getting all possible values for $i$ and $j$ which is better than the previous algorithm.

## Question 3 - Repeating Numbers Algorithm

Since the space complexity needs to be 1 then I cant be using an array to store the repeating numbers in an array to return in the function so I am assuming the output of the program if just through printing to the standard out.

```py
def repeatingNumbers(A, n):
  for i in range(1,n):
    if (A[i])!=i:
      # this is how you swap 2 indexs in python
      A[i], A[A[i]] = A[A[i]], A[i]
  for i in range(0,n):
    if(A[i]!=i):
      print(A[i])
```

The 1st for loop puts as many values in the index equal to the value inside the array and in the 2nd for loop if the same value is not in its place then it is a duplicate.

Since in the algorithm I am running through the array 2 times the run time complexity is $\Theta(n)$.

## Question 4 - Making Change for n Cents

lets assume I want to make change for 30 cents if I use the greedy algorithm I would have `25 + 1 + 1 + 1 + 1 + 1` wich is `5` coins but I now for a fact that I can use `3` coins if I only use 10 cents for each. This means that the greedy algorithm is not optimal.

## Question 5 - Points on The Line

I can sort all of the points in the line in ascending order then take the first point and add to it.

```py
def pointsOnLine(points):
  mergeSort(points)
  x1 = points[0]
  intervals = []
  while(len(points)!=0):
    intervals.append([points[0],points[0]+1])
    j = 0
    # go through the array until you get to the first element that is not in the
    # most recent interval and save the index in j
    for i in range(0,len(points)):
      if points[i]>x1+len(intervals):
        j = i
        break
    points = points[j:] # remove the first j elements of the array
```

So the complexity of the sorting algorithm is $\Theta(n\log{n})$ and every index of the array is only removed once so the complexity of the rest of the algorithm is $\Theta(n)$ so over all the complexity of the pointsOnLine algorithm is $\Theta(n\log{n})$.

### Proof that the Greedy Algorithm is Optimal

Lets assume the proposed greedy algorithm is not optimal and uses $k$ intervals that means there is an algorithm A that solves the problem using $k-1$ intervals.

The greedy algorithm starts at point $x1$ then if $A$ starts the firts interval at point $x$  where $x \leq x_1$ because if $x \geq x_1$  then some points will not be covered so $A$ is just slightly shifted to the left compared to the greedy.

Since we determind that $x \leq x_1$ then $[x_1, x_1+1]$ still covers all the points in the array that $[x, x+1]$ covers so it would not make a difference if we started $A$ at $x_1$ lets call this modified $A$, $A^\prime$ and if we keep following all of the intervals of $A^\prime$ we end up with exaclty the first $k-1$ intervals in the greedy algorithm. Knowing that the greedy algorithm needs $k$ intervals to complete then $A^\prime$ is incomplete and this **contradicts** the assumption that $A$ only needs $k-1$ intervals to be complete so the greedy algorithm that uses $k$ intervals is optimal.
