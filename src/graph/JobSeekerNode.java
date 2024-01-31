package graph;

import java.util.List;

public class JobSeekerNode {
    private String name;
    private List<String> skills;
    private int experienceYears;

    public JobSeekerNode(String name, List<String> skills, int experienceYears) {
        this.name = name;
        this.skills = skills;
        this.experienceYears = experienceYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
}
    
    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}







