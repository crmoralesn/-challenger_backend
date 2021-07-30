package cl.binter.challenge.domain.entities;

import lombok.Builder;
import lombok.Data;

/**
 * Text object
 */
@Data
@Builder
public class Text {

    /**
     * id text
     */
	private String id;

    /**
     * title of text
     */
    private String title;

    /**
     * number page
     */
    private Integer page;

    /**
     * total pages
     */
    private Integer totalPages;

    /**
     * text value
     */
    private String text;

}
