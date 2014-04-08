package no.noark.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import no.noark.database.Database;

@Path("cleanup")
public class Cleanup {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String cleanup() {

		Database.reinit();

		return "Clean up...";
	}

}
