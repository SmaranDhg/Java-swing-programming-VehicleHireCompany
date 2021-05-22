package DataModels;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

import Utilities.RawDatas;
import Utilities.RawDatas.LOGIN_ID;

@CsvDataType
public class LDetailModel {
  public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 public LDetailModel(String username, String password,LOGIN_ID lid) {
		super();
		this.setUsername(username);;
		this.setPassword(password);;
		this.setLogin_id(lid);
	}
 public LDetailModel() {}
  public LOGIN_ID getLogin_id() {
	return login_id;
}
public void setLogin_id(LOGIN_ID login_id) {
	this.login_id = login_id;
}

@CsvField(pos=1)
private String username="None";

@CsvField(pos=2)
private String password="None";

@CsvField(pos=3)
private LOGIN_ID login_id=RawDatas.LOGIN_ID.STAFF;
  
}
