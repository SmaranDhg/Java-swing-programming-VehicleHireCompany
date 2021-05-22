package DataModels;

import java.util.HashMap;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

@CsvDataType
public class StaffModel {
	
	

	public StaffModel() {}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}
	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
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
	public StaffModel(String _id, String _password, String _title, String _name, String _address, String _contact) {
		super();
		this._id = _id;
		this._password = _password;
		this._title = _title;
		this._name = _name;
		this._address = _address;
		this._contact = _contact;
	}

	@CsvField(pos=1)
	private String _id="None";

	@CsvField(pos=2)
	private String _password="None";

	@CsvField(pos=3)
	private String _title="None";

	@CsvField(pos=4)
	private String _name="None";

	@CsvField(pos=5)
	private String _address="None";

	@CsvField(pos=6)
	private String _contact="None";

	

}
