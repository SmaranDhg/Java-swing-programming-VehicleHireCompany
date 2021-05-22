package DataModels;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType
public class CarModel {

	
	public String getVechile_id() {
		return vechile_id;
	}
	public void setVechile_id(String vechile_id) {
		this.vechile_id = vechile_id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getNo_of_doors() {
		return no_of_doors;
	}
	public void setNo_of_doors(String no_of_doors) {
		this.no_of_doors = no_of_doors;
	}
	public String getFule_type() {
		return fule_type;
	}
	public void setFule_type(String fule_type) {
		this.fule_type = fule_type;
	}
	public String getTop_speed() {
		return top_speed;
	}
	public void setTop_speed(String top_speed) {
		this.top_speed = top_speed;
	}
	public String getDaily_hire_rate() {
		return daily_hire_rate;
	}
	public void setDaily_hire_rate(String daily_hire_rate) {
		this.daily_hire_rate = daily_hire_rate;
	}
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	public CarModel(String vechile_id, String make, String model, String no_of_doors, String fule_type, String top_speed,
			String daily_hire_rate, String registration_no) {
		super();
		this.vechile_id = vechile_id;
		this.make = make;
		this.model = model;
		this.no_of_doors = no_of_doors;
		this.fule_type = fule_type;
		this.top_speed = top_speed;
		this.daily_hire_rate = daily_hire_rate;
		this.registration_no = registration_no;
	}
	public CarModel() {}
	
	@CsvField(pos=1)
	private String vechile_id="None"; 
	@CsvField(pos=2)
	private String make="None";// mean brand of car
	@CsvField(pos=3)
	private String model="None";
	@CsvField(pos=4)
	private String no_of_doors="None";
	@CsvField(pos=5)
	private String fule_type="None";
	@CsvField(pos=6)
	private String top_speed="None";
	@CsvField(pos=7)
	private String daily_hire_rate="None";
	@CsvField(pos=8)
	private String registration_no="None";


	
}
