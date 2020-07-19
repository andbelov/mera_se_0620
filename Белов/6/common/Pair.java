package common;

import java.util.Objects;

public class Pair<T1,T2>{
	private final T1 t1;
	private final T2 t2;
	public Pair(final T1 t1, final T2 t2){
		this.t1 = t1;
		this.t2 = t2;
	}
	public T1 getFirst (){ return t1; }
	public T2 getSecond(){ return t2; }
	@Override
	public String toString(){
		return "Pair{" +
				"first=" + t1 +
				", second=" + t2 +
				'}';
	}
	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null
				|| getClass() != o.getClass()
		) return false;
		Pair<?, ?> pair = (Pair<?,?>) o;
		return Objects.equals(t1, pair.getFirst())
			&& Objects.equals(t2, pair.getSecond());
	}
	@Override
	public int hashCode(){ return Objects.hash(t1, t2);	}
}