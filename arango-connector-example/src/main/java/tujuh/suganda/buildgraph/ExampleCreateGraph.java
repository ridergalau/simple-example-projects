package tujuh.suganda.buildgraph;
/*Example Creating Graph
 * 
 * 2017/09/16
 * 
 * By RiderGalau
 * */
import java.util.Collection;
import tujuh.suganda.buildgraph.dataexample.DataExample;
import tujuh.suganda.model.edges.HasCarEdge;
import tujuh.suganda.model.edges.HasChildEdge;
import tujuh.suganda.model.edges.HasFriendEdge;
import tujuh.suganda.model.vertices.CarModel;
import tujuh.suganda.model.vertices.PersonModel;
import tujuh.suganda.service.BuildGraph;

public class ExampleCreateGraph {
	final static BuildGraph BUILD = new BuildGraph();
	final static DataExample GetData = new DataExample();

	public static void main(String[] args) {

		Collection<PersonModel> dataPerson = GetData.examplePerson();
		Collection<CarModel> dataCar = GetData.exampleCar();
		Collection<HasChildEdge> relationChild = GetData.childRelations();
		Collection<HasFriendEdge> relationFriend = GetData.friendRelations();
		Collection<HasCarEdge> relationCar = GetData.carRelations();

		try {
			BUILD.insertPerson(dataPerson);
			BUILD.insertCar(dataCar);
			BUILD.HasChild(relationChild, "childedge");
			BUILD.HasFriend(relationFriend, "friendedge");
			BUILD.HasCar(relationCar, "caredge");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
