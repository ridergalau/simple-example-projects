package tujuh.suganda.model;

import java.io.Serializable;
import java.util.List;

public class SimpleModel implements Serializable{
public String text;
public List topic;
public String id;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public List getTopic() {
	return topic;
}
public void setTopic(List topic) {
	this.topic = topic;
}

	
}