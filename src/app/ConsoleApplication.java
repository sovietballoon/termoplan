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
			ModelSimple model1 = new ModelSimple(config);
			Model1 model = new Model1(config);
			List<List> output = model.calculate(coils);
			
			// Вывод результата, оценка алгоритма
			String regim = "";
			double kpd = 0;
			double kpd_all = 0;
			int coil_count = 0;
			int width_sum = 0;
			int width = 0;
			
			for (int i = 0; i < output.size(); i++){
				List<Coil> furn = output.get(i);
				for(Coil c: furn){
					width += c.width;
					regim += c.regim + " ";
					coil_count += 1;
				}
				kpd = (double)width / config.FURN_HEIGHT * 100;
				System.out.printf("furn:%s kpd:%.1f%% width:%s regim:%s \n", (i + 1), kpd, width, regim);
				width_sum += width;
				width = 0;
				regim = "";
			}
			kpd = (double)width_sum / (config.FURN_HEIGHT * output.size()) * 100;
			System.out.printf("\nAll furns %s, All coils %s, All KPD %.1f %%\n", output.size(), coil_count, kpd);
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
