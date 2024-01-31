package graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final int MAX_SKILLS_PER_JOB_SEEKER = 3;
    private static final String[] SKILLS = {"Java", "Python", "C++", "JavaScript", "HTML", "CSS"};

    public static void main(String[] args) {
        String filename = "graph_data.txt";
        generateDataFile(filename);
    }
    
    public static void generateDataFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();

            // Generate job seekers
            List<JobSeekerNode> jobSeekers = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                String name = "JobSeeker " + i;
                List<String> skills = new ArrayList<>();
                int numSkills = random.nextInt(MAX_SKILLS_PER_JOB_SEEKER) + 1;
                for (int j = 0; j < numSkills; j++) {
                    String skill = SKILLS[random.nextInt(SKILLS.length)];
                    skills.add(skill);
                }
                int experienceYears = random.nextInt(10) + 1;

                JobSeekerNode jobSeeker = new JobSeekerNode(name, skills, experienceYears);
                jobSeekers.add(jobSeeker);
                writer.write("Job Seeker: " + jobSeeker.getName() +
                        " (Skills: " + String.join(", ", jobSeeker.getSkills()) +
                        ", Experience: " + jobSeeker.getExperienceYears() + " years)\n");
            }

            // Generate jobs
            List<JobNode> jobs = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                String title = "Job " + i;
                String company = "Company " + i;
                String location = "Location " + i;
                String description = "Description for Job " + i;

                JobNode job = new JobNode(title, company, location, description);
                jobs.add(job);
                writer.write("Job: " + job.getTitle() +
                        " (Company: " + job.getCompany() +
                        ", Location: " + job.getLocation() +
                        ", Description: " + job.getDescription() + ")\n");
            }

         // Generate job applications
            for (JobSeekerNode jobSeeker : jobSeekers) {
                JobNode job = jobs.get(random.nextInt(jobs.size()));
                String applicationCompany = job.getCompany();
                String status = random.nextBoolean() ? "Applied" : "Not Applied";

                JobApplicationNode application = new JobApplicationNode(jobSeeker, job, applicationCompany, status);
                writer.write("Job Application: " + application.getJobSeeker().getName() + " applied for " +
                        application.getJob().getTitle() + " at " + application.getApplicationCompany() +
                        " (Status: " + application.getStatus() + ")\n");
            }


            // Generate skill edges
            for (JobSeekerNode jobSeeker : jobSeekers) {
                JobNode job = jobs.get(random.nextInt(jobs.size()));
                int numSkills = random.nextInt(MAX_SKILLS_PER_JOB_SEEKER) + 1;

                List<String> requiredSkills = new ArrayList<>();
                for (int j = 0; j < numSkills; j++) {
                    String skill = SKILLS[random.nextInt(SKILLS.length)];
                    requiredSkills.add(skill);
                }

                JobSkillEdge skillEdge = new JobSkillEdge(jobSeeker, job, requiredSkills);
                writer.write("Job Seeker: " + skillEdge.getJobSeeker().getName() +
                        " has the following skills required for " + skillEdge.getJob().getTitle()
                        + " at " + skillEdge.getJob().getCompany() +
                        " (Required Skills: " + String.join(", ", skillEdge.getRequiredSkills()) + ")\n");
            }

            System.out.println("Data file has been generated: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while generating the data file.");
            e.printStackTrace();
        }
    }
}
