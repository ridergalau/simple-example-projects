package tujuh.suganda.crudorientdb.query;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class zzz {
public static void main(String[] args) {
	OObjectDatabaseTx db = new OObjectDatabaseTx ("remote:localhost/petshop").open("admin", "admin");
	ODatabaseDocumentTx dbs = new ODatabaseDocumentTx(
			   "remote:localhost/petshop").open("admin", "admin_passwd");
}
}
