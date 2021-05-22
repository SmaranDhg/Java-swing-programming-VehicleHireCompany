package DataModels;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType
public class MbusModel {
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
	public String getTop_speed() {
		return top_speed;
	}
	public void setTop_speed(String top_speed) {
		this.top_speed = top_speed;
	}
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	public String getDaily_hire_rate() {
		return daily_hire_rate;
	}
	public void setDaily_hire_rate(String daily_hire_rate) {
		this.daily_hire_rate = daily_hire_rate;
	}
	public String getMax_seating_capacity() {
		return max_seating_capacity;
	}
	public void setMax_seating_capacity(String max_seating_capacity) {
		this.max_seating_capacity = max_seating_capacity;
	}


	public MbusModel()
	{}	

	public String getVechile_id() {
		return vechile_id;
	}
	public void setVechile_id(String vechile_id) {
		this.vechile_id = vechile_id;
	}
	
	
	

	public MbusModel(String vechile_id, String make, String model, String max_seating_capacity, String top_speed,
			String daily_hire_rate, String registration_no) {
		super();
		this.vechile_id = vechile_id;
		this.make = make;
		this.model = model;
		this.max_seating_capacity = max_seating_capacity;
		this.top_speed = top_speed;
		this.daily_hire_rate = daily_hire_rate;
		this.registration_no = registration_no;
	}


   @CsvField(pos=1)
	private String vechile_id="None";
	
	@CsvField(pos=2)
	private String make="None";

	@CsvField(pos=3)
	private String model="None";

	@CsvField(pos=4)
	private String max_seating_capacity="None";

	@CsvField(pos=5)
	private String top_speed="None";

	@CsvField(pos=6)
	private String daily_hire_rate="None";

	@CsvField(pos=7)
	private String registration_no="None";

	
}
