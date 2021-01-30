package io.github.maksymdolgykh.dropwizard.example.resources;

import io.github.maksymdolgykh.dropwizard.example.dao.PersonDao;
import io.github.maksymdolgykh.dropwizard.example.model.Person;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    PersonDao PersonDao;

    public PersonResource(PersonDao PersonDao) {
        this.PersonDao = PersonDao;
    }

    @GET
    public List<Person> getAll(){
        return PersonDao.getAllPeople();
    }

    @GET
    @Path("/{id}")
    public Person get(@PathParam("id") Integer id){
        return PersonDao.findPersonById(id);
    }

    @POST
    public Person add(@Valid Person person) {
        long id = PersonDao.createPerson(person);
        person.setId(id);
        return person;
    }

    @PUT
    @Path("/{id}")
    public Person update(@PathParam("id") Integer id, @Valid Person person) {
        Person updatePerson = new Person(id, person.getFullName(), person.getJobTitle(), person.getYearBorn());
        PersonDao.updatePerson(updatePerson);
        return updatePerson;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        PersonDao.deletePersonById(id);
    }
}
