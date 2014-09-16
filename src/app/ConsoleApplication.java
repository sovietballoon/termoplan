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
			coils = LoadCSV.load(Config.DATAFILE, Config.DATAFILE_SPLIT, Config.DATEFORMAT);
			log.info("Load " + coils.size() + " coils");
			
			// init model
			ModelSimple model = new ModelSimple(config);
			Model model1 = new Model(config);
			// Вывод результата, оценка алгоритма
			
			funcOutput(model.calculate(coils));
			funcOutput(model1.calculate(coils));
			}catch(Exception  e){
			log.log(Level.SEVERE, "Exception: ", e);
		}	
	}

	private static void funcOutput(List<List> calculate) {
		List<List> output = (List<List>) calculate;
		String regim = "";
		double kpd = 0;
		double kpd_all = 0;
		int coil_count = 0;
		int width_sum = 0;
		int width = 0;
		String thick = "";
		
		for (int i = 0; i < output.size(); i++){
			List<Coil> furn = output.get(i);
			//System.out.println(output.get(i));
			for(Coil c: furn){
				width += c.width;
				regim += c.regim + " ";
				thick += "("+ c.thick + ");";
				coil_count += 1;
			}
			kpd = (double)width / Config.FURN_HEIGHT * 100;
			System.out.printf("furn:%s kpd:%.1f%% width:%s regim:%s thick:%s \n", (i + 1), kpd, width, regim, thick);
			width_sum += width;
			width = 0;
			regim = "";
			thick = "";
		}
		kpd = (double)width_sum / (Config.FURN_HEIGHT * output.size()) * 100;
		System.out.printf("\nAll furns %s, All coils %s, All KPD %.1f %%\n", output.size(), coil_count, kpd);
		
	}
}

/*
TODO: 
+ Config
+ Add date to coils
+ Readme
Model
*/
