package org.hisrc.declension.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

public class Trie {

	private class TrieNode {
		private boolean terminal = false;
		private final Map<Character, TrieNode> next = new HashMap<>();
	}

	private TrieNode root = new TrieNode();

	public String getCommonPrefix() {
		final StringBuilder sb = new StringBuilder();
		TrieNode currentNode = root;
		while (!currentNode.terminal && currentNode.next.size() == 1) {
			Entry<Character, TrieNode> singleSubTrieNodeEntry = currentNode.next.entrySet().iterator().next();
			sb.append(singleSubTrieNodeEntry.getKey());
			currentNode = singleSubTrieNodeEntry.getValue();
		}
		return sb.toString();
	}

	public void add(String str) {
		Objects.requireNonNull(str, "str must not be null.");
		TrieNode currentNode = root;
		final char[] chs = str.toCharArray();
		for (Character ch : chs) {
			TrieNode nextNode = currentNode.next.get(ch);
			if (nextNode == null) {
				nextNode = new TrieNode();
				currentNode.next.put(ch, nextNode);
			}
			currentNode = nextNode;
		}
		currentNode.terminal = true;
	}
}