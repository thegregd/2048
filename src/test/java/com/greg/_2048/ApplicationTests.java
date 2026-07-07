package com.greg._2048;

import com.greg._2048.model.Game;
import com.greg._2048.service.InProgress;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.client.RestTestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureRestTestClient
class ApplicationTests {
    
    @Autowired
    private RestTestClient restTestClient;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    @DisplayName("Should start a game and make moves in all directions.")
    void shouldMakeAllMoves() {
        final int[][] zeroBoard = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        
        RestTestClient.BodySpec<Game, ?> newGameSpec = restTestClient
                .post()
                .uri("/game/start")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Game.class);
                
        Game newGame = newGameSpec.returnResult().getResponseBody();
        assertThat(newGame.board()).isNotEmpty().isNotEqualTo(zeroBoard);
        assertThat(newGame.score()).isEqualTo(0);
        assertThat(newGame.status()).isEqualTo(new InProgress("New game started."));
        
        RestTestClient.BodySpec<Game, ?> movedUpSpec = restTestClient
                .post()
                .uri("/game/move/up")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Game.class);
        
        Game movedUpGame = movedUpSpec.returnResult().getResponseBody();
        assertThat(movedUpGame.board()).isNotEmpty().isNotEqualTo(zeroBoard);
        assertThat(movedUpGame.score()).isGreaterThanOrEqualTo(0);
        assertThat(movedUpGame.status()).isEqualTo(new InProgress("Carry on."));
        
        RestTestClient.BodySpec<Game, ?> movedRightSpec = restTestClient
                .post()
                .uri("/game/move/right")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Game.class);
        
        Game movedRightGame = movedRightSpec.returnResult().getResponseBody();
        assertThat(movedRightGame.board()).isNotEmpty().isNotEqualTo(zeroBoard);
        assertThat(movedRightGame.score()).isGreaterThanOrEqualTo(0);
        assertThat(movedRightGame.status()).isEqualTo(new InProgress("Carry on."));
        
        RestTestClient.BodySpec<Game, ?> movedDownSpec = restTestClient
                .post()
                .uri("/game/move/down")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Game.class);
        
        Game movedDownGame = movedDownSpec.returnResult().getResponseBody();
        assertThat(movedDownGame.board()).isNotEmpty().isNotEqualTo(zeroBoard);
        assertThat(movedDownGame.score()).isGreaterThanOrEqualTo(0);
        assertThat(movedDownGame.status()).isEqualTo(new InProgress("Carry on."));
        
        RestTestClient.BodySpec<Game, ?> movedLeftSpec = restTestClient
                .post()
                .uri("/game/move/left")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Game.class);
        
        Game movedLeftGame = movedLeftSpec.returnResult().getResponseBody();
        assertThat(movedLeftGame.board()).isNotEmpty().isNotEqualTo(zeroBoard);
        assertThat(movedLeftGame.score()).isGreaterThanOrEqualTo(0);
        assertThat(movedLeftGame.status()).isEqualTo(new InProgress("Carry on."));
        
    }
}
