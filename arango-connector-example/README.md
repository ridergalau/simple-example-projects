#GraphDb with Arango
---------------- Creating Schema ------------------------------
arangosh --server.endpoint tcp://127.0.0.1:8530
db._useDatabase("dbperson"):

db._create("person");
db.person.ensureIndex({ type: "hash", fields: [ "firstname" ]});
db.person.ensureIndex({ type: "hash", fields: [ "lastname" ]});
db.person.ensureIndex({ type: "hash", fields: [ "address" ]});
db.person.ensureIndex({ type: "hash", fields: [ "hobies" ]});
db.person.ensureIndex({ type: "hash", fields: [ "timestamp" ]});
db.person.ensureIndex({ type: "hash", fields: [ "gender" ]});
db.person.ensureIndex({ type: "hash", fields: [ "email" ]});

db._create("car");
db.car.ensureIndex({ type: "hash", fields: [ "merk" ]});
db.car.ensureIndex({ type: "hash", fields: [ "no_car" ]});
db.car.ensureIndex({ type: "hash", fields: [ "color" ]});
db.car.ensureIndex({ type: "hash", fields: [ "manufacture" ]});
db.car.ensureIndex({ type: "hash", fields: [ "timestamp" ]});
db.car.ensureIndex({ type: "hash", fields: [ "category" ]});


db._createEdgeCollection("caredge");
db.caredge.ensureIndex({ type: "hash", fields: [ "type","_from"]});
db.caredge.ensureIndex({ type: "hash", fields: [ "type","_to"]});
db.caredge.ensureIndex({ type: "hash", fields: [ "type","_timestamp"]});

db._createEdgeCollection("childedge");
db.childedge.ensureIndex({ type: "hash", fields: [ "type","_from"]});
db.childedge.ensureIndex({ type: "hash", fields: [ "type","_to"]});
db.childedge.ensureIndex({ type: "hash", fields: [ "type","_timestamp"]});

db._createEdgeCollection("friendedge");
db.friendedge.ensureIndex({ type: "hash", fields: [ "type","_from"]});
db.friendedge.ensureIndex({ type: "hash", fields: [ "type","_to"]});
db.friendedge.ensureIndex({ type: "hash", fields: [ "type","_timestamp"]});

---------------------------------- CREATING GRAPH -------------------------------------------

var graph_module =  require("org/arangodb/general-graph");
var graph = graph_module._create("examplegraph");
graph._addVertexCollection("person");
graph._addVertexCollection("car");

graph._extendEdgeDefinitions(graph_module._relation("caredge", ["person"], ["car"]));
graph._extendEdgeDefinitions(graph_module._relation("childedge", ["person"], ["person"]));
graph._extendEdgeDefinitions(graph_module._relation("friendedge", ["person"], ["person"]));

--------------------------------------------------------------------------------------------------------------------
The Schema will be use for create Graph Like the Picture
![alt text](https://github.com/ridergalau/simple-example-projects/blob/master/arango-connector-example/src/main/resources/graph%20plan.png)
