package com.greg._2048.service;

import com.greg._2048.model.Hint;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class HintService {
    private final ChatClient chatClient;
    
    @Qualifier("hint")
    private final StringBuffer answer;
    
    public String getHint(int[][] board) {
        final String message = """
                Playing game 2048. The board is as follows:
                {board}
                What is the best next move?
                Available moves: up,right,down,left.
                Answer with one word.
                """.replace("{board}", Arrays.deepToString(board));
        
        try {
            Hint response = chatClient.prompt()
                    .user(message)
                    .call()
                    .entity(Hint.class);
            answer.append(response.hint());
            
        } catch (Throwable t) {
            answer.append("AI hint is not working. Check your balance.");
            throw new RuntimeException(t);
        }
        
        answer.replace(0, answer.length(), answer.toString());
        
        return answer.toString();
    }
    
    public String getLastHint() {
        return answer.toString();
    }
}
