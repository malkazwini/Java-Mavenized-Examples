
package com.jverstry.jpa.Inheritance.Type.SingleTable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
@DiscriminatorValue("45")
public class InheritsSingleTable extends SingleTable {
	
	private String s2;
	
	public InheritsSingleTable() { }
	
	@Override
	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s) {
		this.s2 = s;
	}
	
}
