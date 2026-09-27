package client_task_management.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AITaskService {

    private final ChatClient chatClient;

    public AITaskService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateTasks(String projectDescription) {

        String prompt = """
                You are a project management assistant.

                Based on the following project description,
                generate a list of practical development tasks.

                Project Description:
                %s

                Return only the task list.
                Give 5 to 10 tasks.
                Number each task.
                Keep each task short and clear.
                """.formatted(projectDescription);

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }
}