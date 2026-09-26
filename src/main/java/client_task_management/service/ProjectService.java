package client_task_management.service;

import client_task_management.exception.ResourceNotFoundException;
import client_task_management.entity.Project;
import client_task_management.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project with ID " + id + " not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}