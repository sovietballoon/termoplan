package subsys;

import subsys.*;
import java.util.*;

/*
јбстрактный класс модели расчета

*/

abstract public class Model{
	/*
	»нициализаци€ модели: все параметры дождны быть определены в config-е
	*/
	//abstract public Model(Config config);
	
	/*
	«апуск расчета
		возвращает список стоп => список id рулонов
	*/
	abstract public List<List> calculate(Map<Integer, Coil> coils);
}