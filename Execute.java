package Actions;
//import events.AbstractEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import pubSubServer.Commands;

public class Execute {

	Commands command;
	private HashMap<String, Method> methodMapping = new HashMap<>();
	
	public Execute(Commands command) {
		this.command = command;
		for (Method m : command.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(m.getModifiers()))
				methodMapping.put(m.getName().toUpperCase(), m);
		}
	}
	
	public void run(String input) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] words = input.split(" ");
		Method method = methodMapping.get(words[0].toUpperCase());
		Parameter[] params = method.getParameters();
		Object[] args = new Object[method.getParameterCount()];
		for(int i = 0; i < args.length; i++) {
			Parameter param = params[i];
			if(i >= words.length - 1) {
				if(param.isAnnotationPresent(Nullable.class)) {
                    args[i] = null;
                }
			}
			else {
				String arg = words[i + 1];
				Object argValue;
	            if(param.getType() != String.class) {
	                Method valueOf = param.getType().getMethod("valueOf", String.class);
	                argValue = valueOf.invoke(null, arg);
	            } else {
	                argValue = arg;
	            }
	            args[i] = argValue;
			}
		}
		
		method.invoke(this.command, args);
	}
}
