package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.demo.service.Foo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyTest {

	@Test
	public void givenObject_whenToString_thenReturnHelloWorldString() throws InstantiationException, IllegalAccessException {
		DynamicType.Unloaded unloadedType = new ByteBuddy().subclass(Object.class).method(ElementMatchers.isToString())
				.intercept(FixedValue.value("Hello world ByteBuddy")).make();
		
		Class<?> dynamicType = unloadedType.load(getClass().getClassLoader()).getLoaded();
		
		assertEquals(dynamicType.newInstance().toString(), "Hello world ByteBuddy");
	}
	
	@Test
	public void givenFoo_whenRedefined_thenReturnFooRedefined(){
		ByteBuddyAgent.install();
		new ByteBuddy().redefine(Foo.class).method(ElementMatchers.named("sayHelloFoo"))
		.intercept(FixedValue.value("Hello Foo redefined"))
		.make().load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
		Foo f = new Foo();
		assertEquals(f.sayHelloFoo(), "Hello Foo redefined");
	}
	
}
