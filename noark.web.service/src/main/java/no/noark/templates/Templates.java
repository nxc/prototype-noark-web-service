package no.noark.templates;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class Templates {

	// the only instance of this class
	private static Templates instance;

	private Templates() {

		// initialize Velocity
		Properties props = new Properties();
		props.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		props.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

		props.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.Log4JLogChute");

		Velocity.init(props);
	}

	public static void init() {

		instance = new Templates();
	}

	public static Templates get() {
		return instance;
	}

	public String produce(String templateFile, TemplateParams params) {

		// create a new Velocity context
		VelocityContext context = new VelocityContext();

		for (String paramName : params.getParams().keySet()) {

			context.put(paramName, params.getParams().get(paramName));
		}

		Template template = Velocity.getTemplate("/web/" + templateFile);

		StringWriter sw = new StringWriter();

		template.merge(context, sw);

		return sw.toString();
	}

}
