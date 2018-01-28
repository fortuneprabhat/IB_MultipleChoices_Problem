package com.Main;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.NodeManage.NodeManager;

public class Main {

	static Logger logger = Logger.getLogger("Main.class");  
	static FileHandler fh;  
	public static void main(String[] args) throws SecurityException, IOException {
		fh = new FileHandler("D:/Amadeus/temp/logger/Ibm_%g.log",1000,4);  
        logger.addHandler(fh);
        
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
		/*LogManager lm = LogManager.getLogManager();
		Logger logger=lm.getLogger("Main.class");
		 Logger logger=LogManager.getLogManager().getLogger("Main.class");*/
		logger.log(Level.INFO,"Program Begins");
		//List<String> list = FileReaders.readFile("D:\\APrabhat\\Naukri\\WorkSpace\\IBM_Problem\\SourceFile\\input.txt");
		List<String> list = FileReaders.readFile("SourceFile\\input.txt");
		//list.forEach(System.out::println);
		NodeManager manager = NodeManager.getInstance();
		manager.createNodeList(list);
		//manager.printMap();
		System.out.println("----------------------->Print Output---------------");
		manager.processData();
		System.out.println("-------------------END---------------------------");
	}

}
