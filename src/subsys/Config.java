package subsys;

//import org.json.JSONObject;
/*
Настройки приложения


*/

public class Config{

	public static String DATAFILE = "data/export.dsv";
	
	public static String DATAFILE_SPLIT = ";";
	
	public static String DATEFORMAT = "dd.MM.yy HH:mm:ss";
	
	// максимальная разница в весе рулонов в стопе < 7т
	public static double FURN_WEIGHT_DIFF_MAX = 7.0;
	
	// высота печи 5400 мм, высота колец 50 мм
	public static int FURN_HEIGHT = 5200;
	
	// в стопе не более 4х рулонов
	public static int FURN_COIL_MAX = 4;
	
	// рулонов толщиной <= 6 мм  в стопе не более 3х шт (т.е. минимум 1 рулон толщиной >0.6мм)
	public static double FURN_ONE_COIL_THINK_MORE = 0.6;
	
	//типы рулонов стали для водородных печей (названия временные)
	public static String TYPE_1 = "1";
	
	public static String TYPE_2 = "2";
	
	public static String TYPE_3 = "3";
	
	public static String TYPE_4 = "4";
	
	public static String TYPE_5 = "5";
	
	public String tySort (String qur_type) {
		String add_type = null;
		if (qur_type.equals(Config.TYPE_1)){
			add_type = Config.TYPE_2;
		}
		if (qur_type.equals(Config.TYPE_2)){
			add_type = Config.TYPE_3;
		}
		if (qur_type.equals(Config.TYPE_3)){
			add_type = Config.TYPE_4;
		}
		if (qur_type.equals(Config.TYPE_4)){
			add_type = Config.TYPE_5;
		}
		if (qur_type.equals(Config.TYPE_5)){
			add_type = Config.TYPE_5;
		}
		return add_type;	
	};
}
