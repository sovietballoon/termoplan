package app;

import java.util.*;
import java.util.logging.*;
import subsys.*;

public class ConsoleApplication {
 
	public static void main(String[] args) {
		Map<Integer, Coil> coils ;
		
		// Init Logger
		Logger log = Logger.getLogger(ConsoleApplication.class.getName());
		log.info("Start");
		try{
			// Init Config
			Config config = new Config();
			// Load coils
			coils = LoadCSV.load(config.DATAFILE, config.DATAFILE_SPLIT, config.DATEFORMAT);
			log.info("Load " + coils.size() + " coils");
			
			// init model
			//ModelSimple model = new ModelSimple(config);
			
			//List<List> Output = model.calculate(coils);
			
		}catch(Exception  e){
			log.log(Level.SEVERE, "Exception: ", e);
		}
	}
}

/*
TODO: 
+ Config
+ Add date to coils
+ Readme
Model
*/