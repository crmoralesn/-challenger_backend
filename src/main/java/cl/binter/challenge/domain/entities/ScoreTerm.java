package cl.binter.challenge.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Object to dot word matches
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class ScoreTerm {

    /**
     * term match
     */
    private String term;

    /**
     * number of matches
     */
    private Integer matches;

}
