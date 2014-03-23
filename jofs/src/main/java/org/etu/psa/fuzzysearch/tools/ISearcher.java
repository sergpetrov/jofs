package org.etu.psa.fuzzysearch.tools;

import java.util.List;

import org.etu.psa.fuzzysearch.objects.Word;

public interface ISearcher extends Labeled {

	public List<Word> search(String searchWord);

}
