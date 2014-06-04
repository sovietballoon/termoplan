package subsys;

//import org.json.JSONObject;
/*
Настройки приложения


*/

public class Config{

	public static String DATAFILE = "data/test_data.csv";
	
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
}