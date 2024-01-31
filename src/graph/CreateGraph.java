package graph;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateGraph {
    private List<JobSeekerNode> jobSeekers = new ArrayList<>();
    private List<JobNode> jobs = new ArrayList<>();
    private List<JobApplicationNode> applications = new ArrayList<>();
    private List<JobSkillEdge> skillEdges = new ArrayList<>();

    public List<JobSeekerNode> getJobSeekers() {
        return this.jobSeekers;
    }

    public List<JobNode> getJobs() {
        return this.jobs;
    }

    public List<JobApplicationNode> getApplications() {
        return this.applications;
    }

    public List<JobSkillEdge> getSkillEdges() {
        return this.skillEdges;
    }

    public void parseData(String data) {
        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            String category = parts[0];

            switch (category) {
                case "Job Seeker":
                    String[] seekerParts = parts[1].split(" \\(Skills: ");
                    String seekerName = seekerParts[0];
                    String[] skillsAndExperience = seekerParts[1].split(", Experience: ");
                    String[] skills = skillsAndExperience[0].split(", ");
                    int experienceYears = Integer.parseInt(skillsAndExperience[1].replace(" years)", ""));

                    JobSeekerNode jobSeeker = new JobSeekerNode(seekerName, Arrays.asList(skills), experienceYears);
                    jobSeekers.add(jobSeeker);
                    break;
                case "Job":
                    String[] jobParts = parts[1].split(" \\(Company: ");
                    String title = jobParts[0];
                    String[] companyAndLocation = jobParts[1].split(", Location: ");
                    String company = companyAndLocation[0];
                    String location = companyAndLocation[1].split(", Description: ")[0];
                    String description = companyAndLocation[1].split(", Description: ")[1];

                    JobNode job = new JobNode(title, company, location, description);
                    jobs.add(job);
                    break;
                case "Job Application":
                    String[] applicationParts = parts[1].split(" applied for ");
                    String jobSeekerName = applicationParts[0];
                    String[] jobAndCompany = applicationParts[1].split(" at ");
                    String jobTitle = jobAndCompany[0];
                    String applicationCompany = jobAndCompany[1].split(" \\(Status: ")[0];
                    String status = jobAndCompany[1].split(" \\(Status: ")[1].replace(")", "");

                    JobSeekerNode jobSeekerApp = findJobSeekerByName(jobSeekerName);
                    JobNode jobApp = findJobByName(jobTitle);

                    if (jobSeekerApp != null && jobApp != null) {
                        applications.add(new JobApplicationNode(jobSeekerApp, jobApp, applicationCompany, status));
                    } else {
                        System.out.println("Invalid Job Application: " + line);
                    }
                    break;

                case "Job Seeker Skill":
                    String[] skillParts = parts[1].split(" has the following skills required for ");
                    String skillSeekerName = skillParts[0];
                    String[] skillJobAndCompany = skillParts[1].split(" at ");
                    String skillJobTitle = skillJobAndCompany[0];
                    String skillCompany = skillJobAndCompany[1].split(" \\(Required Skills: ")[0];
                    String[] requiredSkills = skillJobAndCompany[1].replace(")", "").split(", ");

                    JobSeekerNode skillSeeker = findJobSeekerByName(skillSeekerName);
                    JobNode skillJob = findJobByName(skillJobTitle);

                    if (skillSeeker != null && skillJob != null) {
                        skillEdges.add(new JobSkillEdge(skillSeeker, skillJob, Arrays.asList(requiredSkills)));
                    } else {
                        System.out.println("Invalid Job Seeker Skill: " + line);
                    }
                    break;
                default:
                    System.out.println("Invalid line: " + line);
                    break;
            }
        }
    }

    private JobSeekerNode findJobSeekerByName(String name) {
        for (JobSeekerNode jobSeeker : jobSeekers) {
            if (jobSeeker.getName().equals(name)) {
                return jobSeeker;
            }
        }
        return null; // JobSeekerNode not found
    }

    private JobNode findJobByName(String name) {
        for (JobNode job : jobs) {
            if (job.getTitle().equals(name)) {
                return job;
            }
        }
        return null; // JobNode not found
    }

    public void createGraph(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }

            parseData(data.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    public void readDataFromFile(String filename) {
        createGraph(filename);
    }

    public void displayGraph() {
        System.out.println("\nJob Applications:");
        if (applications.isEmpty()) {
            System.out.println("No job applications found.");
        } else {
            for (JobApplicationNode application : applications) {
                System.out.println(application.getJobSeeker().getName() + " applied for "
                        + application.getJob().getTitle() + " at " + application.getJob().getCompany() + " (Status: "
                        + application.getStatus() + ")");
            }
        }

        System.out.println("\nJob Skills:");
        if (skillEdges.isEmpty()) {
            System.out.println("No job skills found.");
        } else {
            for (JobSkillEdge skillEdge : skillEdges) {
                System.out.println(skillEdge.getJobSeeker().getName() + " has the following skills required for "
                        + skillEdge.getJob().getTitle() + " at " + skillEdge.getJob().getCompany() + ": "
                        + String.join(", ", skillEdge.getRequiredSkills()));
            }
        }
    }

    public static void main(String[] args) {
        CreateGraph graph = new CreateGraph();
        graph.readDataFromFile("graph_data.txt");
        graph.displayGraph();
    }
}

