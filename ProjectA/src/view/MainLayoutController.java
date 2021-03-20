package view;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import domain.Row;
import domain.foodlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
public class MainLayoutController {
	@FXML
	private TextField cm;
	@FXML
	private TextField kg;
	@FXML
	private Label state;
	@FXML
	private TableView<Row> table;
	@FXML
	private TextField week;
	@FXML
	private TableColumn<String, String> mon;
	@FXML
	private TableColumn<String, String> tue;
	@FXML
	private TableColumn<String, String> wed;
	@FXML
	private TableColumn<String, String> thu;
	@FXML
	private TableColumn<String, String> fri;
	@FXML
	private TableColumn<String, String> sat;
	@FXML
	private TableColumn<String, String> sun;
	@FXML
	private CheckBox Man;
	@FXML
	private CheckBox Woman;
	@FXML
	private TextField age;
	@FXML
	private VBox schedule;
	public void mancheck() {
		Woman.setSelected(false);
	}
	public void womancheck() {
		Man.setSelected(false);
	}
	final ObservableList<Row> data = FXCollections.observableArrayList();
	public void init() {
		table.setEditable(false);		
		mon.setCellValueFactory(new PropertyValueFactory<>("mon"));
		tue.setCellValueFactory(new PropertyValueFactory<>("tue"));
		wed.setCellValueFactory(new PropertyValueFactory<>("wed"));
		thu.setCellValueFactory(new PropertyValueFactory<>("thu"));
		fri.setCellValueFactory(new PropertyValueFactory<>("fri"));
		sat.setCellValueFactory(new PropertyValueFactory<>("sat"));
		sun.setCellValueFactory(new PropertyValueFactory<>("sun"));
		table.setItems(data);
	}
	public void BMI() {
		float w=Float.parseFloat(kg.getText());//몸무게
		float h=Float.parseFloat(cm.getText());//키
		float temp=w/(h/100*h/100);
		int flg=0;
		String result;
		if(temp>=23) {
			schedule.setVisible(true);
			flg=minus(w,h);
			if(temp>=35) {
				result="고도 비만";
			}
			else if(temp>=30){
				result="중등도 비만";
			}
			else if(temp>=25) {
				result="경도 비만";
			}
			else{
				result="과체중";
			}
		}
		else {
			schedule.setVisible(false);
			if(temp>=18.5) {
				result="정상";
			}
			else{
				result="저체중";
			}
		}
		state.setText("BMI "+temp+"  |  "+result);
		if(flg>0) {
			state.setText(state.getText()+"  |  "+flg+"kg 만큼 감량하시면 정상 체중이 됩니다.");
		}
	}
	public int minus(float kg,float cm) {
		float result=kg/(cm/100*cm/100);
		int w=0;
		while(result>23) {
			w++;
			result=(kg-w)/(cm/100*cm/100);
		}
		return w;
	}
	public void makeFood() {
		data.clear();
		float kgA=Float.parseFloat(kg.getText()),cmA=Float.parseFloat(cm.getText()),sj;
		int kcal=minus(kgA,cmA)*7700/Integer.parseInt(week.getText())/7;
		int ageA=Integer.parseInt(age.getText());
		if(Man.isSelected()==true) {
			sj=(float)(66.47+(13.75*kgA)+(5*cmA)-(6.76*ageA))/12;
			kcal-=(float)(66.47+(13.75*kgA)+(5*cmA)-(6.76*ageA))/4*3;
		}
		else {
			sj=(float)(655.1+(9.56*kgA)+(1.85*cmA)-(4.68*ageA))/12;
			kcal-=(float)(655.1+(9.56*kgA)+(1.85*cmA)-(4.68*ageA))/4*3;
		}
		
		try {
			File file=new File("lib/calorie.txt");
			File file2=new File("lib/food.txt");
			ArrayList<foodlist> Exercise=new ArrayList<foodlist>();
			ArrayList<foodlist> Food=new ArrayList<foodlist>();
			FileInputStream fis=new FileInputStream(file);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			FileInputStream fis2=new FileInputStream(file2);
			InputStreamReader isr2=new InputStreamReader(fis2);
			BufferedReader br2=new BufferedReader(isr2);
			while (true){
				String line=br.readLine();
				if (line == null){
					break;
				}
				foodlist temp=new foodlist();
				String[] items = line.split(":");
				temp.name= items[0];
				temp.kcal=Float.parseFloat(items[1]);
				Exercise.add(temp);
			}
			while (true){
				String line=br2.readLine();
				if (line == null){
					break;
				}
				foodlist temp=new foodlist();
				String[] items = line.split(":");
				temp.name= items[0];
				temp.kcal=Float.parseFloat(items[1]);
				Food.add(temp);
			}
			br.close();
			br2.close();
			for(int c=0;c<3;c++) {
				if(c==0) {
					data.add(new Row("아침","아침","아침","아침","아침","아침","아침"));
				}
				else if(c==1) {
					data.add(new Row("점심","점심","점심","점심","점심","점심","점심"));
				}
				else {
					data.add(new Row("저녁","저녁","저녁","저녁","저녁","저녁","저녁"));
				}
				String[] list=new String[7];
				for(int a=0;a<7;a++) {
					int indext=(int)(Math.random()*52),usekcal=0,b=0;
					while(usekcal<sj) {
						b++;
						usekcal+=Food.get(indext).kcal;
					}
					System.out.println(Food.get(indext).name+" "+usekcal+" "+b);
					list[a]=Food.get(indext).name+" "+b+"개";
				}
				data.add(new Row(list[0],list[1],list[2],list[3],list[4],list[5],list[6]));
			}
			data.add(new Row("운동","운동","운동","운동","운동","운동","운동"));
			String[] list=new String[7];
			for(int a=0;a<7;a++) {
				int indext=(int)(Math.random()*23),usekcal=0,b=0;
				while(usekcal<kcal) {
					usekcal+=Exercise.get(indext).kcal*kgA;
					b++;
				}
				System.out.println(Exercise.get(indext).name+" "+usekcal+" "+b);
				list[a]=Exercise.get(indext).name+" "+b*10+"분";
			}
			data.add(new Row(list[0],list[1],list[2],list[3],list[4],list[5],list[6]));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("오류");
		}
		data.removeAll();
		
	}
	
}