package graph;


public class JobApplicationNode {
    private JobSeekerNode jobSeeker;
    private JobNode job;
    private String applicationCompany;
    private String status;
    
    public JobApplicationNode(JobSeekerNode jobSeeker, JobNode job, String applicationCompany, String status) {
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.applicationCompany = applicationCompany;
        this.status = status;
    }

    public JobSeekerNode getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeekerNode jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public JobNode getJob() {
        return job;
    }

    public void setJob(JobNode job) {
        this.job = job;
    }

    public String getApplicationCompany() {
        return applicationCompany;
    }

    public void setApplicationCompany(String applicationCompany) {
        this.applicationCompany = applicationCompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
