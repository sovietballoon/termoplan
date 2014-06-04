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

public class ModelSimple extends Model{
	private Config config;
	/*
	Инициализация модели
	*/
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
		List<Coil> coils_list = new ArrayList<Coil>(coils.values());
		List<Coil> coils_ost = new ArrayList<Coil>();
		
		
		//Сортировка рулонов по: приоритет, ширина, партия
		Collections.sort(coils_list, new Comparator<Coil>() {
            @Override
            public int compare(Coil c1, Coil c2) {
				// "100000 -" для сортировки по убыванию
				// TODO: заменить на max_prior, max_width
				String v1 = String.valueOf(1000000 - c1.prior) + '-' + String.valueOf(100000 - c1.width) + '-' + c1.batch;
				String v2 = String.valueOf(1000000 - c2.prior) + '-' + String.valueOf(100000 - c1.width) + '-' + c1.batch;
                return ((String) v2).compareTo((String) v1);
            }
        });
		
		
		while(coils_list.size() != 0){
			for (Coil coil: coils_list){
				if (furn.size() == 0){
					furn.add(coil);
					furn_regim = coil.regim;
					furn_height = coil.width;
				}else if(coil.regim.equals(furn_regim) 
						&& (furn_height + coil.width) < this.config.FURN_HEIGHT 
						&& furn.size() < this.config.FURN_COIL_MAX){
					furn.add(coil);
					furn_height += coil.width;
				}else{
					coils_ost.add(coil);
				}
			}
			furns.add(furn);
			coils_list = coils_ost;
			furn = new ArrayList<Coil>();
			coils_ost = new ArrayList<Coil>();
		}
		return furns;
	}
}