package no.noark.web.service;

import java.io.IOException;
import java.net.URI;

import no.noark.database.Database;
import no.noark.templates.Templates;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class NoarkWebService {

	public static HttpServer startServer(String listenAddress) {

		Database.init();

		Templates.init();

		final ResourceConfig rc = new ResourceConfig()
				.packages("no.noark.web.resources");

		return GrizzlyHttpServerFactory.createHttpServer(
				URI.create(listenAddress), rc);
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {

		int port = 8080;

		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		String listenAddress = "http://0.0.0.0:" + port + "/noark/";

		startServer(listenAddress);

		System.out.println(String.format("Service started: "
				+ "%sapplication.wadl", listenAddress));

		while (true) {
			Thread.sleep(Long.MAX_VALUE);
		}
	}
}
