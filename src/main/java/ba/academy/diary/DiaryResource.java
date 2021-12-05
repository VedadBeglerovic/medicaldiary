package ba.academy.diary;

import ba.academy.diary.dto.DiaryInput;
import ba.academy.diary.services.DiaryService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static java.lang.Boolean.FALSE;

@Path("/diary")
public class DiaryResource {

  @Inject
  DiaryService diaryService;
  // GET - get by id
  // DELETE - by id
  // PUT - update by id

  @GET
  public Response getDiary() {
    List<DiaryInput> allDiaryInputs = diaryService.getDiary();
    if (allDiaryInputs == null || allDiaryInputs.isEmpty()) {
      return Response.noContent().build();
    }
    return Response.ok(allDiaryInputs).build();
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)

  public Response createDiary(DiaryInput diaryInput, @Context UriInfo uriInfo) {
    DiaryInput storedDiary = diaryService.addDiaryInput(diaryInput);
    if (storedDiary != null) {
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      uriBuilder.path(Integer.toString(storedDiary.getId()));
      return Response.created(uriBuilder.build()).entity(storedDiary).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateDiary(DiaryInput diaryInput, @PathParam("id") String id) {
    DiaryInput storedDiary = diaryService.updateDiaryInput(id, diaryInput);
    if (storedDiary != null) {
      return Response.status(Response.Status.OK).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  @GET
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getDiaryById(@PathParam("id") String id) {
    DiaryInput diaryInput= diaryService.getDiaryById(id);
    if (diaryInput==null) {
      return Response.noContent().build();
    }
    return Response.ok(diaryInput).build();
  }

  @DELETE
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deleteDiaryById(@PathParam("id") String id) {
    boolean deleted= diaryService.deleteDiaryById(id);
    if (deleted==FALSE) {
      return Response.noContent().build();
    }
    return Response.ok().build();
  }
}