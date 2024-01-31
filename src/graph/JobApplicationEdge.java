package graph;

import java.util.Date;

public class JobApplicationEdge {
    private JobApplicationNode jobApplication;
    private JobNode job;
    private Date submissionDate;

    public JobApplicationEdge(JobApplicationNode jobApplication, JobNode job, Date submissionDate) {
        this.jobApplication = jobApplication;
        this.job = job;
        this.submissionDate = submissionDate;
    }

    public JobApplicationNode getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplicationNode jobApplication) {
        this.jobApplication = jobApplication;
    }

    public JobNode getJob() {
        return job;
    }

    public void setJob(JobNode job) {
        this.job = job;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
}
