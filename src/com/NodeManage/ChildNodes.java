package com.NodeManage;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ChildNodes {

	private Set<String> child;

	public ChildNodes() {

		child = new HashSet<>();
	}

	public void addChild(String ele) {
		child.add(ele);
	}

	public Set getChild() {
		return child;
	}

	public void removeChild(String ele) {
		child.remove(ele);
	}

	public int sizeChild() {
		return child.size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (String c : child) {
			sb.append(c + ",");
		}
		return "childs [" + sb.toString() + "]" + '\n';
	}

}
