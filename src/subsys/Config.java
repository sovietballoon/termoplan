package subsys;

//import org.json.JSONObject;
/*
��������� ����������


*/

public class Config{

	public static String DATAFILE = "data/test_data.csv";
	
	public static String DATAFILE_SPLIT = ";";
	
	public static String DATEFORMAT = "dd.MM.yy HH:mm:ss";
	
	// ������������ ������� � ���� ������� � ����� < 7�
	public static double FURN_WEIGHT_DIFF_MAX = 7.0;
	
	// ������ ���� 5400 ��, ������ ����� 50 ��
	public static int FURN_HEIGHT = 5200;
	
	// � ����� �� ����� 4� �������
	public static int FURN_COIL_MAX = 4;
	
	// ������� �������� <= 6 ��  � ����� �� ����� 3� �� (�.�. ������� 1 ����� �������� >0.6��)
	public static double FURN_ONE_COIL_THINK_MORE = 0.6;
}