package com.bma.chatbot.app.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.springframework.http.ResponseEntity;

import com.bma.chatbot.app.contracts.WebhookRequests;
import com.bma.chatbot.app.vo.User;
import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ChatbotUtil {

	private static Gson gson;
	
	static {
		gson = new Gson();
	}

	/**
	 * Print the values of every property of an object
	 * @param obj
	 * @return
	 */
	public static String printObj(Object obj) {
		if (null == obj) {
			return "Object is Null";
		}
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(obj.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = obj.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			field.setAccessible(true);
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
				result.append(field.get(obj));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}

	public static Object convertObjectToJSON(Object obj) {
		return gson.toJson(obj);
	}

	public static boolean isSet(Object obj) {
		return obj != null;
	}

	public static Object convertJSONToObj(String json, Type type) {
		return gson.fromJson(json, type);
	}

	public static Object convertJSONElementToJSON(JsonElement jsonEl, Class clazz) {
		return gson.toJson(jsonEl, clazz);
	}
}
