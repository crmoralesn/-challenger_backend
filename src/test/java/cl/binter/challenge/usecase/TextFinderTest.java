package cl.binter.challenge.usecase;

import cl.binter.challenge.domain.entities.ScoreTerm;
import cl.binter.challenge.domain.entities.Text;
import cl.binter.challenge.domain.repository.TextRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TextFinderTest {

    @Mock
    private TextRepository textRepository;

    @InjectMocks
    private TextFinder textFinder;

    @BeforeEach
    void setup() {
    }

    @Test
    void matches_success() {
        var textBlock = "Así pues, el doctor era hombre bien conocido, pese a no pertenecer a ninguna institución " +
                "científica, ni a las Reales Sociedades Geográficas de Londres, París, Berlín, Viena o San Petersburgo," +
                " ni al Club de los Viajeros, ni siquiera a la Royal Politechnic Institution, donde su amigo, el " +
                "estadista Kokburn, metía mucho ruido. Un día Kokburn le propuso, para darle gusto, resolver el " +
                "siguiente problema: dado el número de millas recorridas por el doctor alrededor del mundo, cuántas " +
                "millas más ha andado su cabeza que sus pies, teniendo en cuenta la diferencia de los radios O bien, " +
                "conociendo el número de millas recorridas por los pies y por la cabeza del doctor, calcular su " +
                "estatura con toda exactitud.";
        var text = Text.builder().textBlock(textBlock).build();
        when(textRepository.findText(1L, 3)).thenReturn(text);
        List<ScoreTerm> result = textFinder.scoreTerm(1L, 3);
        assertEquals(83, result.size());
        assertEquals(6, result.stream()
                .filter(term -> term.getTerm().equals("el"))
                .findFirst().get().getMatches());
    }
}