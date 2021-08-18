/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.charts;

import Extras.AnchorButton;
import Extras.Materia;
import Extras.Separator;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MATHEUSDACOSTACAFFER
 */
public class MainFrameController implements Initializable {

    @FXML
    private Button ChangeChart;

    @FXML
    private ScrollPane ContentScrollConteudo;

    @FXML
    private PieChart PieChart;

    @FXML
    private Label ContentInfo;

    @FXML
    private AnchorPane Content;

    @FXML
    private CategoryAxis x;

    @FXML
    private BarChart<?, ?> BarChart;

    @FXML
    private NumberAxis y;

    @FXML
    private ScrollPane ContentScroll;

    @FXML
    private LineChart<?, ?> lineChar;

    @FXML
    private AnchorPane ContentConteudo;

    boolean byContent = false;
    boolean barChart = true;
    FileReader file = new FileReader();

    ArrayList<Extras.AnchorButton> buttonList = new ArrayList<>();
    ArrayList<Extras.AnchorButton> buttonListConteudo = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Materia> materias = file.getMaterias();
        ArrayList<Materia> materiasGeral = Extras.Separator.getGeralMateria(materias);
        ArrayList<Materia> conteudoGeral = Extras.Separator.getConteudo(materias);

        getAllContents();

        ChangeChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (barChart) {
                    BarChart.setOpacity(0);
                    lineChar.setOpacity(1);
                    barChart = false;
                } else {
                    BarChart.setOpacity(1);
                    lineChar.setOpacity(0);
                    barChart = true;
                }
            }
        });

        for (Extras.AnchorButton i : buttonList) {
            Button button = i.button;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    byContent = false;
                    ContentInfo.setText(i.materia.toString());
                    getContentBy(i.materia.Nome);
                }
            });
        }

        for (Extras.AnchorButton i : buttonListConteudo) {
            Button button = i.button;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    byContent = true;
                    ContentInfo.setText(i.materia.toString());
                    getContentBy(i.materia.Conteudo);
                }
            });
        }

    }

    public void getAllContents() {
        ArrayList<Materia> materiasGeral = Extras.Separator.getGeralMateria(file.getMaterias());
        buttonList = new ArrayList<>();
        for (int i = 0; i < materiasGeral.size(); i++) {
            Button button = new Button();
            button.setText(materiasGeral.get(i).Nome);
            Content.getChildren().add(button);
            Content.setTopAnchor(button, 30.0 * i);
            Extras.AnchorButton anchorButton = new AnchorButton();
            anchorButton.materia = materiasGeral.get(i);
            anchorButton.button = button;
            buttonList.add(anchorButton);
        }
        ArrayList<Materia> conteudoGeral = Extras.Separator.getConteudo(file.getMaterias());

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        //System.out.println(getAllQuestions(conteudoGeral));
        for (int i = 0; i < conteudoGeral.size(); i++) {
            //System.out.println(conteudoGeral.get(i).Conteudo);
            //System.out.println("Per: "+ ((conteudoGeral.get(i).Acertos+conteudoGeral.get(i).Erros)));
            pieChartData.add(new PieChart.Data(conteudoGeral.get(i).Conteudo, ((conteudoGeral.get(i).Acertos + conteudoGeral.get(i).Erros) / 1)));
        }
        PieChart.setData(pieChartData);

        buttonListConteudo = new ArrayList<>();
        for (int i = 0; i < conteudoGeral.size(); i++) {
            Button button = new Button();
            button.setText(conteudoGeral.get(i).Nome);
            ContentConteudo.getChildren().add(button);
            ContentConteudo.setTopAnchor(button, 30.0 * i);
            Extras.AnchorButton anchorButton = new AnchorButton();
            anchorButton.materia = conteudoGeral.get(i);
            anchorButton.button = button;
            buttonListConteudo.add(anchorButton);
        }

    }

    public int getAllQuestions(ArrayList<Materia> conteudoGeral) {
        int all = 0;
        for (int i = 0; i < conteudoGeral.size(); i++) {
            all += conteudoGeral.get(i).Acertos;
            all += conteudoGeral.get(i).Erros;
        }
        return all;
    }

    public ArrayList<Extras.Prova> getProvas(ArrayList<String> provasName){
            ArrayList<Extras.Prova> provas = new ArrayList<>();

        for (int i = 0; i < provasName.size(); i++) {
            Extras.Prova prova = new Extras.Prova();

            prova.prova = provasName.get(i);
            ArrayList<Materia> conteudoGeral = Extras.Separator.getGeralMateria(file.getMaterias(), provasName.get(i));
            prova.materias = conteudoGeral;

            provas.add(prova);
        }
        return provas;
    }
    
    public void getContentBy(String text) {

        lineChar.getData().clear();
        BarChart.getData().clear();

        //ArrayList<String> provasName = file.getProvas();
        ArrayList<String> provasName = file.getProvasByMaterias(file.getMaterias());
        
        ArrayList<Extras.Prova> provas = getProvas(provasName);
        
        XYChart.Series series = new XYChart.Series();
        XYChart.Series barSeries = new XYChart.Series();
        if (!byContent) {
            int g = 0;
            for (int i = 0; i < provas.size(); i++) {
                for (Materia materia : provas.get(i).materias) {
                    System.out.println(materia.Nome + " | " + text);
                    if (Extras.Separator.compareString(materia.Nome, text)) {

                        g++;

                        series.getData().add(new XYChart.Data(provas.get(i).prova, materia.Acertos / (materia.Erros + 1)));
                        series.setName(materia.Nome);

                        barSeries.getData().add(new XYChart.Data(provas.get(i).prova, materia.Acertos + materia.Erros + 1));
                        barSeries.setName(materia.Nome);

                        break;
                    }
                }
            }
            if (g == 0) {
                System.out.println("ERRO");
            } else {
                lineChar.getData().addAll(series);
                BarChart.getData().addAll(barSeries);
            }
        } else {
            int g = 0;
            for (int i = 0; i < provas.size(); i++) {
                for (Materia materia : Separator.getConteudo(provas.get(i).materias)) {
                    System.out.println(materia.Conteudo + " | " + text);
                    if (Extras.Separator.compareString(materia.Conteudo, text)) {

                        g++;
                        series.getData().add(new XYChart.Data(provas.get(i).prova, materia.Acertos / (materia.Erros + 1)));
                        series.setName(materia.Conteudo);

                        barSeries.getData().add(new XYChart.Data(provas.get(i).prova, materia.Acertos + materia.Erros + 1));
                        barSeries.setName(materia.Conteudo);

                        break;
                    }
                }
            }
            if (g == 0) {
                System.out.println("ERRO");
            } else {
                lineChar.getData().addAll(series);
                BarChart.getData().addAll(barSeries);
            }
        }
    }

}
