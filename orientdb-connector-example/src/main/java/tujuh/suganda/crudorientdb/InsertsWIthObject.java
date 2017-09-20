package tujuh.suganda.crudorientdb;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import tujuh.orientconfig.Config;
import tujuh.suganda.model.Person;
import tujuh.suganda.exampledata.DataExample;
import tujuh.suganda.model.Car;

public class InsertsWIthObject {
	static Config orientConf;
	static DataExample example;

	public static void main(String[] args) throws IOException {
		OObjectDatabaseTx db = orientConf.connectDatabase();
		Car modelCar1 = db.newInstance(Car.class);
		Person person = db.newInstance(Person.class);
		Collection<Car> colCar = example.exampleCar();
		Collection<Person> colPerson = example.examplePerson();

		System.out.println("PERSON ..");
		db.save(colCar);
		System.out.println("CAR ..");
		db.save(colPerson);
	}

}