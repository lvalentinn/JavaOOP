import java.io.PrintWriter;
import java.util.*;

public class PrintQueueSimulation {
	
    private PriorityQueue<Job> waitQueue;    
    
    private PriorityQueue<Job> finishedQueue; 
    
    private int totalWaitTime;      
    
    private int currentTime;  
    
    private Printer[] printer;                 
    
    private Random randy;                      
    
    private int numberOfPrinters;              
    
    private int numberOfPrintJobs;             

    public PrintQueueSimulation(int numberOfJobs, int numberOfPrinters, int seed) {
    	
        
    	this.numberOfPrintJobs = numberOfJobs;
        
    	this.numberOfPrinters = numberOfPrinters;
        
    	this.randy = new Random(seed);

        
    	waitQueue = new PriorityQueue<>();
        
    	finishedQueue = new PriorityQueue<>();
        
    	printer = new Printer[numberOfPrinters];

       
    	for (int i = 0; i < numberOfPrinters; i++) {
           
    		printer[i] = new Printer("Printer" + i);
        
    	}
    }

    public void simulate() {
    	
        boolean flagDone = false;
        
        int jobNumber = 0;
        

        while (!flagDone) {
        	
        	
        	
            if (currentTime % 80 == 0 && jobNumber < numberOfPrintJobs) {
            	
                int jobTime = randy.nextInt(1091) + 10;  
                
                int priority = randy.nextInt(11) + 1;     
                
                Job newJob = new Job(currentTime, jobTime, priority);
               
                waitQueue.add(newJob);
                
                jobNumber++;
            }

            
            for (int i = 0; i < numberOfPrinters; i++) {
            	
                Printer p = printer[i];
                
                Job job = p.getJob();
                
                if (job != null && currentTime >= job.getStartTime() + job.getTimeForJob()) {
                	
                    job.setEndTime(currentTime);
                    
                    finishedQueue.add(job);
                    
                    p.setJob(null); 
                    
                    p.setStartIdleTime(currentTime);
                }
            }

            for (int i = 0; i < numberOfPrinters; i++) {
                
            	Printer p = printer[i];
                
            	if (p.getJob() == null && !waitQueue.isEmpty()) {
                
            		Job job = waitQueue.poll();
                    
            		job.setStartTime(currentTime);
                    
            		p.setJob(job);
                    
            		p.setStartInUseTime(currentTime);
                    
            		totalWaitTime += job.getWaitTime();
                }
            }

            currentTime++;

            if (jobNumber >= numberOfPrintJobs && waitQueue.isEmpty()) {

            	flagDone = true;
            
            }
            
            for (int i = 0; i < numberOfPrinters; i++) {
            
            	if (printer[i].getJob() != null) {
                
            		flagDone = false;
                    
            		break;
                }
            }
        }
    }

    public void displayStatistics() throws Exception {
    	
        Scanner kb = new Scanner(System.in);

        // Prompt for output file name
        System.out.print("Enter the name of your output file for the results: ");
        
        String outputFileName = kb.nextLine();

        PrintWriter writer = new PrintWriter(outputFileName);

        // Job statistics
        
        writer.println("Simulation Results");
        
        writer.printf("Simulation with %d printers lasted %d seconds and processed %d jobs%n", 
                      numberOfPrinters, currentTime, numberOfPrintJobs);
        
        
        writer.printf("The average time in the wait queue for a job is %.2f seconds%n", 
                      totalWaitTime / (double) numberOfPrintJobs);
        
        writer.println("Printer Statistics");
        
        
        writer.println("Name     Jobs Processed   Time In Use   Idle Time");
        
        for (int i = 0; i < numberOfPrinters; i++) {
        
        	Printer p = printer[i];
            
        	writer.printf("%-9s %-15d %-13d %-9d%n", p.getPrinterName(), 
            
        			p.getTotalJobsProcessed(), p.getTotalInUseTime(), 
                    
        			p.getTotalIdleTime(currentTime));
        }

        writer.println("Job Statistics");
      
        writer.println("Job No.   Priority   Wait Time   Length Of Job");
        
        while (!finishedQueue.isEmpty()) {
        
        	Job job = finishedQueue.poll();
            
        	writer.printf("%-9d %-10d %-11d %-13d%n", job.getID(), 
            
        			job.getPriority(), job.getWaitTime(), 
                    
        			job.getTimeForJob());
        }


        writer.close();
        
        System.out.println("Simulation results written to " + outputFileName);
    }
}

