package com.example.studentmanagementsystem.Controller;

import com.example.studentmanagementsystem.Model.DataDriver;
import com.example.studentmanagementsystem.Model.courseData;
import com.example.studentmanagementsystem.Model.getData;
import com.example.studentmanagementsystem.Model.studentData;
import com.example.studentmanagementsystem.Model.studentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DashboardController implements Initializable {
    public TextField availableCourse_description_fld;
    public TextField availableCourse_degree_fld;
    public TableColumn <courseData,String>availableCourse_col_Description;
    public TableColumn <courseData,String>availableCourse_col_degree;
    public AnchorPane main_form;
    public Label username;
    public Button home_btn;
    public Button addStudents_btn;
    public Button availableCourse_btn;
    public Button studentGrade_btn;
    public Button logout;
    public AnchorPane home_form;
    public Label home_totalEnrolled;
    public Label home_totalFemale;
    public Label home_totalMale;
    public BarChart home_totalEnrolledChart;
    public AreaChart home_totalFemaleChart;
    public LineChart home_totalMaleChart;
    public AnchorPane addStudents_form;
    public TextField addStudents_search;
    public TableView<studentData> addStudents_tableView;
    public TableColumn<studentData,String> addStudents_col_studentNum;
    public TableColumn<studentData,String> addStudents_col_year;
    public TableColumn<studentData,String> addStudents_col_course;
    public TableColumn<studentData,String> addStudents_col_firstName;
    public TableColumn<studentData,String> addStudents_col_lastName;
    public TableColumn<studentData,String> addStudents_col_gender;
    public TableColumn<studentData,String> addStudents_col_birth;
    public TableColumn<studentData,String> addStudents_col_status;
    public TextField addStudents_studentNum;
    public ComboBox<String> addStudents_year;
    public ComboBox<String> addStudents_course;
    public TextField addStudents_firstName;
    public TextField addStudents_lastName;
    public ComboBox<String> addStudents_gender;
    public DatePicker addStudents_birth;
    public ComboBox<String> addStudents_status;
    public ImageView addStudents_imageView;
    public Button addStudents_insertBtn;
    public Button addStudents_addBtn;
    public Button addStudents_updateBtn;
    public Button addStudents_deleteBtn;
    public Button addStudents_clearBtn;
    public AnchorPane availableCourse_form;
    public TextField availableCourse_course_fld;
    public Button availableCourse_addBtn;
    public Button availableCourse_updateBtn;
    public Button availableCourse_clearBtn;
    public Button availableCourse_deleteBtn;
    public TableView <courseData>availableCourse_tableView;
    public TableColumn <courseData,String>availableCourse_col_course;
    public AnchorPane studentGrade_form;
    public TextField studentGrade_studentNum;
    public Label studentGrade_year;
    public Label studentGrade_course;
    public TextField studentGrade_firstSem;
    public TextField studentGrade_secondSem;
    public Button studentGrade_clearBtn;
    public Button studentGrade_updateBtn;
    public TableView<studentGrade> studentGrade_tableView;
    public TableColumn<studentGrade,String> studentGrade_col_studentNum;
    public TableColumn<studentGrade,String> studentGrade_col_year;
    public TableColumn<studentGrade,String> studentGrade_col_course;
    public TableColumn<studentGrade,Double> studentGrade_col_firstSem;
    public TableColumn<studentGrade,Double> studentGrade_col_secondSem;
    public TableColumn<studentGrade,Double> studentGrade_col_final;
    public TextField studentGrade_search;


    private Image image;

    public void homeDisplayTotalEnrolledStudents(){
        DataDriver Con = new DataDriver();
        ResultSet result = Con.connectD2();
int countEnrolled=0;

        try{
         if (result.next()){
             countEnrolled = result.getInt("COUNT(id)");
         }
            home_totalEnrolled.setText(String.valueOf(countEnrolled));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
public void homeDisplayFemaleEnrolled(){
    DataDriver Con = new DataDriver();
    ResultSet result = Con.connectD4();
    try{
        int countFemale = 0;
        if(result.next()){
            countFemale=result.getInt("COUNT(id)");
        }
        home_totalFemale.setText(String.valueOf(countFemale));
    }catch (Exception e){
        e.printStackTrace();
    }
}

  public void homeDisplayMaleEnrolled(){
      DataDriver Con = new DataDriver();
      ResultSet result = Con.connectD5();
      try{
          int countMale=0;
          if(result.next()){
              countMale=result.getInt("COUNT(id)");
          }
          home_totalMale.setText(String.valueOf(countMale));
      }catch (Exception e){
          e.printStackTrace();
      }
  }


    public void addStudentsAdd() {
        Alert alert;
        try {

            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                int studentNum = Integer.parseInt(addStudents_studentNum.getText());
                String year = (String) addStudents_year.getSelectionModel().getSelectedItem();
                String course = (String) addStudents_course.getSelectionModel().getSelectedItem();
                String firstName = addStudents_firstName.getText();
                String lastName = addStudents_lastName.getText();
                String gender = (String) addStudents_gender.getSelectionModel().getSelectedItem();
                LocalDate birth = addStudents_birth.getValue();
                String status = (String) addStudents_status.getSelectionModel().getSelectedItem();
                String path = getData.path;

                // Gọi phương thức thích hợp để thêm sinh viên
                DataDriver Con = new DataDriver();
                Con.insertData(studentNum, year, course, firstName, lastName, gender, String.valueOf(birth), status, path); // Giả sử phương thức insertStudent đã tồn tại

                Con.insertStudentGrade(studentNum,year,course,0.0,0.0,0.0);
                addStudentsShowListDataGrade();
                addStudentsShowListData();

                addStudentsClear();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Student added successfully.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();//in thông tin về ngoại lệ
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Error");
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error occurred. Please contact support.");
            alert.showAndWait();
        }
    }
    public void addStudentsUpdate() {
        Alert alert;

        try {

            if (addStudents_studentNum.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                int studentNum = Integer.parseInt(String.valueOf(addStudents_studentNum.getText()));
                String year = (String) addStudents_year.getSelectionModel().getSelectedItem();
                String course = (String) addStudents_course.getSelectionModel().getSelectedItem();
                String firstName = addStudents_firstName.getText();
                String lastName = addStudents_lastName.getText();
                String gender = (String) addStudents_gender.getSelectionModel().getSelectedItem();
                LocalDate birth = addStudents_birth.getValue();
                String status = (String) addStudents_status.getSelectionModel().getSelectedItem();
                String path = getData.path;
                studentData studentD =addStudents_tableView.getSelectionModel().getSelectedItem();
                int num = addStudents_tableView.getSelectionModel().getSelectedIndex();
                if((num-1)< -1) return;
                int originalStudent =Integer.parseInt(String.valueOf((studentD.getStudentNum())));
                String birth1 = studentD.getBirth();
                String y = String.valueOf(studentD.getYear());
                DataDriver Con = new DataDriver();
                if (!(String.valueOf(studentNum).trim().isEmpty())){
                    Con.updateDataStudentNum(studentNum , originalStudent);
                }
                if (!(String.valueOf(year).trim().isEmpty())){
                    Con.updateDataYear(year , originalStudent);
                }
                if(!(firstName.trim().isEmpty())){
                    Con.updateDataFname(firstName,studentNum);
                }
                if(!(lastName.trim().isEmpty())){
                    Con.updateDatalastName(lastName,studentNum);
                }
                if (!(String.valueOf(gender).trim().isEmpty())){
                    Con.updateDatagender(gender , originalStudent);
                }
                if(birth != null){
                    Con.updateDatabirth(String.valueOf(birth),studentNum);
                }
                if (!(String.valueOf(status).trim().isEmpty())){
                    Con.updateDatastatus(status , originalStudent);
                }

                addStudentsShowListData();
                addStudentsClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void addStudentsDelete(){
        try{
            Alert alert;

            if (addStudents_studentNum.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #"+addStudents_studentNum.getText()+"?");

                Optional<ButtonType>option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    int student =Integer.parseInt(addStudents_studentNum.getText());
                    DataDriver Con = new DataDriver();
                    Con.deleteData(student);
                    Con.deleteDataGrade(student);



                    alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addStudentsShowListData();
                    addStudentsShowListDataGrade();
                    addStudentsClear();
                }else {
                    return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStudentsClear(){
        addStudents_studentNum.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birth.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);
        getData.path="";

    }

    public  void addStudentsInsertImage(){
        FileChooser open = new FileChooser();
        open.setTitle("open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File","*jpg","png"));
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if(file !=null){


            image=new Image(file.toURI().toString(),120,149,false,true);
            addStudents_imageView.setImage(image);
            getData.path= file.getAbsolutePath();
        }
    }

    public void addStudentsSearch(){
        FilteredList<studentData> filter = new FilteredList<>(addStudentsListD,e->true);

        addStudents_search.textProperty().addListener((Observable,oldValue,newValue)->{

            filter.setPredicate(predicateStudentData->{


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String searchKey = newValue.toLowerCase();
                if(predicateStudentData.getStudentNum().toString().contains(searchKey)){

                    return true;
                }
                else if(predicateStudentData.getYear().toLowerCase().contains(searchKey)){
                    return true;

                }else if(predicateStudentData.getCourse().toLowerCase().contains(searchKey)){
                    return true;

                } else if(predicateStudentData.getFirstName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStudentData.getLastName().toLowerCase().contains(searchKey)){
                    return true;

                } else if(predicateStudentData.getGender().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStudentData.getBirth().toLowerCase().contains(searchKey)){
                    return true;

                }else if(predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;

                }else{
                    return false;
                }

            });
            SortedList<studentData>sortList =new SortedList<>(filter);
            sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
            addStudents_tableView.setItems(sortList);

        });

    }
//1
public void addStudentsSearch1(){
    FilteredList<studentGrade> filter = new FilteredList<>(addStudentsListDGrade,e->true);

    studentGrade_search.textProperty().addListener((Observable,oldValue,newValue)->{

        filter.setPredicate(predicateStudentData->{


            if (newValue == null || newValue.isEmpty()) {
                return true;
            }


            String searchKey = newValue.toLowerCase();
            if(predicateStudentData.getStudentNum().toString().contains(searchKey)){

                return true;
            }
            else if(predicateStudentData.getYear().toLowerCase().contains(searchKey)){
                return true;

            }else if(predicateStudentData.getCourse().toLowerCase().contains(searchKey)){
                return true;

            } else if(predicateStudentData.getFinal_sem().toString().contains(searchKey)){
                return true;
            }else if(predicateStudentData.getSecond_sem().toString().contains(searchKey)){
                return true;

            } else if(predicateStudentData.getFirst_sem().toString().contains(searchKey)){
                return true;


            }else{
                return false;
            }

        });
        SortedList<studentGrade>sortList =new SortedList<>(filter);
        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
        studentGrade_tableView.setItems(sortList);

    });

}
    private String[] yearList = {"First Year","Second Year","Third Year","Fourth Year"};
    public void addStudentsYearList() {
        List<String> yearL = new ArrayList<>();
        for (String data : yearList) {
            yearL.add(data);
        }
        ObservableList Oblist = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(Oblist);
    }
    public  void addStudentsCourseList(){
        ObservableList<String> listC = FXCollections.observableArrayList();
        DataDriver Con = new DataDriver();
        ResultSet result = Con.connectD3();

        try{
            while(result.next()){
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);

        }catch (Exception e){e.printStackTrace();}

    }
    private String[] genderList ={"Male","Female","Others"};
    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();
        for (String data : genderList) {
            genderL.add(data);
        }
        ObservableList Oblist = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(Oblist);
    }
    private String [] statusList={"Enrolled","Not Enrolled","Inactive"};
    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();
        for (String data : statusList) {
            statusL.add(data);
        }
        ObservableList Oblist = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(Oblist);
    }





    public DashboardController () {
    }

    // student Grade
    public ObservableList<studentGrade>addStudentsListGradeData(){
        //nơi lấy dữ liệu từ cơ sở dữ liệu
        ObservableList<studentGrade> listStudentsGrade = FXCollections.observableArrayList();
        DataDriver Con = new DataDriver();
        ResultSet result = Con.connectDataGrade();
        try{

            while(result.next()){

                studentGrade studentD;
                studentD =new studentGrade(result.getInt("studentNum")
                        ,result.getString("year")
                        ,result.getString("course")
                        ,result.getDouble("first_sem")
                        ,result.getDouble("second_sem")
                        ,result.getDouble("final"));


                listStudentsGrade.add(studentD);
            }

        }catch (Exception e){e.printStackTrace();}
        return listStudentsGrade;
    }
    private ObservableList<studentGrade> addStudentsListDGrade; //nơi lưu trữ dữ liệu
    public void addStudentsShowListDataGrade(){

        //Phương thức này được sử dụng để hiển thị dữ liệu sinh viên trong một TableView của JavaFX.
        addStudentsListDGrade = addStudentsListGradeData();

        studentGrade_tableView.setItems(addStudentsListDGrade);
        studentGrade_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGrade_col_firstSem.setCellValueFactory(new PropertyValueFactory<>("first_sem"));
        studentGrade_col_secondSem.setCellValueFactory(new PropertyValueFactory<>("second_sem"));
        studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("final_sem"));

    }
    public void addStudentsSelectStuGrade(){ //Phương thức này được gọi khi một sinh viên được chọn trong TableView.
        studentGrade studentGrade = studentGrade_tableView.getSelectionModel().getSelectedItem();
        int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();
        if((num-1)< -1) return;
        studentGrade_studentNum.setText(String.valueOf((studentGrade.getStudentNum())));
        studentGrade_year.setText(studentGrade.getYear());
        studentGrade_course.setText(studentGrade.getCourse());

    }
    //update grade
    public void updateGrade(){
        DataDriver Con = new DataDriver();
        Double final_sem = (Double.parseDouble(studentGrade_firstSem.getText()) + Double.parseDouble(studentGrade_secondSem.getText()))/2;

        Con.updateDataGrade(Integer.parseInt(studentGrade_studentNum.getText()),
                Double.valueOf(studentGrade_firstSem.getText()),
                Double.valueOf(studentGrade_secondSem.getText()),
                final_sem);

        addStudentsShowListDataGrade();
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");
    }
    public void addGradeClear(){
        studentGrade_studentNum.setText("");
        studentGrade_year.setText("");
        studentGrade_course.setText("");
        studentGrade_firstSem.setText("");
        studentGrade_secondSem.setText("");


    }



    //student
    public ObservableList<studentData>addStudentsListData(){
        //nơi lấy dữ liệu từ cơ sở dữ liệu
        ObservableList<studentData> listStudents = FXCollections.observableArrayList();
        DataDriver Con = new DataDriver();
        ResultSet result = Con.connectDb();
        try{

            while(result.next()){

                studentData studentD;
                studentD =new studentData(result.getInt("studentNum")
                        ,result.getString("year")
                        ,result.getString("course")
                        ,result.getString("firstName")
                        ,result.getString("lastName")
                        ,result.getString("gender")
                        ,result.getString("birth")
                        ,result.getString("status")
                        ,result.getString("image"));

                listStudents.add(studentD);
            }

        }catch (Exception e){e.printStackTrace();}
        return listStudents;
    }
    private ObservableList<studentData> addStudentsListD; //nơi lưu trữ dữ liệu
    public void addStudentsShowListData(){

        //Phương thức này được sử dụng để hiển thị dữ liệu sinh viên trong một TableView của JavaFX.
        addStudentsListD = addStudentsListData();

        addStudents_tableView.setItems(addStudentsListD);
        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));


    }
   public void addStudentsSelect(){ //Phương thức này được gọi khi một sinh viên được chọn trong TableView.
        studentData studentD =addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();
        if((num-1)< -1) return;
        addStudents_studentNum.setText(String.valueOf((studentD.getStudentNum())));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());


       String uri = "file:" + studentD.getImage();
       image = new Image(uri,120,149,false,true );
        addStudents_imageView.setImage(image);
   }
   public void availableCourseAdd(){

       try{
           Alert alert;
            if(availableCourse_course_fld.getText().isEmpty()
                ||availableCourse_description_fld.getText().isEmpty()
                ||availableCourse_degree_fld.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else { // lỗi add ko đc
                DataDriver Con = new DataDriver();
                Con.insertCourse(availableCourse_course_fld.getText() , availableCourse_description_fld.getText() , availableCourse_degree_fld.getText());

                availableCourseShowListData();

                availableCourseClear();

        }



       }catch (Exception e){e.printStackTrace();}
   }

public void availableCourseUpdate() {
    Alert alert;
    try {
        if (availableCourse_course_fld.getText().isEmpty()
                || availableCourse_description_fld.getText().isEmpty()
                || availableCourse_degree_fld.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String course = availableCourse_course_fld.getText();
            String description = availableCourse_description_fld.getText();
            String degree = availableCourse_degree_fld.getText();
            System.out.println("aaaaaaaaa");
            // Lấy giá trị khóa học gốc để xác định bản ghi cần cập nhật
            String originalCourse = availableCourse_course_fld.getText();
            DataDriver Con = new DataDriver();
            Con.updateCourse(course, degree, description, originalCourse);
            availableCourseShowListData();
        }
    } catch (Exception e) {
        e.printStackTrace();
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unexpected Error");
        alert.setHeaderText(null);
        alert.setContentText("An unexpected error occurred. Please contact support.");
        alert.showAndWait();
    }
}



    public void availableCourseDelete() {
        Alert alert;
        try {
            if (availableCourse_course_fld.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the course name to delete.");
                alert.showAndWait();
            } else {
                String course = availableCourse_course_fld.getText();

                DataDriver Con = new DataDriver();
                Con.deleteCourse(course); // Sử dụng phương thức deleteCourse đã sửa
                availableCourseShowListData();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Course deleted successfully.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Error");
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error occurred. Please contact support.");
            alert.showAndWait();
        }
    }



    public void availableCourseClear(){
       availableCourse_course_fld.setText("");
       availableCourse_description_fld.setText("");
       availableCourse_degree_fld.setText("");

   }
   public ObservableList<courseData> availableCourseListData(){
        ObservableList<courseData> ListData =FXCollections.observableArrayList();

       DataDriver Con = new DataDriver();
       ResultSet result = Con.connectD();
       try{

           courseData courseD;

           while (result.next()){
               courseD = new courseData(result.getString("course")
                       ,result.getString("description")
                       ,result.getString("degree"));
               ListData.add(courseD);
           }

       }catch(Exception e){e.printStackTrace();}
       return ListData;
   }
    private ObservableList<courseData> availableCourseList; //nơi lưu trữ dữ liệu
    public void availableCourseShowListData(){

        //Phương thức này được sử dụng để hiển thị dữ liệu sinh viên trong một TableView của JavaFX.
        availableCourseList= availableCourseListData();

        availableCourse_tableView.setItems(availableCourseList);

        availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        availableCourse_col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));


    }
    public void availableCourseSelect (){
        courseData courseD = availableCourse_tableView.getSelectionModel().getSelectedItem();
        int num = availableCourse_tableView.getSelectionModel().getSelectedIndex();
        if((num-1)< -1) return;
        availableCourse_course_fld.setText(courseD.getCourse());
        availableCourse_description_fld.setText(courseD.getDescription());
        availableCourse_degree_fld.setText(courseD.getDegree());



    }



    private double x= 0;
    private   double y=0;

    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout ?");

            Optional<ButtonType>option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene= new Scene(root);
                root.setOnMousePressed((MouseEvent event)->{
                    x =event.getSceneX();
                    y= event.getSceneY();

                    stage.setOpacity(.8);

                });
                root.setOnMouseReleased((MouseEvent event)->{
                    stage.setOpacity(1);
                });
                root.setOnMouseDragged((MouseEvent event)->{
                   stage.setX(event.getScreenX() - x);
                   stage.setY(event.getScreenY() - y);

                });
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            }else return;

        }catch (Exception e){e.printStackTrace();}
    }
public void switchForm(ActionEvent event){   //tương tác giữa khác nút  để bk ddc nó hoạt động
    if(event.getSource() == home_btn){
    home_form.setVisible(true);
        addStudents_form.setVisible(false);
        availableCourse_form.setVisible(false);
        studentGrade_form.setVisible(false);

        home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#3f82ae,#26bf7d)");
        addStudents_btn.setStyle("-fx-background-color: transparent");
        availableCourse_btn.setStyle("-fx-background-color: transparent");
        studentGrade_btn.setStyle("-fx-background-color: transparent");

        homeDisplayTotalEnrolledStudents();
        homeDisplayFemaleEnrolled();
        homeDisplayMaleEnrolled();
    } else if (event.getSource() ==addStudents_btn) {
        home_form.setVisible(false);
        addStudents_form.setVisible(true);
        availableCourse_form.setVisible(false);
        studentGrade_form.setVisible(false);

        addStudents_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#3f82ae,#26bf7d)");
        home_btn.setStyle("-fx-background-color: transparent");
        availableCourse_btn.setStyle("-fx-background-color: transparent");
        studentGrade_btn.setStyle("-fx-background-color: transparent");

        addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        addStudentsCourseList();
        addStudentsSearch();
    }else if(event.getSource() ==availableCourse_btn){
        home_form.setVisible(false);
        addStudents_form.setVisible(false);
        availableCourse_form.setVisible(true);
        studentGrade_form.setVisible(false);

        availableCourse_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#3f82ae,#26bf7d)");
        addStudents_btn.setStyle("-fx-background-color: transparent");
        home_btn.setStyle("-fx-background-color: transparent");
        studentGrade_btn.setStyle("-fx-background-color: transparent");

        availableCourseShowListData();
    }else if(event.getSource() ==studentGrade_btn){
        home_form.setVisible(false);
        addStudents_form.setVisible(false);
        availableCourse_form.setVisible(false);
        studentGrade_form.setVisible(true);

        studentGrade_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#3f82ae,#26bf7d)");
        addStudents_btn.setStyle("-fx-background-color: transparent");
        availableCourse_btn.setStyle("-fx-background-color: transparent");
        home_btn.setStyle("-fx-background-color: transparent");
    }

}

    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataDriver c2 = new DataDriver();
        try{
            ResultSet c1 = c2.getClientData();
            if (c1.next()) {

                username.setText( "            "+c1.getString("username"));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        homeDisplayTotalEnrolledStudents();
        homeDisplayFemaleEnrolled();
        homeDisplayMaleEnrolled();
        addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        addStudentsCourseList();
        availableCourseShowListData();
        addStudentsShowListDataGrade();


    }
}
