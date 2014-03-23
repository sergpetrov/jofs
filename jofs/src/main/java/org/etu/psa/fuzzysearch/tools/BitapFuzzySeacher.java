package org.etu.psa.fuzzysearch.tools;

import java.util.List;

import org.etu.psa.fuzzysearch.objects.Options;
import org.etu.psa.fuzzysearch.objects.Word;

public class BitapFuzzySeacher extends DictionarySearcher {
	protected final IAlphabet alphabet;

	public BitapFuzzySeacher(String[] dictionary, IAlphabet alphabet, Options options) {
		super(dictionary, options);
		this.alphabet = alphabet;
	}

	public List<Word> search(String searchWord) {
		String needle = searchWord;
		
		final int wordLength = needle.length();
		final int endMask = 1 << wordLength;

		final int[] tableRow = new int[options.getDistance() + 1];
		final int[] wordMask = new int[alphabet.size() + 1];
		for (int i = 0; i < wordLength; ++i) {
			wordMask[normalizeChar(needle.charAt(i))] |= 1 << i;
		}
		
		readDictionary(new WordReader() {
			public void read(String word) {
				for (int i = 0; i <= options.getDistance(); ++i)
					tableRow[i] = 1;

				for (int i = 0; i < word.length(); ++i) {
					char ch = word.charAt(i);
					int charMask = wordMask[normalizeChar(ch)];
					int oldTableCell = 0;
					int nextOldTableCell = 0;
					for (int d = 0; d <= options.getDistance(); ++d) {
						int newSubstitutionTableCell = (oldTableCell | (tableRow[d] & charMask)) << 1;
						int newInsertionTableCell = oldTableCell | ((tableRow[d] & charMask) << 1);
						int newDeletionTableCell = (nextOldTableCell | (tableRow[d] & charMask)) << 1;
						int newTableCell = newSubstitutionTableCell | newInsertionTableCell | newDeletionTableCell | 1;
						oldTableCell = tableRow[d];
						tableRow[d] = newTableCell;
						nextOldTableCell = newTableCell;
					}
					
					if ((tableRow[options.getDistance()] & endMask) > 0) {
						result.add(new Word(word, i));
						break;
					}
				}
			}
		});
		return result;
	}
	
	private int normalizeChar(char ch) {
		int value = alphabet.mapChar(ch);
		if (value < 0) value = alphabet.size();
		return value;
	}

	public String getLabel() {
		return getClass().getSimpleName();
	}

}
