package rewards.messaging;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.integration.annotation.Transformer;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.util.Assert;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;

import rewards.Dining;

public class DiningRequestUnmarshaller implements Unmarshaller {

	private XPathOperations xpathTemplate = new Jaxp13XPathTemplate();

	@Override
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return clazz.isAssignableFrom(Dining.class);
	}

	@Override
	@Transformer
	public Object unmarshal(Source source) throws XmlMappingException,
			IOException {
		Assert.isInstanceOf(DOMSource.class, source, this.getClass()
				.getSimpleName()
				+ " can only unmarshall DOMSources");

		String transactionId = xpathTemplate.evaluateAsString(
				"/dining/@transaction-id", source);
		String amount = xpathTemplate.evaluateAsString("/dining/amount/@value",
				source);
		String creditCardNumber = xpathTemplate.evaluateAsString(
				"/dining/creditcard/@number", source);
		String merchantNumber = xpathTemplate.evaluateAsString(
				"/dining/merchant/@number", source);
		String dateString = xpathTemplate.evaluateAsString(
				"/dining/timestamp/date", source);
		int year = Integer.valueOf(dateString.substring(0,4));
		int month = Integer.valueOf(dateString.substring(5,7));
		int day = Integer.valueOf(dateString.substring(8,10));
		return Dining.createDining(transactionId, amount,
				creditCardNumber,merchantNumber,month, day,year);
	}

}
