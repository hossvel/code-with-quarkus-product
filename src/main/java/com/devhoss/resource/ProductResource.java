package com.devhoss.resource;

import com.devhoss.entity.Product;
import com.devhoss.service.IProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    IProductService iProductService;

    @GET
    public List<Product> getAll() {
        return iProductService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Product product = iProductService.findById(id);
        return product != null ? Response.ok(product).build() : Response.status(Status.NOT_FOUND).build();
    }

    @POST
    public Response create(Product product) {
        Product created = iProductService.create(product);
        return Response.status(Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
        Product updated = iProductService.update(id, product);
        return updated != null ? Response.ok(updated).build() : Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = iProductService.delete(id);
        return deleted ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
    }
}