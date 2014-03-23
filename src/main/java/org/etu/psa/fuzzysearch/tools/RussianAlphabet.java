package org.etu.psa.fuzzysearch.tools;

public class RussianAlphabet extends SimpleAlphabet {

	public RussianAlphabet() {
		super('А', 'Я');
	}

	@Override
	public int mapChar(char ch) {
		if (ch == 'Ё') ch = 'Е';
		return super.mapChar(ch);
	}
}
