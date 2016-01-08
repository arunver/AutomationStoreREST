package com.store.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.store.exception.StoreException;
import com.store.model.Entity;
import com.store.model.Pack;
import com.store.model.StorePacks;
import com.store.vo.StoreResponse;


@Path("/wfsmetadata/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON})
public interface StoreManager {
	
	@GET
	@Path("/1.0")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public String getMessage() throws WebApplicationException;
	
	@GET
	@Path("/2.0")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public StoreResponse getPacks() throws StoreException;
	
	@GET
	@Path("/getPackByUuid/{uuid}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public StorePacks getPackByUuid(@PathParam("uuid") String uuid) throws WebApplicationException;
	
	@GET
	@Path("/getPackByVersion/{version}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public StorePacks getPackByVersion(@PathParam("version") String version) throws WebApplicationException;
	
	@GET
	@Path("/getEntityByPackId/{id}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public List<Entity> getEntityByPackId(@PathParam("id") long id) throws WebApplicationException;
	
	@GET
	@Path("/getPackByPackId/{id}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Pack getPackByPackId(@PathParam("id") long id) throws WebApplicationException;
	
	/*@GET
	@Path("/getPackByPackId/{id}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public StorePacks getPackByAuthor(@PathParam("authorName") String authorName) throws WebApplicationException;*/

}
