# CSX42: Assignment 4
## Name: Nicholas Vallejos

-----------------------------------------------------------------------
-----------------------------------------------------------------------


## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant -buildfile numberPlay/src/build.xml run \
-DinputFile="<Input filename>" \
-DavailableItemsFile="<Available items file>" \
-DrunningAverageWindowSize="<Window size for running average calculation>" \
-DoutputFile="<Output filename>"
```
-----------------------------------------------------------------------
## References:
- HashMap Documentation: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
- State Pattern Idea: Panopto lecture on State Pattern on Mycourses
-----------------------------------------------------------------------
## Important Notes:
- I used the same validator classes and custom exceptions from the previous assignment 3 which includes:
	- CommandArgHandler
	- Validator
	- ValidatorUtil
	- EmptyFileException
	- InvalidArgNameException
	- NumberOfArgsException
- I used the PersisterI interface and RunningAverageResultsI interface from my assignment 2 submission
- I used the FileProcessor class from previous assignment 3 & 2
-----------------------------------------------------------------------
