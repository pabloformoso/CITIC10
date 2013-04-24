package curso.citic10.sqlitea;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Button btnCreate;
	Button btnUpdate;
	Button btnRead;
	EditText text;
	DataBaseHandler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new DataBaseHandler(this);
		
		btnCreate = (Button)findViewById(R.id.button1);
		btnUpdate = (Button)findViewById(R.id.button2);
		btnRead = (Button)findViewById(R.id.button3);
		text = (EditText)findViewById(R.id.editTxt);
		
		btnCreate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createContacts();
			}
		});
		
		btnUpdate.setOnClickListener( new OnClickListener() {	
			@Override
			public void onClick(View v) {
				updateContacts();
			}
		});
		
		btnRead.setOnClickListener( new OnClickListener() {		
			@Override
			public void onClick(View v) {
				readContacts();				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void createContacts() {
		Log.d("MainActivity", "Inserting ..");
		db.addContact(new Contact("Einstein", "9100000000"));
		db.addContact(new Contact("Euler", "9199999999"));
		db.addContact(new Contact("Gauss", "9522222222"));
		db.addContact(new Contact("Copernico", "9533333333"));
	}
	
	private void updateContacts() {
		Contact contact = db.getContact(1);
		Log.d("Actualizando: " , contact.getName());
		contact.setName("Tesla");
		db.updateContact(contact);				
	}
	
	private void readContacts() {
		text.setText("");
	
		Log.d("Reading: ", "Reading all contacts..");
		List<Contact> contacts = db.getAllContacts();

		for (Contact cn : contacts) {
			String line = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
			text.append(line + "\n");
		}		
	}
}
