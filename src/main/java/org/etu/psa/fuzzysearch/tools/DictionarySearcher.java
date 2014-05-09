package org.etu.psa.fuzzysearch.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.etu.psa.fuzzysearch.objects.Options;
import org.etu.psa.fuzzysearch.objects.Word;

public abstract class DictionarySearcher implements ISearcher {
	protected List<String> dictionary;
	protected List<Word> result;
	protected Options options;
	
	public DictionarySearcher(List<String> dictionary, Options options) {
		this.dictionary = dictionary;
		this.options = options;
		result = new ArrayList<Word>();
	}
	
	protected void readDictionary(WordReader reader) {
		for(String word : dictionary) {
			reader.read(word);
		}
		Collections.sort(result, new Comparator<Word>() {
			public int compare(Word o1, Word o2) {
				if(o1.getPriority() < o2.getPriority()) return -1;
				if(o1.getPriority() > o2.getPriority()) return 1;
				return 0;
			}
		});
	}
	
	protected interface WordReader {
		public void read(String word);
	}
}
