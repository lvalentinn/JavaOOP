#2. Print Queue 
In this problem you will simulate multiple printers processing jobs from a single print queue. To
do this you will need to create the following classes:
TestPrintQueueSimulation – which includes the main program.
PrintQueueSimulation – controls the simulation and prints the outputs
Job – A class from which printing jobs are created
Printer – an class from which Printers are created
Below is a description to guide you through the program. Remember that object instance
variables are initialized to null unless overwritten.
Job class (20)
Job has the following variables (You define the access modifier ):
int id;
int arrivalTime;
int timeForJob;
int priority;
private int startTime; //fpr job
int waitTime; //in queue
int endTime; //for job
static int idCounter = 1;
The Job implements the Comparable interface
A Job has a default constructor that does nothing.
A Job has a constructor that is instantiated with three int variables, indicating the arrivalTime,
the timeForTheJob, and the priority.
Call setID with no parameters
Then call the set methods for each of the parameters in the constructor
The following methods are required; Please read any special programming required for certain
of the set methods. The accessors are all standard with no extra processing
setID and getID,

setArrivalTime and getArrivalTime
setTimeForJob and getTimeForJob
setPriority and getPriority
setStartTime and getStartTime
setEndTime and getEndTime
getWaitTime
Special mutator instructions
When calling setID
Set to the idCounter.
Increment the idCounter
When calling setStartTime to set the startTime you must also calculate and set the waitTime
The compareTo interface is implemented based on priority, with priority 1 Jobs printed before
priority two, etc.
Printer class (20)
Printer class has the following variables (You define the access modifier ):
Job printJob; This instance variable is either null or refers to the current Job being
processed by the printer.
String printerName;
int startIdleTime;
startInUseTime;
totalIdleTime;
totalInUseTime;
totalJobsProcessed;
Printer()
A Printer class has a default constructor that does nothing
A Printer class has a construcmor that is instantiated with a printerName.
Use the appropriate Mutator method to set the printerName
The following methods are required: - note special instructions
setPrinterName and getPrinterName
setJob and getJob
setStartInUseTime – accepts a time (as an int) and includes incrementing the
totalJobsProcessed variable
setStartIdleTime – accepts a time (as an int) and includes updating the totalInUseTime
getTotalIdleTime – easily calculated at the end of the simulation since it is called with
currentTime as an input parameter -2 miscalculated
getTotalInUseTime
getTotalJobsProcessed
PrintQueueSimulation class (60)

This is the main driver of this simulation. It is instantiated with the number of printers and the
number of jobs to be processed. The simulation runs until all the jobs are processed. There is a
single print queue that all printers service. As soon as a printer finishes it get the next job (if any
exists) from the waitQueue. Printer Printer0 has priority over Printer1, etc. The simulate method
(see below) runs the simulation and calls displayStatistics once the simulation completes.
This class has the following instance variables:
waitQueue which is a PriorityQueue where the references to arriving Jobs are placed, until they
pulled by a Printer. (Instantiate as a PriorityQueue based on the natural order which is of
course based on the compareTo method in Job)
an int totalWaitTime which keeps track of the total wait time for all Jobs
finishedQueue which is a PriorityQueue where a Job reference is placed when a job is
completed
an int currentTime is the simulation clock which starts at 0 and is incremented 1 second at a
time
printer which is a reference to an array of Printer objects
randy – a reference to a Random object
two int variables – numberOfPrinters and numberOfPrintJobs
The PrintQueueSimulation constructor’s parameters are the numberOfJobs, numberOfPrinters,
and a seed for the Random object randy.
It uses the first two values to set the instance variables numberOfJobs and numberOfPrinters.
(You don’t need to create mutators here)
It creates a Random object randy with the seed in the from the constructor parameter list.
It creates a waitQueue instantiated as a PriorityQueue with a capacity equal to the
numberOfPrintJobs
It creates a finishedQueue instantiated as a PriorityQueue with a capacity equal to the
numberOfPrintJobs.
It also creates an array printer of length numberOfPrinters.
It then creates a set of printers that total the numberOfprinters, with the array printer referring to
each of them in order. As the printer is created the printer names will be Printerl0, Printer1, …
(Hint: printer[i] = new Printer(&quot;Printer&quot; +i);
simulate() method
This method will simulate the completion of all the print jobs, using the numberOfPrinters as
defined.
You may want to create some local variables to help with this method
I created a flagDone which I set to false, allowing me to loop through the logic below until all the
Jobs were printed.
For this simulation a new job will be created every 80 seconds. When a Job is created it is
randomly assigned a jobTime of between 10 and 1100 seconds inclusive.
It is also assigned a priority of from 1 to 11 inclusive
Below is an outline of the logic, but you will need to do some work and coding
While all jobs aren’t complete (flag is false)

If a job needs to be created (Every 80 seconds (%80 == 0) and not all jobs yet created
Generate a jobTime and jobPriority create a Job with the required 3 parameters
Add the Job to the waitQueue
Increment the jobNumber(just a counter to keep track of number of jobs created)
//See if any Job is fished
For each printer
If there is a Job in the printer
If the Job is complete
Set the endTime for the Job
Place job in the finishedQueue
Set the printer’s Job to null
Set the startIdleTime for the printer to the currentTime
//See if a printer is idle. If so and a job is waiting and assign it to the printer if there is one
For each printer
If the printer is idle (i.e. the Job is null)
If the waitQueue isn’t empty
Remove a Job from the waitQueue
Set the startTime for the Job.
Place the Job in the printer (i.e. use setJob for the printer)
Set the startInUseTime for the printer
Update the totalWaitTime
Increment the currentTime
If the number of jobs to arrive is complete and the waitQueue is empty
Set the flag to true
If there is still a job in any printer
Set the flag back to false
End of loop

displayStatistics() (don’t forget the throws Exception)
Create a Scanner to read from the keyboard
Read in the name of an output file and create a PrintWriter based on that name
Note: Print out the Job Statistics table using the finishedQueue which will print the Jobs in order
based on theor priotities
Produce a report like the one in the printerstats.txt file in the Lesson 9 Homework file on
Canvas.
TestPrintQueueSimulation class (10) (don’t forget the throws Exception)
Create a Scanner to read in data via the keyboard
Request the number of printers, the number of print jobs and a seed.
Create a PrintQueueSimulation object
Call the simulate method for the object you have just created
Call the displayStatistics method for the object you have just created

Sample Input
Please enter the number of printers for the simulation: 6
Please enter the number of printer jobs for the simulation: 45
Please enter a random number seed for the simulation: 2
Enter the name of your output file for the results: printerstats.txt
