package org.selenium.pom.utils;

import java.io.IOException;
import java.io.InputStream;

import org.selenium.pom.objects.BillingAddress;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
	
	public static BillingAddress deserializeJson (InputStream is, BillingAddress billingAddress) throws StreamReadException, DatabindException, IOException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(is, billingAddress.getClass());

	}

}
