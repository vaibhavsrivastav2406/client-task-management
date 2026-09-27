package client_task_management.controller;

import client_task_management.service.AITaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AITaskController {

    private final AITaskService aiTaskService;

    public AITaskController(AITaskService aiTaskService) {
        this.aiTaskService = aiTaskService;
    }

    @PostMapping("/generate-tasks")
    public String generateTasks(@RequestBody String projectDescription) {

        return aiTaskService.generateTasks(projectDescription);
    }
}