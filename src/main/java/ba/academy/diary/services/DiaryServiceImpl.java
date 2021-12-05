package ba.academy.diary.services;

import ba.academy.diary.dto.DiaryInput;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@ApplicationScoped
public class DiaryServiceImpl implements DiaryService {

  List<DiaryInput> myobjects = new ArrayList<>();

  @ConfigProperty(name = "prefix.message")
  String prefix;

  @Override
  public List<DiaryInput> getDiary() {
    return myobjects;
  }

  @Override
  public DiaryInput addDiaryInput(DiaryInput input) {
    Random rand = new Random(); //instance of random class
    input.setDate(new Date());
    if (input.getId() == null) {
      input.setId(rand.nextInt());
    }
    myobjects.add(input);
    return input;
  }

  @Override
  public DiaryInput updateDiaryInput(String id, DiaryInput input) {
    for(DiaryInput diary : myobjects) {
      if (diary.getId().toString().equals(id)) {
        diary.setCount(input.getCount());
        diary.setDescription(input.getDescription());
        diary.setMedicines(input.getMedicines());
        diary.setMedicineType(input.getMedicineType());
        input.setId(diary.getId());
      }
    }


    return input;
  }

  @Override
  public DiaryInput getDiaryById(String id) {
    return myobjects.stream().filter(object -> id.equals(object.getId().toString())).findFirst().get();
  }

  @Override
  public boolean deleteDiaryById(String id) {
    if(myobjects.stream().filter(object -> id.equals(object.getId().toString())).findFirst().isEmpty())
      return FALSE;
    myobjects.removeIf(object -> id.equals(object.getId().toString()));
    return TRUE;
  }

}
