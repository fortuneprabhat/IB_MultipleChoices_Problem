package com.NodeManage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ExceptionHandling.MyException;

public class NodeManager implements NodeList {

	private static NodeManager instance;
	private Map<String, ChildNodes> nodeMap;

	private NodeManager() {
		// nodelist=new ArrayList<>();
		nodeMap = new ConcurrentHashMap<String, ChildNodes>();
		// nodeMap=new HashMap<String, ChildNodes>();
		// nodeMap=new Hashtable<String, ChildNodes>();
		// nodeMap=new TreeMap<>();
	}

	public static NodeManager getInstance() {
		if (instance == null) {
			synchronized (NodeManager.class) {
				if (instance == null) {
					instance = new NodeManager();
				}
			}
		}
		return instance;
	}

	public void createNodeList(List<String> list) {

		try {
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			String[] sp = str.split("->");
			String left = sp[0];
			String right = sp[1];
			if(left.isEmpty()||right.isEmpty()||left==null||right==null)
			{
				
					throw new MyException("Data incorrect at line number-->"+i+1);
				
			}
			ChildNodes childNodes = new ChildNodes();
			if (!nodeMap.containsKey(right)) {
				childNodes.addChild(left);
				nodeMap.put(right, childNodes);
			} else {
				ChildNodes child = nodeMap.get(right);
				child.addChild(left);
			}

		}

		for (int i = 0; i < list.size(); i++) {
			ChildNodes childNodes = new ChildNodes();
			String str = list.get(i);
			String[] sp = str.split("->");
			String left = sp[0];
			if (!nodeMap.containsKey(left)) {
				nodeMap.put(left, childNodes);
			}

		}
		} catch (MyException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void processData() {
		List<String> list = new ArrayList<String>();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (nodeMap.isEmpty())
			return;

		for (Map.Entry<String, ChildNodes> node : nodeMap.entrySet()) {
			if (node.getValue().sizeChild() == 0) {
				System.out.print(node.getKey() + ", ");
				nodeMap.remove(node.getKey());
				// removeChildFromOtherNodes(node);
				list.add(node.getKey());
			}
		}
		System.out.println();
		removeChildFromOtherNodes(list);
		processData();

	}

	private void removeChildFromOtherNodes(List<String> list) {
		for (String str : list)
			for (Map.Entry<String, ChildNodes> node1 : nodeMap.entrySet()) {
				if (node1.getValue().getChild().contains(str)) {
					node1.getValue().getChild().remove(str);
				}
			}

	}

	public void printMap() {
		for (Map.Entry<String, ChildNodes> entry : nodeMap.entrySet()) {
			System.out.println(entry.getKey() + '\t' + entry.getValue());
		}

	}
}
