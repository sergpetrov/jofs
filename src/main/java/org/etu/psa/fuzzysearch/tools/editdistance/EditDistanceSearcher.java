package org.etu.psa.fuzzysearch.tools.editdistance;

import java.util.List;

import org.etu.psa.fuzzysearch.objects.Options;
import org.etu.psa.fuzzysearch.objects.Word;
import org.etu.psa.fuzzysearch.tools.DictionarySearcher;

public class EditDistanceSearcher extends DictionarySearcher {
	private IEditDistance editDistance;
	
	public EditDistanceSearcher(List<String> dictionary, IEditDistance editDistance, Options options) {
		super(dictionary, options);
		this.editDistance = editDistance;
	}

	public List<Word> search(final String searchWord) {
		readDictionary(new WordReader() {
			public void read(String word) {
				int distance = editDistance.getDistance(word, searchWord);
				if(distance <= options.getDistance()) {
					result.add(new Word(word, distance));
				}	
			}
		});
		return result;
	}

	public String getLabel() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append(" ")
		  .append(editDistance.getClass().getSimpleName());
		return sb.toString();
	}

}
