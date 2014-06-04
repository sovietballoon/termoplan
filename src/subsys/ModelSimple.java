package subsys;

import subsys.*;
import java.util.*;
/*
јбстрактный класс модели расчета

*/

public class ModelSimple extends Model{
	private Config config;
	/*
	»нициализаци€ модели: все параметры дождны быть определены в config-е
	*/
	public ModelSimple(Config config){
		this.config = config;
	}
	
	/*
	«апуск расчета
		возвращает список стоп => список id рулонов
	*/
	public List<List> calculate(Map<Integer, Coil> coils){
		List<List> furns = null;
		List furn;
		String regim;
		//—ортировка рулонов по: приоритет, ширина, парти€
		
		// выбор первого рулона, оперделение группы
		furn.add(coil)
		regim = coil.regim
		
		for (Coil c: coils_sort){
			
		}
		// фильтр списка рулонов с учетом группы
		
		
		return furns;
	}
}