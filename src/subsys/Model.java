package subsys;

import subsys.*;
import java.util.*;

/*
����������� ����� ������ �������

*/

abstract public class Model{
	/*
	������������� ������: ��� ��������� ������ ���� ���������� � config-�
	*/
	//abstract public Model(Config config);
	
	/*
	������ �������
		���������� ������ ���� => ������ id �������
	*/
	abstract public List<List> calculate(Map<Integer, Coil> coils);
}