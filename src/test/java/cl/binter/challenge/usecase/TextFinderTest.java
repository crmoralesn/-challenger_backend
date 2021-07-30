package cl.binter.challenge.usecase;

import cl.binter.challenge.domain.entities.Text;
import cl.binter.challenge.domain.repository.TextRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.when;

@SpringBootTest
public class TextFinderTest {

    @Mock
    private TextRepository textRepository;

    @BeforeEach
    void setup() {
    }

    @Test
    void matches_success() {
        var text = Text.builder()
                .textBlock("Esta es una prueba, donde encontraremos las palabras repetidas").build();
        when(textRepository.findText(10L, 1)).thenReturn(text);

    }
}