package tujuh.suganda.model.edges;

import java.math.BigInteger;

public class HasCarEdge {
	private String _key;
	private String _from;
	private String _to;
	private BigInteger timestamp;
	public String get_key() {
		return _key;
	}
	public void set_key(String _key) {
		this._key = _key;
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
	public HasCarEdge(String _key, String _from, String _to, BigInteger timestamp) {
		super();
		this._key = _key;
		this._from = _from;
		this._to = _to;
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "HasCarEdge [_key=" + _key + ", _from=" + _from + ", _to=" + _to + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
