package subsys;

import subsys.*;

import java.util.*;
/*
Абстрактный класс модели расчета

Пример расчета. Простой перебор
TODO: 
 - смежные режимы
 - ограничения по толщине
 - разбирать неукомплектованные стопы
*/

public class ModelSimple{ //extends Model
	private Config config;
	
	public ModelSimple(Config config){
		this.config = config;
	}
	
	/*
	Запуск расчета
		возвращает список стоп => список id рулонов
	*/
	public List<List> calculate(Map<Integer, Coil> coils){
		List<List> furns = new ArrayList<List>();
		List<Coil> furn = new ArrayList<Coil>();
		String furn_regim = "0";
		Integer furn_height = 0;
		
		
		double weight_difference = 0;
		String qur_type = null;
		String add_type = null;
		int kol = 0;
		
		
		List<Coil> coils_list = new ArrayList<Coil>(coils.values());
		List<Coil> coils_ost = new ArrayList<Coil>();
		//Сортировка рулонов по: приоритет, ширина, партия
		Collections.sort(coils_list, new Comparator<Coil>() {
           // @Override
            public int compare(Coil c1, Coil c2) {
				// "100000 -" для сортировки по убыванию
				// TODO: заменить на max_prior, max_width
				String v1 = String.valueOf(1000000 - c1.prior) + '-' + String.valueOf(100000 - c1.width) + '-' + c1.batch;
				String v2 = String.valueOf(1000000 - c2.prior) + '-' + String.valueOf(100000 - c1.width) + '-' + c1.batch;
                return ((String) v2).compareTo((String) v1);
            }
        });
		/*for (Coil coil: coils_list){
		if (coil.thick > Config.FURN_ONE_COIL_THINK_MORE
				){
			coil.use = 1;
			//System.out.printf("valid roll (" + coil.prior + " ; " + coil.use + ")\n");
			//if (coil.use ==1)
			
			
		}
		else {
			coil.use = 0;
			continue;
		}
		}*/
		while(coils_list.size() != 0){
			for (Coil coil: coils_list){
					if (furn.size() == 0
							&& coil.thick < Config.FURN_ONE_COIL_THINK_MORE){
						coils_ost.add(coil);
						
					}
					else if (furn.size() == 0
							&& coil.thick > Config.FURN_ONE_COIL_THINK_MORE
							){
					furn.add(coil);
					furn_regim = coil.regim;
					furn_height = coil.width;
					weight_difference = coil.weight;
					//System.out.printf("prior " + coil.prior + " regim " + coil.regim + " thick_1 = " + coil.thick + " width " + coil.width + " weight " + coil.weight + " ");
				
					qur_type = coil.regim;
					add_type = config.tySort(qur_type);
					
					}else if((coil.regim.equals(furn_regim) ||  coil.regim.equals(add_type))
							&& (furn_height + coil.width) < Config.FURN_HEIGHT 
							&& furn.size() < Config.FURN_COIL_MAX
							&& Math.abs(coil.weight - weight_difference) < Config.FURN_WEIGHT_DIFF_MAX
							){
					furn.add(coil);
					furn_height += coil.width;
				}else{
					coils_ost.add(coil);
				}
			}
			//System.out.printf("\n");
			furns.add(furn);
			coils_list = coils_ost;
			furn = new ArrayList<Coil>();
			coils_ost = new ArrayList<Coil>();
			kol = 0;
			for (Coil coil: coils_list){
				if (coil.thick > Config.FURN_ONE_COIL_THINK_MORE) break;
				else kol += 1;			
			}
			if (kol == coils_list.size()) {
				
				break;
			}
		}
		return furns;
	}
}
