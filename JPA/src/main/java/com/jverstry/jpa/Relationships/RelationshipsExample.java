
package com.jverstry.jpa.Relationships;

import com.jverstry.jpa.JPA;
import com.jverstry.jpa.Relationships.ManyToMany.ManyToManyA;
import com.jverstry.jpa.Relationships.ManyToMany.ManyToManyB;
import com.jverstry.jpa.Relationships.ManyToOne.ManyToOneBidirectionalA;
import com.jverstry.jpa.Relationships.ManyToOne.ManyToOneBidirectionalB;
import com.jverstry.jpa.Relationships.ManyToOne.ManyToOneUnidirectionalA;
import com.jverstry.jpa.Relationships.ManyToOne.ManyToOneUnidirectionalB;
import com.jverstry.jpa.Relationships.OneToMany.OneToManyBidirectionalA;
import com.jverstry.jpa.Relationships.OneToMany.OneToManyBidirectionalB;
import com.jverstry.jpa.Relationships.OneToMany.OneToManyUnidirectionalA;
import com.jverstry.jpa.Relationships.OneToMany.OneToManyUnidirectionalB;
import com.jverstry.jpa.Relationships.OneToOne.OneToOneBidirectionalA;
import com.jverstry.jpa.Relationships.OneToOne.OneToOneBidirectionalB;
import com.jverstry.jpa.Relationships.OneToOne.OneToOneUnidirectionalA;
import com.jverstry.jpa.Relationships.OneToOne.OneToOneUnidirectionalB;
import java.util.ArrayList;
import java.util.Collection;

public class RelationshipsExample {
	
	public static void main(String[] args) {
		
		JPA.INSTANCE.clear();
		
		OneToOneUnidirectional();
		OneToOneBidirectional();
		OneToManyUnidirectional();
		OneToManyBidirectional();
		ManyToOneUnidirectional();
		ManyToOneBidirectional();
		ManyToMany();
		
	}
	
	public static void OneToOneUnidirectional() {
		
		OneToOneUnidirectionalA a = new OneToOneUnidirectionalA("AAA");
		OneToOneUnidirectionalB b = new OneToOneUnidirectionalB("BBB");
		
		a.setB(b);
		
		JPA.INSTANCE.save(b);
		JPA.INSTANCE.save(a);
		JPA.INSTANCE.clear();
		
		OneToOneUnidirectionalA retrA
			= JPA.INSTANCE.get(OneToOneUnidirectionalA.class, a.getId());
		
		System.out.println("Retrieved OneToOne Unidirectional A:");
		System.out.println(retrA);
		System.out.println(retrA.getB());
		
		OneToOneUnidirectionalB retrB
			= JPA.INSTANCE.get(OneToOneUnidirectionalB.class, b.getId());
		
		System.out.println("Retrieved OneToOne Unidirectional B:");
		System.out.println(retrB);
		
	}
		
	public static void OneToOneBidirectional() {
		
		OneToOneBidirectionalA a2 = new OneToOneBidirectionalA("BiAAA");
		OneToOneBidirectionalB b2 = new OneToOneBidirectionalB("BiBBB");
		
		JPA.INSTANCE.save(b2);
		JPA.INSTANCE.save(a2);
		
		a2.setB(b2);
		b2.setA(a2);
		
		JPA.INSTANCE.update(b2);
		JPA.INSTANCE.update(a2);
		JPA.INSTANCE.clear();
		
		OneToOneBidirectionalA retrA2
			= JPA.INSTANCE.get(OneToOneBidirectionalA.class, a2.getId());
		
		System.out.println("Retrieved OneToOne Bidirectional A:");
		System.out.println(retrA2 + " -> " + retrA2.getB());
		
		OneToOneBidirectionalB retrB2
			= JPA.INSTANCE.get(OneToOneBidirectionalB.class, b2.getId());
		
		System.out.println("Retrieved OneToOne Bidirectional B:");
		System.out.println(retrB2 + " -> " + retrB2.getA());
		
	}
	
	public static void OneToManyUnidirectional() {
		
		OneToManyUnidirectionalA a = new OneToManyUnidirectionalA("AAA");
		
		Collection<OneToManyUnidirectionalB> setB
		    = new ArrayList<OneToManyUnidirectionalB>();
		
		setB.add(new OneToManyUnidirectionalB("BBB"));
		setB.add(new OneToManyUnidirectionalB("CCC"));

		a.setB(setB);
		
		JPA.INSTANCE.save(a);
		JPA.INSTANCE.clear();
		
		OneToManyUnidirectionalA retrA
			= JPA.INSTANCE.get(OneToManyUnidirectionalA.class, a.getId());
		
		System.out.println("Retriving OneToMany Unidirectional A");
		System.out.println(retrA);
		
		System.out.println("Retriving OneToMany Unidirectional B's");
		for (OneToManyUnidirectionalB origB : a.getB()) {
			OneToManyUnidirectionalB retrB
				= JPA.INSTANCE.get(
					OneToManyUnidirectionalB.class, origB.getId());
			System.out.println(retrB);
		}
		
	}
		
	public static void OneToManyBidirectional() {
		
		OneToManyBidirectionalA a2 = new OneToManyBidirectionalA("BiAAA");
		
		OneToManyBidirectionalB b2 = new OneToManyBidirectionalB("BiBBB");
		OneToManyBidirectionalB c2 = new OneToManyBidirectionalB("BiCCC");
		
		Collection<OneToManyBidirectionalB> setB2
		    = new ArrayList<OneToManyBidirectionalB>();
		
		setB2.add(b2);
		setB2.add(c2);

		a2.setB(setB2);
		b2.setA(a2);
		c2.setA(a2);
		
		JPA.INSTANCE.save(a2);
		JPA.INSTANCE.clear();
		
		OneToManyBidirectionalA retrA2
			= JPA.INSTANCE.get(OneToManyBidirectionalA.class, a2.getId());
		
		System.out.println("Retriving OneToMany Bidirectional A");
		System.out.println(retrA2);

		System.out.println("Retriving OneToMany Bidirectional B's");
		for (OneToManyBidirectionalB origB : a2.getB()) {
			OneToManyBidirectionalB retrB
				= JPA.INSTANCE.get(
					OneToManyBidirectionalB.class, origB.getId());
			System.out.println(retrB);
		}
		
	}
	
	public static void ManyToOneUnidirectional() {
		
		ManyToOneUnidirectionalA a1 = new ManyToOneUnidirectionalA("AA1");
		ManyToOneUnidirectionalA a2 = new ManyToOneUnidirectionalA("AA2");
		
		ManyToOneUnidirectionalB b = new ManyToOneUnidirectionalB("BBB");
		
		a1.setB(b);
		a2.setB(b);
		
		JPA.INSTANCE.save(b);
		JPA.INSTANCE.save(a1);
		JPA.INSTANCE.save(a2);
		JPA.INSTANCE.clear();
		
		ManyToOneUnidirectionalA retrA1
			= JPA.INSTANCE.get(ManyToOneUnidirectionalA.class, a1.getId());
		
		System.out.println("Retrieving ManyToOne Unidirectional A's");
		System.out.println(retrA1);
		
		ManyToOneUnidirectionalA retrA2
			= JPA.INSTANCE.get(ManyToOneUnidirectionalA.class, a2.getId());
		
		System.out.println(retrA2);

		System.out.println("Retrieving ManyToOne Unidirectional B");
		ManyToOneUnidirectionalB retrB
			= JPA.INSTANCE.get(ManyToOneUnidirectionalB.class, b.getId());
		
		System.out.println(retrB);
		
	}
		
	public static void ManyToOneBidirectional() {
		
		ManyToOneBidirectionalA a11 = new ManyToOneBidirectionalA("BiAAA");
		ManyToOneBidirectionalA a22 = new ManyToOneBidirectionalA("BiAAA");
		ManyToOneBidirectionalB b2 = new ManyToOneBidirectionalB("BiBBB");
		
		Collection<ManyToOneBidirectionalA> c
			= new ArrayList<ManyToOneBidirectionalA>();
		c.add(a11);
		c.add(a22);
		b2.setA(c);
		
		a11.setB(b2);
		a22.setB(b2);
		
		JPA.INSTANCE.save(b2);
		JPA.INSTANCE.clear();

		ManyToOneBidirectionalB retrB
			= JPA.INSTANCE.get(ManyToOneBidirectionalB.class, b2.getId());
		
		System.out.println("Retrieving ManyToOne Bidirectional B");
		System.out.println(retrB);
		
		for (ManyToOneBidirectionalA orig : b2.getA()) {
			ManyToOneBidirectionalA retrA
				= JPA.INSTANCE.get(
					ManyToOneBidirectionalA.class, orig.getId());
			System.out.println(retrA);
		}		
		
		System.out.println("Retrieving ManyToOne Bidirectional A's");
		
		ManyToOneBidirectionalA retrA11
			= JPA.INSTANCE.get(ManyToOneBidirectionalA.class, a11.getId());
		
		System.out.println(retrA11);
		System.out.println(retrA11.getB());
		
		ManyToOneBidirectionalA retrA22
			= JPA.INSTANCE.get(ManyToOneBidirectionalA.class, a22.getId());
		
		System.out.println(retrA22);
		System.out.println(retrA22.getB());
		
	}
	
	public static void ManyToMany() {
		
		ManyToManyA a1 = new ManyToManyA("AA1");
		ManyToManyA a2 = new ManyToManyA("AA2");
		ManyToManyA a3 = new ManyToManyA("AA3");
		
		ManyToManyB b1 = new ManyToManyB("BB1");
		ManyToManyB b2 = new ManyToManyB("BB2");

		a1.getListOfB().add(b1);
		b1.getListOfA().add(a1);
		
		a3.getListOfB().add(b1);
		b1.getListOfA().add(a3);
		
		a2.getListOfB().add(b2);
		b2.getListOfA().add(a2);
		
		a3.getListOfB().add(b2);
		b2.getListOfA().add(a3);
		
		JPA.INSTANCE.save(a1);
		JPA.INSTANCE.save(a2);
		JPA.INSTANCE.save(a3);
		JPA.INSTANCE.clear();
		
		System.out.println("Retriving ManyToMany A's and their B's");

		ManyToManyA retrA1
			= JPA.INSTANCE.get(ManyToManyA.class, a1.getId());
		
		System.out.println(retrA1);
		for (ManyToManyB origB : retrA1.getListOfB()) {
			ManyToManyB retrB
				= JPA.INSTANCE.get(
					ManyToManyB.class, origB.getId());
			System.out.println(retrB);
		} System.out.println(" ");
		
		ManyToManyA retrA2
			= JPA.INSTANCE.get(ManyToManyA.class, a2.getId());
		
		System.out.println(retrA2);
		for (ManyToManyB origB : retrA2.getListOfB()) {
			ManyToManyB retrB
				= JPA.INSTANCE.get(
					ManyToManyB.class, origB.getId());
			System.out.println(retrB);
		} System.out.println(" ");
		
		ManyToManyA retrA3
			= JPA.INSTANCE.get(ManyToManyA.class, a3.getId());
		
		System.out.println(retrA3);
		for (ManyToManyB origB : retrA3.getListOfB()) {
			ManyToManyB retrB
				= JPA.INSTANCE.get(
					ManyToManyB.class, origB.getId());
			System.out.println(retrB);
		} System.out.println(" ");
		
		System.out.println("Retriving ManyToMany B's and their A's");
		
		ManyToManyB retrB1
			= JPA.INSTANCE.get(ManyToManyB.class, b1.getId());
		
		System.out.println(retrB1);
		for (ManyToManyA origA : retrB1.getListOfA()) {
			ManyToManyA retrA
				= JPA.INSTANCE.get(
					ManyToManyA.class, origA.getId());
			System.out.println(retrA);
		} System.out.println(" ");
		
		ManyToManyB retrB2
			= JPA.INSTANCE.get(ManyToManyB.class, b2.getId());
		
		System.out.println(retrB2);
		for (ManyToManyA origA : retrB2.getListOfA()) {
			ManyToManyA retrA
				= JPA.INSTANCE.get(
					ManyToManyA.class, origA.getId());
			System.out.println(retrA);
		} System.out.println(" ");
		
	}
	
}
