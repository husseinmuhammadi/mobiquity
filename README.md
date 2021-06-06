# Backend Code Assignment - Mobiquity 2021

## Knapsnak Problem
It is a famous problem in algorithms which I remember it from university studing.
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.

### Method 1: Recursion by Brute-Force algorithm OR Exhaustive Search.
A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets.

### Method 2: Dynamic Programming(DP) problems


## Test Driven Development

### Create data models and APIs
As we are supposed to put the items in the package with the max value, we create some data models regarding to this challenge
Two data model were created: Knapsnak and KnapsnakItem with some consideration 

### Create class PackageDataReader
This class is responsible for reading data from a stream and parse it as a _**Knapsack**_.
As reading a file and parsing is a separate activity rather than solving knapsack problem,
so I use _**Observber/Observable**_ pattern in order to make the code loosely coupled.

### User stories:
As I am going to write my code based on TDD approach, first I write user stories 
as much as possible and after that I will write my code in order to all my test cases 
satisfied, hence below would be some user stories:

I create data model using _**builder design pattern**_ and I prefer to keep my data models
_**immutable**_ type. If we need to share state between different threads, we can create
_thread-safe_ classes by making them immutable.

#### Knapsack Data Model
- Knapsnak can not carry more than 100
- The number of items in each problem can not be more than 15 

#### Knapsack Item Data Model
- Maximum weight of each item can not be more than 100
- Maximum cost of each item can not be more than 100 

#### Package Data Reader
- The test file will be in UTF-8 and all lines should be parsed as a knapsack object




