package DataModels;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType
public class VDetailModel {

	public VDetailModel() {}
	
	public String getVechile_id() {
		return vechile_id;
	}
	public void setVechile_id(String vechile_id) {
		this.vechile_id = vechile_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public VDetailModel(String vechile_id, String type, String status) {
		super();
		this.vechile_id = vechile_id;
		this.type = type;
		this.status = status;
	}
	public VDetailModel(String vechile_id, String type, String status,String customer_id) {
		super();
		this.vechile_id = vechile_id;
		this.type = type;
		this.status = status;
		this.customer_id=customer_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	@CsvField(pos=1)
	private String vechile_id="None";

	@CsvField(pos=2)
	private String type="None";

	@CsvField(pos=3)
	private String status="None";//hired or available

	@CsvField(pos=4)
	private String customer_id="None";
	


	

}
