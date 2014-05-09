package org.etu.psa.fuzzysearch;

import java.util.ArrayList;
import java.util.List;

import org.etu.psa.fuzzysearch.objects.Options;
import org.etu.psa.fuzzysearch.objects.Word;
import org.etu.psa.fuzzysearch.tools.BitapFuzzySearcher;
import org.etu.psa.fuzzysearch.tools.RussianAlphabet;
import org.etu.psa.fuzzysearch.tools.editdistance.DamerauLevensteinDistance;
import org.etu.psa.fuzzysearch.tools.editdistance.EditDistanceSearcher;
import org.etu.psa.fuzzysearch.tools.editdistance.IEditDistance;
import org.etu.psa.fuzzysearch.tools.editdistance.LevensteinDistance;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class SearchTest {

	private final Logger LOG = LoggerFactory.getLogger(SearchTest.class.getSimpleName());
	private final List<String> dictionary = new ArrayList<String>();
	private final Options options = new Options();
	private final String searchWord = "ХЕЛБ";
	
	@Before
	public void setUp() {
		dictionary.add("ХЛЕВ"); 
		dictionary.add("КЛЕВ");
		dictionary.add("ХЛЕБ");
		options.setDistance(2);
	}
	
	@Test
	public void testLevensteinDistanceSearcher() {
		IEditDistance dlEditDistance = new LevensteinDistance();
		EditDistanceSearcher searcher = new EditDistanceSearcher(dictionary, dlEditDistance, options);
		LOG.info("LevensteinDistance result: ");
		List<Word> result = searcher.search(searchWord);
		for (Word word : result) {
			LOG.info(word.getValue());
		}
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void testDamerauLevensteinDistanceSearcher() {
		IEditDistance dlEditDistance = new DamerauLevensteinDistance();
		EditDistanceSearcher searcher = new EditDistanceSearcher(dictionary, dlEditDistance, options);
		LOG.info("DamerauLevensteinDistance result: ");
		List<Word> result = searcher.search(searchWord);
		for (Word word : result) {
			LOG.info(word.getValue());
		}
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void testBitapFuzzySeacher() {
		BitapFuzzySearcher searcher = new BitapFuzzySearcher(dictionary, new RussianAlphabet(), options);
		LOG.info("BitapFuzzy result: ");
		List<Word> result = searcher.search(searchWord);
		for (Word word : result) {
			LOG.info(word.getValue());
		}
		assertTrue(result.size() > 0);
	}
}
