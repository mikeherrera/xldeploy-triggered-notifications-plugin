package ext.deployit.michaelherrera.plugin.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xebialabs.deployit.plugin.generic.freemarker.ConfigurationHolder;

import ext.deployit.michaelherrera.plugin.hipchat.notification.HipChatNotification;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public final class TemplateParser {
	
	private static final Logger logger = LoggerFactory.getLogger(HipChatNotification.class);
	
	@SuppressWarnings("rawtypes")
	public static final String parseTemplatedMessage(String templatedMessage, Map templateVars)	{
		Configuration cfg = ConfigurationHolder.getConfiguration();
        Template loadedTemplate;
        StringWriter stringWriter = null;
        
        try {
			loadedTemplate = new Template("name", new StringReader(templatedMessage), cfg);
			stringWriter = new StringWriter();
			loadedTemplate.process(templateVars, stringWriter);
		} catch (TemplateException | IOException e) {
			logger.error("Exception: " + e.getMessage());
		}
        
        return stringWriter.toString();
	}
}
