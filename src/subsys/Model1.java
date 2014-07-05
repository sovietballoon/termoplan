package subsys;

import subsys.*;

import java.util.*;

/*
Абстрактный класс модели расчета


TODO:
- Резервировать кол-во рулонов, соответствующее среднему кол-ву стопок. Проверить, повысит ли это КПД.
- Разработать алгоритм, который после создания всех стоп будет добавлять к ним оставшиеся рулоны, если это необходимо. 
	
*/
//abstract 
public class Model1{
private Config config;
	
	public Model1(Config config){
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
		List<Coil> coils_list = new ArrayList<Coil>(coils.values());
		List<Coil> coils_ost = new ArrayList<Coil>();
		//TypeChanger tch = new TypeChanger();
		String addittype = null;
		boolean flag = false;
		
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
						
		while(coils_list.size() != 0){
			for (Coil coil: coils_list){
				if (furn.size() == 0
						&& coil.thick < config.FURN_ONE_COIL_THINK_MORE){
					
					coils_ost.add(coil);
					flag = true; // флаг на выход из общего цикла. Условия будут дорабатываться. Суть в выходе из циклов, когда элементов с необходмой толщиной уже не будет. 
				}
				else if (furn.size() == 0
						&& coil.thick > config.FURN_ONE_COIL_THINK_MORE){
					furn.add(coil);
					furn_regim = coil.regim;
					furn_height = coil.width;
					weight_difference = coil.weight;
					//addittype = tch.typeChanger(coil.regim);
					if (coil.regim.equals("1")) // конечно же, надо придумать более адекватную модель сравнения режимов относительно данных в конфиге, но пока так
						addittype = "2";
						else if (coil.regim.equals("2"))
							addittype = "3";
							else if (coil.regim.equals("3"))
								addittype = "4";
								else if (coil.regim.equals("4"))
									addittype = "5";
									else if (coil.regim.equals("5"))
										addittype = "5";
				}else if((coil.regim.equals(furn_regim) ||  coil.regim.equals(addittype))
						&& (furn_height + coil.width) < this.config.FURN_HEIGHT 
						&& furn.size() < this.config.FURN_COIL_MAX
						&& Math.abs(coil.weight - weight_difference) < config.FURN_WEIGHT_DIFF_MAX){
					//weight_difference = coil.weight;
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
			if (flag)
				break;
		}
		return furns;
	}


//	private void average() {
		// TODO Auto-generated method stub
		
	}
	/*
	Инициализация модели: все параметры дождны быть определены в config-е
	*/
	//abstract public Model(Config config);
	
	/*
	Запуск расчета
		возвращает список стоп => список id рулонов
	*/
	//abstract public List<List> calculate(Map<Integer, Coil> coils);
//}
