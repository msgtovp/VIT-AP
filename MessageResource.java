package com.blogger.rest.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blogger.rest.model.Message;
import com.blogger.rest.store.ErrorStore;
import com.blogger.rest.store.MessageStore;
import com.blogger.rest.store.SuccessStore;

@Path("/")
public class MessageResource {

	MessageStore messageStore = new MessageStore();

	@GET
	@Path("/messages")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages() {
		return messageStore.getMessages();
	}
	
	@POST
	@Path("/messages")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String> createMessage(Message message) {
		Integer messageId = messageStore.addMessage(message);
		return SuccessStore.getSuccessMessage(messageId);
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
	
	@PUT
	@Path("/messages/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMessageById(@PathParam("messageId") Integer messageID, Message messageInp) {
		Message message = messageStore.getMessage(messageID);
		if (message != null) {
			return Response.ok().entity(
					SuccessStore.getSuccessMessage(
							messageStore.updateMessage(messageID, messageInp)
							)
					).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorStore.getErrorResponse(2000, "Message not found")).build();
		}
	}
	
	@DELETE
	@Path("/messages/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMessageById(@PathParam("messageId") Integer messageID) {
		Message message = messageStore.getMessage(messageID);
		if (message != null) {
			return Response.ok().entity(
					SuccessStore.getSuccessMessage(
							messageStore.deleteMessage(messageID)
							)
					).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorStore.getErrorResponse(2000, "Message not found")).build();
		}
	}

}
