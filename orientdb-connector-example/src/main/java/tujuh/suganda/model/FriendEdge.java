package tujuh.suganda.model;

import java.math.BigInteger;

public class FriendEdge {
	private String edge_id;
	private String _from;
	private String _to;
	private BigInteger timestamp;
	
	public FriendEdge() {
		super();
	}
	
 
	public FriendEdge(String edge_id, String _from, String _to, BigInteger timestamp) {
		super();
		this.edge_id = edge_id;
		this._from = _from;
		this._to = _to;
		this.timestamp = timestamp;
	}


	public String getEdge_id() {
		return edge_id;
	}


	public void setEdge_id(String edge_id) {
		this.edge_id = edge_id;
	}


	public String get_from() {
		return _from;
	}
	public void set_from(String _from) {
		this._from = _from;
	}
	public String get_to() {
		return _to;
	}
	public void set_to(String _to) {
		this._to = _to;
	}
	public BigInteger getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(BigInteger timestamp) {
		this.timestamp = timestamp;
	}
 

}

