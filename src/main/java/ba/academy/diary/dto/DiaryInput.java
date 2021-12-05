package ba.academy.diary.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiaryInput {
  private Integer id;
  private Date date;
  private String description;
  private MedicineType medicineType;
  private Medicines medicines;
  private int count;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MedicineType getMedicineType() {
    return medicineType;
  }

  public void setMedicineType(MedicineType medicineType) {
    this.medicineType = medicineType;
  }

  public Medicines getMedicines() {
    return medicines;
  }

  public void setMedicines(Medicines medicines) {
    this.medicines = medicines;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
