package subsys;

import subsys.Coil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

/*
 Загрузка доступных рулонов из фацла
 Возвращает в формате Map<Integer, Coil>
 
*/
public class LoadCSV{

	public static Map<Integer, Coil> load(String fname, String cvsSplitBy, String DtFormat) throws IOException, ParseException{
		BufferedReader br;
		String line;
		Map<Integer, Coil> coils = new HashMap<Integer, Coil>();
		DateFormat df = new SimpleDateFormat(DtFormat);
		
		br = new BufferedReader(new FileReader(fname));
		int n =0;
		try{
			while ((line = br.readLine()) != null) {
				String[] field = line.split(cvsSplitBy);
				//"UNIT_ID";"THICK";"WIDTH";"MELT_NUM";"MELT_YEAR";"BATCH_NUM";"PROD_WEIGHT";"DATE_PROD";"PRIO";"REGIM"
				Coil c = new Coil(Integer.valueOf(field[0]), 
									Double.valueOf(field[1].replace(',', '.')), 
									Integer.valueOf(field[2]), 
									field[4] + "-" + field[3] + "-" +  field[5],
									Double.valueOf(field[6].replace(',', '.')), 
									df.parse(field[7]),
									Integer.valueOf(field[8].substring(1, field[8].length() - 1)), 
									field[9].substring(1, field[9].length() - 1),
									n);
				coils.put(c.id, c);
			}
		}finally{
			br.close();
		}
		return coils;
	}
}
