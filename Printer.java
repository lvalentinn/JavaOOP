public class Printer {
	
    
	private Job printJob;        
    
	private String printerName;   
    
	private int startIdleTime;    
    
	private int startInUseTime;   
    
	private int totalIdleTime;   
    
	private int totalInUseTime;   
    
	private int totalJobsProcessed; 
    
	public Printer() {
    
		
    
	}

    
	public Printer(String printerName) {
    
		setPrinterName(printerName);
    
	}

    
	public void setPrinterName(String name) {
    
		this.printerName = name;
    
	}

    
	public String getPrinterName() {
    
		return printerName;
    
	}

    
	public void setJob(Job job) {
    
		this.printJob = job;
    
	}

    
	public Job getJob() {
    
		return printJob;
    
	}

    
	public void setStartInUseTime(int time) {
    
		this.startInUseTime = time;
        
		totalJobsProcessed++;
    }

    public void setStartIdleTime(int time) {
        
    	totalInUseTime += (time - startInUseTime);
        
    	this.startIdleTime = time;
    }

    
    public int getTotalIdleTime(int currentTime) {
        
    	return currentTime - startIdleTime + totalIdleTime;
    }

    public int getTotalInUseTime() {
    
    	return totalInUseTime;
    
    }

    public int getTotalJobsProcessed() {
    
    	return totalJobsProcessed;
    }
}
