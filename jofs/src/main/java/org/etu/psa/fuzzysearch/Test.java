package org.etu.psa.fuzzysearch;

import org.etu.psa.fuzzysearch.objects.Options;
import org.etu.psa.fuzzysearch.objects.Word;
import org.etu.psa.fuzzysearch.tools.BitapFuzzySeacher;
import org.etu.psa.fuzzysearch.tools.ISearcher;
import org.etu.psa.fuzzysearch.tools.RussianAlphabet;
import org.etu.psa.fuzzysearch.tools.editdistance.DamerauLevensteinDistance;
import org.etu.psa.fuzzysearch.tools.editdistance.IEditDistance;
import org.etu.psa.fuzzysearch.tools.editdistance.EditDistanceSearcher;
import org.etu.psa.fuzzysearch.tools.editdistance.LevensteinDistance;

public class Test {

	public static void main(String[] args) {
		String[] dictionary = { "укеб", "йкеб", "укеа"};
		Options options = new Options(2);
		
		IEditDistance dlEditDistance = new LevensteinDistance();
		IEditDistance lEditDistance = new DamerauLevensteinDistance();
		
		ISearcher[] searchers = {new EditDistanceSearcher(dictionary, dlEditDistance, options),  
				new EditDistanceSearcher(dictionary, lEditDistance, options),
				new BitapFuzzySeacher(dictionary, new RussianAlphabet(), options)
		};
				
		for(ISearcher searcher : searchers) {
			System.out.println(searcher.getLabel());
			System.out.println("result:");
			for(Word word : searcher.search("уека")) {
				System.out.println(word);
			}
			System.out.println("\n");
		}

	}
}
