package cl.binter.challenge.usecase;

import cl.binter.challenge.domain.entities.ScoreTerm;
import cl.binter.challenge.domain.entities.Text;
import cl.binter.challenge.domain.repository.TextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Text Finder Service
 */
@Service
@Slf4j
public class TextFinder {

	public static final int ONE = 1;
	/**
	 * Text repository
	 */
	private TextRepository textRepository;

	@Autowired
	public void setTextRepository(TextRepository textRepository) {
		this.textRepository = textRepository;
	}


	public Text getText(Long id, Integer page)  {
		if (Objects.isNull(page)) {
			if (Objects.isNull(id)) {
				return getRandomText();
			}
			return textRepository.findText(id);
		}
		return textRepository.findText(id, page);
	}

	public Text getRandomText() {
		var allIds = textRepository.findAllIds();
		var index = new Random().nextInt(allIds.size());
		return textRepository.findText(allIds.get(index));
	}

	/**
	 * Score Term method
	 *
	 * @param id id doc
	 * @param page page of doc
	 * @return List of Score term
	 */
	public List<ScoreTerm> scoreTerm(Long id, Integer page) {
		log.info("[scoreTerm] id {} page {} ", id, page);
		var text = textRepository.findText(id, page);
		log.info("[scoreTerm] text to score {}", text);
		var listCountTerm = buildCountTerm(text.getText());
		log.info("[scoreTerm] Found {}", listCountTerm.size());
		return listCountTerm;
	}

	/**
	 * Build count list of terms
	 *
	 * @param texBlock textblock to evaluate
	 * @return count list of terms
	 */
	private List<ScoreTerm> buildCountTerm(String texBlock) {
		if (Objects.nonNull(texBlock) && texBlock.length() > 0) {
			List<String> terms = Arrays
					.stream(texBlock.replaceAll("[^\\dA-Za-z ]", "").toLowerCase()
					.split(" "))
					.collect(Collectors.toList());
			var termsSet = new HashSet<>(terms);
			var resultSet = new HashSet<ScoreTerm>();
			termsSet.forEach(term -> {
					var count = (int) terms.stream().filter(term::equals).count();
					if (count > ONE) {
						resultSet.add(new ScoreTerm(term, count));
					}
			}
			);
			return resultSet.stream()
					.sorted(Comparator.comparing(ScoreTerm::getMatches)
					.reversed())
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
}
