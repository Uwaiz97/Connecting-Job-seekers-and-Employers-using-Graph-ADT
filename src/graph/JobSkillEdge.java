package graph;

import java.util.List;

public class JobSkillEdge {
    private JobSeekerNode jobSeeker;
    private JobNode job;
    private List<String> requiredSkills;

    public JobSkillEdge(JobSeekerNode jobSeeker, JobNode job, List<String> requiredSkills) {
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.requiredSkills = requiredSkills;
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

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
