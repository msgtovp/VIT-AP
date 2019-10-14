package com.blogger.rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blogger.rest.model.Message;
import com.blogger.rest.store.ErrorStore;
import com.blogger.rest.store.MessageStore;

@Path("/")
public class MessageResource {

	MessageStore messageStore = new MessageStore();

	@GET
	@Path("/messages")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages() {
		return messageStore.getMessages();
	}

	@GET
	@Path("/messages/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessageById(@PathParam("messageId") Integer messageID) {
		Message message = messageStore.getMessage(messageID);
		if (message != null) {
			return Response.ok().entity(message).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorStore.getErrorResponse(2000, "Message not found")).build();
		}
	}

}