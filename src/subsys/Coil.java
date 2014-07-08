package subsys;

import java.util.Formatter;
import java.util.Date;

/*
Контейнер информации о рулоне

*/
public class Coil{
	public int id;
	public double thick;
	public int width;
	public String regim;
	public Date prod_date;
	public String batch;
	public double weight;
	public int prior;
	public int use;
	
	public Coil(int id, double thick, int width, String batch, double weight, Date prod_date, int prior, String regim, int use){
		this.id = id;
		this.thick = thick;
		this.width = width;
		this.regim = regim;
		this.prod_date = prod_date;
		this.batch = batch;
		this.prior = prior;
		this.regim = regim;
		this.use = use;
	}
	
	public String toString(){
		Formatter f = new Formatter();
		f.format("Coil(id=%s, thick=%s, width=%s, regim=%s)", id, thick, width, regim);
		return f.toString();
	}
}
