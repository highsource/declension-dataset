package org.hisrc.declension.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TrieTest {

	@Test
	public void calculatesCommonPrefix() {
		Trie trie = new Trie();
		assertThat(trie.getCommonPrefix()).isEqualTo("");

		trie.add("abc");

		assertThat(trie.getCommonPrefix()).isEqualTo("abc");

		trie.add("ab");
		assertThat(trie.getCommonPrefix()).isEqualTo("ab");

		trie.add("acb");
		assertThat(trie.getCommonPrefix()).isEqualTo("a");

		trie.add("bcd");
		assertThat(trie.getCommonPrefix()).isEqualTo("");
	}
}