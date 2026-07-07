package com.greg._2048;

import com.greg._2048.model.Hint;
import com.greg._2048.service.Hinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class HinterTest {
    
    private Hinter hinter;
    
    @Mock
    private ChatClient chatClient;
    
    @Mock
    private ChatClient.ChatClientRequestSpec chatClientRequestSpec;
    
    @Mock
    private ChatClient.CallResponseSpec callResponseSpec;
    
    @BeforeEach
    void setUp() {
        hinter = new Hinter(chatClient, new StringBuffer());
    }
    
    @Test
    @DisplayName("Basic hinter test")
    void shouldReturnHint() {
        // given
        final int[][] board = {
                {2, 4, 8, 16},
                {16, 8, 4, 2},
                {2, 4, 8, 16},
                {16, 8, 4, 16}
        };
        final String expected = "up";
        given(chatClient.prompt()).willReturn(chatClientRequestSpec);
        given(chatClientRequestSpec.user(anyString())).willReturn(chatClientRequestSpec);
        given(chatClientRequestSpec.call()).willReturn(callResponseSpec);
        given(callResponseSpec.entity(Hint.class)).willReturn(new Hint(expected));
        
        // when
        String actual = hinter.getHint(board);
        
        // then
        assertThat(actual).isEqualTo(expected);
    }
}