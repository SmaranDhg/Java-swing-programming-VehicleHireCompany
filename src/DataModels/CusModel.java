package DataModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType
public class CusModel {
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String password) {
		this._password = password;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_address() {
		return _address;
	}
	public void set_address(String _address) {
		this._address = _address;
	}
	public String get_contact() {
		return _contact;
	}
	public void set_contact(String _contact) {
		this._contact = _contact;
	}

	
	public CusModel() {}
	
	public CusModel(String _id, String _password, String _name, String _address, String _contact,
			ArrayList<String> vechiles_on_hire) {
		super();
		this._id = _id;
		this._password = _password;
		this._name = _name;
		this._address = _address;
		this._contact = _contact;
		this.setVechiles_on_hire(vechiles_on_hire);
	}

	public ArrayList<String> getVechiles_on_hire() {
		return vechiles_on_hire;
	}
	public void setVechiles_on_hire(ArrayList<String> vechiles_on_hire) {
		this.vechiles_on_hire = vechiles_on_hire;
	}

	@CsvField(pos=1)
	private String _id="None"; 
	@CsvField(pos=2)
	private String _password="None";
	
	@CsvField(pos=3)
	private String _name="None";

	@CsvField(pos=4)
	private String _address="None";

	@CsvField(pos=5)
	private String _contact="None";

	@CsvField(pos=6)
	private ArrayList<String> vechiles_on_hire=new ArrayList<>();//vechile 



	

}
