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
public class Model{
private Config config;
	
	public Model(Config config){
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
		boolean flag = false;
		int n_valid_thick = 0, n = 0, reg_1 = 0, reg_2 = 0, reg_3 = 0, reg_4 = 0, reg_5 = 0, kol = 0, numb = 1;
		String qur_type = null;
		String add_type = null;
		
		//Сортировка рулонов по: приоритет, ширина, партия
		Collections.sort(coils_list, new Comparator<Coil>() {
           // @Override
            public int compare(Coil c1, Coil c2) {
				// "100000 -" для сортировки по убыванию
				// TODO: заменить на max_prior, max_width
				String v1 = String.valueOf(Math.abs(1000000 - c1.prior)) + '-' + String.valueOf(100000 - c1.width) + '-' + c1.batch;
				String v2 = String.valueOf(Math.abs(1000000 - c2.prior)) + '-' + String.valueOf(100000 - c1.width) + '-' + c1.batch;
                return ((String) v2).compareTo((String) v1);
            }
        });
		/* Метод в следующем: резервируем (присваиваем значение use = 1) 
		столько рулонов толщиной >= заданной, чтобы они были равны среднему кол-ву рулонов 
		(принимаем, что мы собираем стопы по 4 рулона), 
		при этом устанавливаем их для каждого типа.
		*/
		n_valid_thick = coils.size() / 4;
		//System.out.printf("chislo zarezervirovannuh rulonov = " + n_valid_thick + "\n");
		
		for (Coil coil: coils_list){
			coil.use = 0;
			if (coil.regim.equals("1")) 
				reg_1 ++;
				else if (coil.regim.equals("2"))
					reg_2 ++;
					else if (coil.regim.equals("3"))
						reg_3 ++;
						else if (coil.regim.equals("4"))
							reg_4 ++;
							else if (coil.regim.equals("5"))
								reg_5 ++;
		}
		reg_1 = reg_1 / 4;
		reg_2 = reg_2 / 4;
		reg_3 = reg_3 / 4;
		reg_4 = reg_4 / 4;
		reg_5 = reg_5 / 4;
		
		//System.out.printf("regim_1 (" + reg_1 + ") regim_2 (" + reg_2 + ") regim_3 (" + reg_3 + ") regim_4 (" + reg_4 + ") regim_5 (" + reg_5 + ")\n");
			for (Coil coil: coils_list){
				if (coil.regim.equals("1")
						&& reg_1 > 0){
					reg_1 --;
					
				}
					else if (coil.regim.equals("2")
							&& reg_2 > 0){
						reg_2 --;
						
					}
						else if (coil.regim.equals("3")
								&& reg_3 > 0){
							reg_3 --;
							
						}
							else if (coil.regim.equals("4")
									&& reg_4 > 0){
								reg_4 --;
								
							}
								else if (coil.regim.equals("5")
										&& reg_5 > 0){
									reg_5 --;
									
								}
								else continue;
				if (coil.thick > Config.FURN_ONE_COIL_THINK_MORE
						&& n_valid_thick > 0){
					coil.use = 1;
					n_valid_thick--;
					//System.out.printf("valid roll (" + coil.prior + " ; " + coil.use + ")\n");
					//if (coil.use ==1)
					n++;
					
				}
				else continue;
			}
		//System.out.printf("chislo zarezervirovannuh rulonov = " + n + "\n");
		
		while(coils_list.size() != 0){
			for (Coil coil: coils_list){
				if (furn.size() == 0
						&& coil.thick < Config.FURN_ONE_COIL_THINK_MORE){
					coils_ost.add(coil);
					flag = true;
				}
				else if (furn.size() == 0
						&& coil.thick > Config.FURN_ONE_COIL_THINK_MORE
						){
					furn.add(coil);
					furn_regim = coil.regim;
					furn_height = coil.width;
					weight_difference = coil.weight;
					//System.out.printf(numb + " prior " + coil.prior + " regime " + coil.regim + " thick_1 = " + coil.thick + " width " + coil.width + " weight " + coil.weight + " ");					
					numb++;
					qur_type = coil.regim;
					add_type = config.tySort(qur_type);
					
				}else if((coil.regim.equals(furn_regim) ||  coil.regim.equals(add_type))
						&& (furn_height + coil.width) < Config.FURN_HEIGHT 
						&& furn.size() < Config.FURN_COIL_MAX
						&& Math.abs(coil.weight - weight_difference) < Config.FURN_WEIGHT_DIFF_MAX
						&& coil.use == 0
						){					
					furn.add(coil);
					furn_height += coil.width;
					//System.out.printf("thick_n = " + coil.thick + " weight " + coil.weight + " ");
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
				for (Coil coil: coils_list){
					int  regim = coil.regim.length();
				System.out.printf("id " + coil.id + " regime " + regim + " prior " + coil.prior + " thick " + coil.thick + " width " + coil.width + " weight " + coil.weight + "use " + coil.use + "\n");
				}
				break;
			}
		}
		//System.out.printf("\n");
		return furns;
	}	
}
